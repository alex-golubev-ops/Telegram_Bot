package com.golubev.telegrambot.bot;

import com.golubev.telegrambot.webservice.service.CityInfoService;
import com.golubev.telegrambot.webservice.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Bot extends TelegramLongPollingBot {
    private final CityService cityService;
    private final CityInfoService cityInfoService;
    @Value("${telegram.username}")
    private String botUSerName;
    @Value("${telegram.token}")
    private String botToken;


    @Autowired
    public Bot(DefaultBotOptions options, CityService cityService, CityInfoService cityInfoService) {
        super(options);
        this.cityService = cityService;
        this.cityInfoService = cityInfoService;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {

            if (update.getMessage().hasText()) {

                sendMsg(update.getMessage().getChatId(),
                        update.getMessage().getText(),
                        update.getMessage().getMessageId());

            }

        } else if (update.hasCallbackQuery()) {

            EditMessageText newMessage = new EditMessageText()
                    .setChatId(update.getCallbackQuery().getMessage().getChatId())
                    .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                    .setText(update.getCallbackQuery().getData());

            try {

                execute(newMessage);

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            sendMsg(update.getCallbackQuery().getMessage().getChatId(),
                    update.getCallbackQuery().getData(),
                    update.getCallbackQuery().getMessage().getMessageId());
        }
    }

    private void sendMsg(Long chatId, String message, Integer messageId) {

        SendMessage sendMessage;
        String send = "";

        if (message.equals("/start") || message.equals("/Start")) {
            send = "Здравствуйте! Вас приветствует путеводитель! Я с радостью " +
                    "покажу все достопримечательности городов)))))\nВведите город";
            sendMessage = createMessage(send, chatId, messageId);
        } else {

            List<String> allCityInfo = cityInfoService.allCityInfoByCityName(message.trim());

            if (allCityInfo != null) {
                if (!allCityInfo.isEmpty()) {
                    send = String.join(".\n", allCityInfo);
                } else {
                    send = "Этот город не содержит интересных мест((( Мне очень жаль";
                }
                sendMessage = createMessage(send, chatId, messageId);
            } else {

                send = "Такого города я не знаю! Попробуй ввести другой!)";
                sendMessage = createMessage(send, chatId, messageId);

            }
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private SendMessage createMessage(String send, Long chatId, Integer messageId, InlineKeyboardMarkup setInline) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(setInline);
        sendMessage.setText(send);

        return sendMessage;
    }

    private SendMessage createMessage(String send, Long chatId, Integer messageId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyToMessageId(messageId);
        sendMessage.setChatId(chatId);
        sendMessage.setText(send);
        return sendMessage;
    }


    private InlineKeyboardMarkup setInline(List<String> cities) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (String city : cities) {
            List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
            keyboardButtonsRow.add(new InlineKeyboardButton().setText(city).setCallbackData(city));
            rowList.add(keyboardButtonsRow);
        }

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }


    @Override
    public String getBotUsername() {
        return botUSerName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
