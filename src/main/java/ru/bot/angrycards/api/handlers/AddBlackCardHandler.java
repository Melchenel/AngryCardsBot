package ru.bot.angrycards.api.handlers;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bot.angrycards.api.BotState;
import ru.bot.angrycards.api.DataCache;
import ru.bot.angrycards.api.InputMessageHandler;
import ru.bot.angrycards.dto.Players;

@Data
@Slf4j
@Component
public class AddBlackCardHandler implements InputMessageHandler {

    @Autowired
    DataCache dataCache;

    @Override
    public SendMessage handle(Message message) {

        Players.players.get(message.getFrom().getId()).setBlackCart("Лучшее воспоминание детства это ___");

        log.info("Игрок " + message.getFrom().getFirstName() + message.getFrom().getFirstName()
                + "получил черную карту");

        dataCache.setBotStateCurrentForCurrentUser(message.getFrom().getId(), BotState.SHOW_BLACK_CARD);
        return null;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.ADD_BLACK_CARD;
    }
}
