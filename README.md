<div align="center">
  <img src="https://github.com/addo37/AbilityBots/blob/gh-pages/images/API%20BOT-03.png?raw=true" alt="abilitybots" width="200" height="200"/>

  [![Build Status](https://travis-ci.org/addo37/ExampleBots.svg?branch=master)](https://travis-ci.org/addo37/ExampleBots)
  [![Jitpack](https://jitpack.io/v/addo37/ExampleBots.svg)](https://jitpack.io/#addo37/ExampleBots)
  [![Telegram](http://trellobot.doomdns.org/telegrambadge.svg)](https://telegram.me/AbilityBots)
</div>

Example Bots
------------

Your entry point should be the **[ExampleBot](./src/main/java/org/telegram/examplebots/ExampleBot.java)**, it showcases most of the features of the AbilityBot.

Don't forget that you can do the following with ANY AbilityBot!

* /claim - Claims this bot
* /commands - Reports all user-defined commands (abilities)
* /backup - Returns a backup of the bot database
* /recover - Recovers the database
* /promote @username- Promotes user to bot admin
* /demote @username - Demotes bot admin to user
* /ban @username - Bans the user from accessing your bot commands and features
* /unban @username - Lifts the ban from the user

Sample:

```java
public Ability saysHelloWorld() {
    return Ability.builder()
        .name("hello") // Name and command (/hello)
        .info("Says hello world!") // Necessary if you want it to be reported via /commands
        .privacy(PUBLIC)  // Choose from Privacy Class (Public, Admin, Creator)
        .locality(ALL) // Choose from Locality enum Class (User, Group, PUBLIC)
        .input(0) // Arguments required for command (0 for ignore)
        .action(ctx -> {
          /*
          ctx has the following main fields that you can utilize:
          - ctx.update() -> the actual Telegram update from the basic API
          - ctx.user() -> the user behind the update
          - ctx.firstArg()/secondArg()/thirdArg() -> quick accessors for message arguments (if any)
          - ctx.arguments() -> all arguments
          - ctx.chatId() -> the chat where the update has emerged

          NOTE that chat ID and user are fetched no matter what the update carries.
          If the update does not have a message, but it has a callback query, the chatId and user will be fetched from that query.
           */
          // Custom sender implementation
          sender.send("Hello World!", ctx.chatId());
        })
        .build();
}
```

Testing
-------
Check out the **[ExampleBotTest](./src/test/java/org/telegram/examplebots/ExampleBotTest.java)** on how you can harness the power of mocked senders!

Sample
```java
@Test
public void canSayHelloWorld() {
    Update mockedUpdate = mock(Update.class);
    EndUser endUser = EndUser.endUser(USER_ID, "Abbas", "Abou Daya", "addo37");
    MessageContext context = new MessageContext(mockedUpdate, endUser, CHAT_ID);

    bot.saysHelloWorld().consumer().accept(context);

    // We verify that the sender was called only ONCE and sent Hello World to CHAT_ID
    Mockito.verify(sender, times(1)).send("Hello World!", CHAT_ID);
}
```