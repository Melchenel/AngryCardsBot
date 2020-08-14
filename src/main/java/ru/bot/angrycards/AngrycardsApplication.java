package ru.bot.angrycards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class AngrycardsApplication {
    public static void main(String[] args) {

        ApiContextInitializer.init();

        SpringApplication.run(AngrycardsApplication.class, args);
    }

}
