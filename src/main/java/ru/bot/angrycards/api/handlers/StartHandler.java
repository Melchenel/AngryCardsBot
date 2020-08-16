package ru.bot.angrycards.api.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bot.angrycards.api.BotState;
import ru.bot.angrycards.api.InputMessageHandler;

@Component
public class StartHandler implements InputMessageHandler {

    @Override
    public SendMessage handle(Message message) {
        int userId = message.getFrom().getId();
        long chatId = message.getChatId();

        SendMessage sendMessage = new SendMessage(chatId, "Старт игры");
        return sendMessage;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.START;
    }
}
