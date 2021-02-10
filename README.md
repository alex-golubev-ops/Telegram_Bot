# Telegram Bot and Menage

**О проекте:** 
В проект входит сам телеграм бот и REST приложение по упрвлению данным телеграмботом.Телеграм
бот выдает информацию по введенному городу, если присутствует город в базе данных, иначе выдаст, что не знает данный город.

### TelegramBot:

Телеграм бот создан на API Telegrambots. База данных создана при помощи Spring Data & Hibernate.

### Для запуска нужно:
 Настроить подключение к базе данных в resources/application.properties
 Запустить метод main класса com/golubev/telegrambot/Application
 
 ### Telegrambot information:
 **Name:** CitiesInformationBot
 ###
 **Token:** 1386328487:AAFSsUghfIOi1KcwtAG4ju8SlJSVawJj1f0