package org.telegram.examplebots;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.db.MapDBContext;
import org.telegram.abilitybots.api.objects.EndUser;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.abilitybots.api.sender.MessageSender;
import org.telegram.telegrambots.api.objects.Update;

import static org.mockito.Mockito.*;

public class ExampleBotTest {

  public static final int USER_ID = 1337;
  public static final long CHAT_ID = 1337L;

  private ExampleBot bot;
  private DBContext db;
  private MessageSender sender;

  @Before
  public void setUp() {
    // Offline instance will get deleted at JVM shutdown
    db = MapDBContext.offlineInstance("test");
    bot = new ExampleBot();
    sender = mock(MessageSender.class);

    bot.setSender(sender);
  }

  @Test
  public void canSayHelloWorld() {
    Update mockedUpdate = mock(Update.class);
    EndUser endUser = EndUser.endUser(USER_ID, "Abbas", "Abou Daya", "addo37");
    MessageContext context = MessageContext.newContext(mockedUpdate, endUser, CHAT_ID);

    bot.saysHelloWorld().action().accept(context);

    // We verify that the sender was called only ONCE and sent Hello World to CHAT_ID
    Mockito.verify(sender, times(1)).send("Hello World!", CHAT_ID);
  }

  @After
  public void tearDown() {
    db.clear();
  }
}