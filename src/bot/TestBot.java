package bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

/**
 * Main program for the discord bot, Test Bot just named Test Boot
 * @author Luis Perez
 */
public class TestBot {
    public static JDA jda;
    public static final String PREFIX = "-";
    public static final String BOT_NAME = "Test Bot";

    public static void main(String[] args) throws LoginException
    {
        jda = JDABuilder.createDefault("temporary string, actual string should not be public").build();

        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.playing("Cool Stuff"));

        jda.addEventListener(new Commands());

    }
}
