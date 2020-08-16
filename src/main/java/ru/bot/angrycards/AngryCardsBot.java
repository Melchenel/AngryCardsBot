package ru.bot.angrycards;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bot.angrycards.facades.TelegramFacade;

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
    public BotApiMethod onWebhookUpdateReceived(Update update) {

        if(update.getMessage() != null && update.getMessage().hasText()){

           // SendMessage sendMessage = telegramFacade.handleMessage(update);

            long chatId = update.getMessage().getChatId();

            try{
                SendMessage sendMessage = new SendMessage();
              //  execute(new SendMessage(chatId, "hello " + update.getMessage().getText()));
                sendMessage.setText(update.getMessage().toString());
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                execute(sendMessage);
               // execute(new SendMessage(chatId, update.getMessage().getChat().getUserName()));
                //execute(new SendMessage(chatId, update.getMessage().getChatId().toString()));
               // execute(new SendMessage(chatId, update.getMessage().getNewChatMembers().toString()));
            }
            catch (TelegramApiException e){
                e.getMessage();
            }
        }
        return null;
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

    public void setWebHookPath(String webHookPath) {
        this.webHookPath = webHookPath;
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }
}
