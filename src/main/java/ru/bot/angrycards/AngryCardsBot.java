package ru.bot.angrycards;


import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.api.chat.TLChat;
import org.telegram.api.engine.TelegramApi;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import org.telegram.telegrambots.meta.api.objects.Update;

import ru.bot.angrycards.facades.TelegramFacade;

@Setter
public class AngryCardsBot extends TelegramWebhookBot {

    private String webHookPath;
    private String botUserName;
    private String botToken;

    @Autowired
    private TelegramFacade telegramFacade;



    public AngryCardsBot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {

        return telegramFacade.handleMessage(update);
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

}
