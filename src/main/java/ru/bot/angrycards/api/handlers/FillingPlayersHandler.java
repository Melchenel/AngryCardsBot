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
import ru.bot.angrycards.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@Component
public class FillingPlayersHandler implements InputMessageHandler {

    @Autowired
    DataCache dataCache;

    @Override
    public SendMessage handle(Message message) {
        int userId = message.getFrom().getId();
        long chatId = message.getChatId();

        SendMessage sendMessage;

        if( Players.players.get(userId) == null){
            Players.players.put(userId, createUserInfo(message));

            log.info(Players.players.toString());

            dataCache.setBotStateCurrentForCurrentUser(userId, BotState.USER_READY);

            log.info("BotState:   " + dataCache.getBotStateForCurrentUser(userId).toString());

            sendMessage = new SendMessage(chatId, "Игрок" + message.getFrom().getFirstName());
        }
        else {
            sendMessage = new SendMessage(chatId, "Игрок" + message.getFrom().getFirstName()  +" уже добавлен в игру");
        }
        return sendMessage;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.FILLING_PLAYERS;
    }

    private UserDTO createUserInfo(Message message){
        UserDTO user = new UserDTO();
        List<String> whiteCards = new ArrayList<>();

        whiteCards.add("конская залупа");
        whiteCards.add("яйцо Гарольда");

        user.setName(message.getFrom().getFirstName());
        user.setCount(0);
        user.setWhiteCards(whiteCards);

        return user;
    }
}
