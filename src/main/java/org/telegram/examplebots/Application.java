package org.telegram.examplebots;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.logging.BotLogger;

public class Application {
    public static void main(String... args) {
        ApiContextInitializer.init();

        TelegramBotsApi api = new TelegramBotsApi();
        try {
            api.registerBot(new HelloBot());
        } catch (TelegramApiRequestException e) {
            BotLogger.error("Oops, something went wrong while registering bot", e);
        }
    }
}