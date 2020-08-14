package ru.bot.angrycards.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bot.angrycards.AngryCardsBot;

@RestController
public class AngryCardsController {

    private final AngryCardsBot angryCardsBot;

    public AngryCardsController(AngryCardsBot angryCardsBot) {
        this.angryCardsBot = angryCardsBot;
    }

    @PostMapping(value = "/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update){
        return angryCardsBot.onWebhookUpdateReceived(update);
    }
}
