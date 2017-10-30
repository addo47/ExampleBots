package org.telegram.examplebots;

import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;

import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

public class HelloBot extends AbilityBot {
  public static final String BOT_TOKEN = "HELLOBOT_TOKEN";
  public static final String BOT_USERNAME = "HELLOBOT_USERNAME";

  public HelloBot() {
    super(BOT_TOKEN, BOT_USERNAME);
  }

  @Override
  public int creatorId() {
    return 123456789;
  }

  public Ability sayHelloWorld() {
    return Ability
        .builder()
        .name("hello")
        .info("says hello world!")
        .locality(ALL)
        .privacy(PUBLIC)
        .action(ctx -> silent.send("Hello world!", ctx.chatId()))
        .build();
  }
}
