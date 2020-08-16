package ru.bot.angrycards.facades.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bot.angrycards.facades.TelegramFacade;
import ru.bot.angrycards.api.BotState;
import ru.bot.angrycards.api.BotStateContext;
import ru.bot.angrycards.api.DataCache;

@Component
@Slf4j
public class DefaultTelegramFacade implements TelegramFacade {

    @Autowired
    private DataCache dataCache;

    @Autowired
    private BotStateContext botStateContext;

    @Override
    public SendMessage handleMessage(Update update) {
        SendMessage sendMessage = null;

        Message message = update.getMessage();
        if(message != null && message.hasText()){
            log.info("New message from user: {}, chatId: {}, text: {}",
                    message.getFrom().getUserName(), message.getChatId(), message.getText());
            sendMessage = handleInputMessage(message);
        }

        return sendMessage;
    }

    private SendMessage handleInputMessage(Message message){
        String inputMessage = message.getText();
        int userId = message.getFrom().getId();

        SendMessage sendMessage;

        BotState botState;

        switch (inputMessage){
            case "/start":
                botState = BotState.START;
            case "/restart":
                botState = BotState.RESTART;
            case "Показать количество очков":
                botState = BotState.SHOW_WIN;
            case "Получить черную карту":
                botState = BotState.ADD_BLACK_CARD;
            case "Получить белые карты":
                botState = BotState.ADD_WHITE_CARDS;
            case "Выложить белую карту ":
                botState = BotState.SHOW_NAME_GAME;
            case "Показать результат":
                botState = BotState.SWOW_RESULT;
            case "Закончить игру":
                botState = BotState.END;
            case "Показать правила игры":
                botState = BotState.SHOW_RULES_OF_GAME;
            default:
                botState = dataCache.getBotStateForCurrentUser(userId);
                break;
        }

        dataCache.setBotStateCurrentForCurrentUser(userId,botState);

        sendMessage = botStateContext.processInputMessage(botState, message);


        return sendMessage;
    }
}
