package ru.bot.angrycards.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import ru.bot.angrycards.AngryCardsBot;

@Data
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class AngryCardsConfig {

    private String webHookPath;
    private String botUserName;
    private String botToken;

    @Bean
    public AngryCardsBot createAngryCardsBot(){
        DefaultBotOptions defaultBotOptions = ApiContext.getInstance(DefaultBotOptions.class);

        AngryCardsBot angryCardsBot = new AngryCardsBot(defaultBotOptions);
        angryCardsBot.setBotToken(botToken);
        angryCardsBot.setBotUserName(botUserName);
        angryCardsBot.setWebHookPath(webHookPath);

        return angryCardsBot;
    }
}
