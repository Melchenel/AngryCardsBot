package ru.bot.angrycards.api.handlers;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bot.angrycards.api.BotState;
import ru.bot.angrycards.api.InputMessageHandler;

@Data
@Slf4j
@Component
public class UserReadyHandler implements InputMessageHandler {
    @Override
    public SendMessage handle(Message message) {
        log.info(message.getFrom().getUserName() + " ожидает");
        return null;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.USER_READY;
    }
}
