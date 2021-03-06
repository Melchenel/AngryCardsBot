package ru.bot.angrycards.api.handlers;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bot.angrycards.api.BotState;
import ru.bot.angrycards.api.InputMessageHandler;
import ru.bot.angrycards.dto.Players;

@Data
@Slf4j
@Component
public class ShowBlackCardHandler implements InputMessageHandler {

    @Override
    public SendMessage handle(Message message) {
        String blackCardText = Players.players.get(message.getFrom().getId()).getBlackCart();

        if(message.getText().equals(blackCardText)){

        }
        return null;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SHOW_BLACK_CARD;
    }
}
