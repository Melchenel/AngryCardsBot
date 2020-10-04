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
public class StartGameHandler implements InputMessageHandler {

    @Autowired
    DataCache dataCache;

    @Override
    public SendMessage handle(Message message) {
        SendMessage sendMessage;

        if(Players.players.size() != 0 && Players.players !=null){

            initFirstLeader(message);

            sendMessage = new SendMessage(message.getChatId(),
                    message.getFrom().getUserName() + " ведущий. Выложите черную карту");

            log.info("Игрок " + message.getFrom().getFirstName() + message.getFrom().getFirstName()
                    + "получил черную карту");

            dataCache.setBotStateCurrentForCurrentUser(message.getFrom().getId(), BotState.SHOW_BLACK_CARD);

            log.info("BotState:   " + dataCache.getBotStateForCurrentUser(message.getFrom().getId()).toString());

        }
        else {
            sendMessage = new SendMessage(message.getChatId(),
                    "Игроков нет. Пожалуйста, присоединитесь к игре." +
                            " За дополнительной информацией вызовите команду /help");
        }

        return sendMessage;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.START;
    }

    private void initFirstLeader(Message message){

        Players.players.get(message.getFrom().getId()).setIsLeader(true);
        Players.players.get(message.getFrom().getId()).setBlackCart("Лучшее воспоминание детства это ___");

    }
}
