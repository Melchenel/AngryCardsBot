package ru.bot.angrycards.api;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BotStateContext {
    private Map<BotState, InputMessageHandler> handlerMap = new HashMap<>();

    public BotStateContext(List<InputMessageHandler> handlers) {
        handlers.forEach(handler -> this.handlerMap.put(handler.getHandlerName(),handler));
    }

    public SendMessage processInputMessage(BotState currentState, Message message){
        InputMessageHandler inputMessageHandler = findMessageHandler(currentState);
        return inputMessageHandler.handle(message);
    }

    private InputMessageHandler findMessageHandler(BotState currentState){
        return handlerMap.get(currentState);
    }

}
