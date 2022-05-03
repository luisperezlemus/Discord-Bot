package bot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Luis Perez
 * @version 8/2021 and 12/2021
 */
public class Commands extends ListenerAdapter
{

    /**
     * This method listens for messages sent on any channel. It then finds for the command prefix and the
     * command being triggered. Does the command triggered.
     */
    public void onMessageReceived(@NotNull MessageReceivedEvent event)
    {
        String[] args = event.getMessage().getContentRaw().split("\\s+"); //get the messages and split the spaces to split the String (any size space)
        String command = args[0]; // gets the command, since the prefix and command are in the same string

        if (command.length() > 0 && command.substring(0,TestBot.PREFIX.length()).equalsIgnoreCase(TestBot.PREFIX)) // checks if prefix is there
        {
            command = command.substring(TestBot.PREFIX.length()); // command after prefix

            switch(command.toLowerCase())
            {
                case Constants.GREETING:
                    greetingCommand(event);
                    break;
                case Constants.INFO:
                    infoCommand(event);
                    break;
                case Constants.HELP:
                    helpCommand(event);
                    break;
                case Constants.EXPOSE:
                    exposeCommand(event);
                    break;
                case Constants.JOIN:
                    joinVoiceChannelCommand(event);
                    break;
                case Constants.LEAVE:
                    leaveVoiceChannelCommand(event);
                    break;
                case Constants.BITRATE:
                    getBitrateCommand(event);
                    break;
                case Constants.ANIMAL:
                    if (args[1].equalsIgnoreCase(Constants.ANIMAL_FACT))
                    {
                        animalFactCommand(event);
                    }
                    break;
                case Constants.SPAM:
                    try
                    {
                        String arg = args[1];
                        spamCommand(event, arg);
                    }
                    catch (Exception e)
                    {
                        event.getChannel().sendMessage("Invalid Argument. Use Case: " + TestBot.PREFIX + Constants.SPAM + " <int>").queue();
                    }
                case Constants.CLEAR:
                    try {
                        String arg = args[1];
                        clearCommand(event, arg);
                    } catch (Exception e)
                    {
                        event.getChannel().sendMessage("Invalid Argument. Use Case: " + TestBot.PREFIX + Constants.CLEAR + " <int>").queue();
                    }
                    break;
                case Constants.ADD:
                    try
                    {
                        String arg1 = args[1];
                        String arg2 = args[2];
                        addCommand(event, arg1, arg2);
                    }
                    catch (Exception e)
                    {
                        event.getChannel().sendMessage("Invalid Arguments. Use Case: -" + Constants.ADD + " <num> <num>").queue();
                    }
                    break;
                case Constants.SUBTRACT:
                    try
                    {
                        String arg1 = args[1];
                        String arg2 = args[2];
                        subtractCommand(event, arg1, arg2);
                    }
                    catch (Exception e)
                    {
                        event.getChannel().sendMessage("Invalid Arguments. Use Case: -" + Constants.SUBTRACT + " <num> <num>").queue();
                    }
                    break;
                case Constants.MULTIPLY:
                    try
                    {
                        String arg1 = args[1];
                        String arg2 = args[2];
                        multiplyCommand(event, arg1, arg2);
                    }
                    catch (Exception e)
                    {
                        event.getChannel().sendMessage("Invalid Arguments. Use Case: -" + Constants.MULTIPLY + " <num> <num>").queue();
                    }
                    break;
                case Constants.DIVIDE:
                    try
                    {
                        String arg1 = args[1];
                        String arg2 = args[2];
                        divideCommand(event, arg1, arg2);
                    }
                    catch (Exception e)
                    {
                        event.getChannel().sendMessage("Invalid Arguments. Use Case: -" + Constants.DIVIDE + " <num> <num>").queue();
                    }
                    break;
                case Constants.SQUARE:
                    try
                    {
                        String arg = args[1]; // the number being squared
                        squareCommand(event, arg);
                    }
                    catch (Exception e)
                    {
                        event.getChannel().sendMessage("Invalid Arguments. Use Case: -" + Constants.SQUARE + " <num>").queue();
                    }
                default: // does nothing
                    break;
            }
        }
    }

    /**
     * Clears the last arg messages.
     * @param arg   number of messages to delete.
     */
    public void clearCommand(MessageReceivedEvent event, String arg)
    {
        try
        {
            int num = Integer.parseInt(arg);
            System.out.println("Clearing " + num + " messages");
            List<Message> messages = event.getChannel().getHistory().retrievePast(num).complete(); // returns a list of the num recent messages
            event.getTextChannel().deleteMessages(messages).queue();
            event.getChannel().sendMessage(num + " messages cleared!").queue();
        }
        catch (IllegalArgumentException ex)
        {
            event.getChannel().sendMessage(ex.toString()).queue();
        }
        catch (Exception e)
        {
            event.getChannel().sendMessage("Invalid Argument. Use Case: " + TestBot.PREFIX + Constants.CLEAR + " <int>").queue();
        }
    }

    /**
     * Spams message "hi haha hi haha" arg number of times
     * @param arg   number of times to send the message
     */
    public void spamCommand(MessageReceivedEvent event, String arg)
    {
        try
        {
            int num = Integer.parseInt(arg);
            System.out.println("Spamming " + num + " messages");
            for (int i = 0; i < num; i++)
            {
                event.getChannel().sendMessage("hi haha hi haha").queue();
            }
        }
        catch (Exception e)
        {
            event.getChannel().sendMessage("Invalid Argument. Use Case: " + TestBot.PREFIX + Constants.SPAM + " <int>").queue();
        }
    }

    /**
     * Squares a number and displays it in the text channel.
     */
    public void squareCommand(MessageReceivedEvent event, String arg)
    {
        try
        {
            double num = Double.parseDouble(arg);

            double ans = num * num;
            int ansInt = (int) ans;
            if (Math.abs(ans - ansInt) > 0)
            {
                event.getChannel().sendMessage(num + "^2 = " + ans).queue();
            }
            else
            {
                event.getChannel().sendMessage(((int) num) + "^2 = " + ansInt).queue();
            }
        }
        catch (Exception e)
        {
            System.out.println("Square Command error");
            event.getChannel().sendMessage("Invalid Arguments. Use Case: -" + Constants.SQUARE + " <num>").queue();
        }
    }

    /**
     * Executes the divide command. Determines if the arguments are valid numbers, then divides them.
     */
    public void divideCommand(MessageReceivedEvent event, String arg1, String arg2)
    {
        try
        {
            double double1 = Double.parseDouble(arg1);
            double double2 = Double.parseDouble(arg2);
            divide(event, double1, double2);
        }
        catch (Exception e)
        {
            System.out.println("Divide Command error");
            event.getChannel().sendMessage("Invalid Arguments. Use Case: -" + Constants.DIVIDE + "<num> <num>").queue();
        }
    }

    /**
     * Divides double parameters arg1 and arg2.
     */
    public void divide(MessageReceivedEvent event, double arg1, double arg2)
    {
        double ans = arg1 / arg2;
        event.getChannel().sendMessage(arg1 + " / " + arg2 + " = " + ans).queue();
        System.out.println("Divide Command Executed");
    }


    /**
     * Executes the multiply command. Determines if the arguments are valid numbers, then multiplies them.
     */
    public void multiplyCommand(MessageReceivedEvent event, String arg1, String arg2)
    {
        try
        {
            double double1 = Double.parseDouble(arg1);
            double double2 = Double.parseDouble(arg2);
            int int1 = (int) double1; // cast to int, to check if input has decimals
            int int2 = (int) double2;
            if (Math.abs(double1 - int1) > 0 || Math.abs(double2 - int2) > 0) multiply(event, double1, double2);
            else multiply(event, int1, int2);
        }
        catch (Exception e)
        {
            System.out.println("Multiply Command error");
            event.getChannel().sendMessage("Invalid Arguments. Use Case: -" + Constants.MULTIPLY + "<num> <num>").queue();
        }
    }

    /**
     * Multiplies two double type parameters.
     */
    public void multiply(MessageReceivedEvent event, double arg1, double arg2)
    {
        double ans = arg1 * arg2;
        event.getChannel().sendMessage(arg1 + " * " + arg2 + " = " + ans).queue();
        System.out.println("Multiply Command Executed (double)");
    }

    /**
     * Multiplies two int parameters.
     */
    public void multiply(MessageReceivedEvent event, int arg1, int arg2)
    {
        int ans = arg1 * arg2;
        event.getChannel().sendMessage(arg1 + " * " + arg2 + " = " + ans).queue();
        System.out.println("Multiply Command Executed (int)");
    }

    /**
     * Executes the subtract command. Determines if the arguments are valid numbers, and then subtracts them.
     */
    public void subtractCommand(MessageReceivedEvent event, String arg1, String arg2)
    {
        try
        {
            double double1 = Double.parseDouble(arg1);
            double double2 = Double.parseDouble(arg2);
            int int1 = (int) double1; // casting to int versions, then subtracting int from double to check if input has a decimal or not.
            int int2 = (int) double2;
            if (Math.abs(double1 - int1) > 0 || Math.abs(double2 - int2) > 0) subtract(event, double1, double2); // if double
            else subtract(event, int1, int2); // else int
        }
        catch (Exception e)
        {
            System.out.println("Subtract command error");
            event.getChannel().sendMessage("Invalid Arguments. Use Case: -" + Constants.ADD + " <num> <num>").queue();
        }
    }

    /**
     * Subtracts doubles. arg1 - arg2. Displays result.
     */
    public void subtract(MessageReceivedEvent event, double arg1, double arg2)
    {
        double diff = arg1 - arg2;
        event.getChannel().sendMessage(arg1 + " - " + arg2 + " = " + diff).queue();
        System.out.println("Subtract Command Executed (double)");
    }

    /**
     * Subtracts int. arg1 - arg2. Displays result.
     */
    public void subtract(MessageReceivedEvent event, int arg1, int arg2)
    {
        int diff = arg1 - arg2;
        event.getChannel().sendMessage(arg1 + " - " + arg2 + " = " + diff).queue();
        System.out.println("Subtract Command Executed (int)");
    }

    /**
     * Executes the add command and determines whether the arguments are valid, and then adds them by
     * calling the add function for either ints or doubles.
     */
    public void addCommand(MessageReceivedEvent event, String arg1, String arg2)
    {
        try
        {
            double double1 = Double.parseDouble(arg1);
            double double2 = Double.parseDouble(arg2);
            int int1 = (int) double1; // casting to int versions, then subtracting int from double to check if input has a decimal or not.
            int int2 = (int) double2;
            if (Math.abs(double1 - int1) > 0 || Math.abs(double2 - int2) > 0) add(event, double1, double2); // if double
            else add(event, int1, int2); // else int
        }
        catch (Exception e)
        {
            System.out.println("Add command error");
            event.getChannel().sendMessage("Invalid Arguments. Use Case: -" + Constants.ADD + " <num> <num>").queue();
        }
    }
    /**
     * Adds two doubles and displays the value.
     */
    public void add(MessageReceivedEvent event, double arg1, double arg2)
    {
        double sum = arg1 + arg2;
        event.getChannel().sendMessage(arg1 + " + " + arg2 + " = " + sum).queue();
        System.out.println("Add Command Executed (double)");
    }

    /**
     * Adds two ints and displays the value.
     */
    public void add(MessageReceivedEvent event, int arg1, int arg2)
    {
        int sum = arg1 + arg2;
        event.getChannel().sendMessage(arg1 + " + " + arg2 + " = " + sum).queue();
        System.out.println("Add Command Executed (int)");
    }

    /**
     * Greets the user when the user says hi to them.
     */
    public void greetingCommand(MessageReceivedEvent event)
    {
        User author = event.getAuthor();
        Member member = event.getMember();

        String name;
        if (event.getMessage().isWebhookMessage()) // if this is a Webhook message, then there is no Member associated with the User, thus we default to the author for the name
            name = author.getName();
        else
        {
            name = member.getNickname();
            System.out.println("getNickname method used: " + name);
            if (name == null)
            {
                name = member.getEffectiveName();
                System.out.println("getEffectiveName used: " + name);
            }
        }

        event.getChannel().sendTyping().queue();
        event.getChannel().sendMessage("hi " + name + "!").queue();
        System.out.println("hi command finished");
    }

    /**
     * Displays information about the bot.
     */
    public void infoCommand(MessageReceivedEvent event)
    {
        EmbedBuilder info = new EmbedBuilder();
        info.setTitle(TestBot.BOT_NAME + " Information 😎");
        info.setDescription("Just a simple bot that does stuff");
        info.addField("Creator", "Luis Perez", false);
        info.setImage("https://static.wikia.nocookie.net/kimetsu-no-yaiba/images/8/8a/Kyojuro%27s_introduction_%28anime%29.png/revision/latest/scale-to-width-down/1000?cb=20190831164755.png");
        info.setColor(Constants.HOT_PINK);
        info.setThumbnail("https://images-na.ssl-images-amazon.com/images/I/71H4GD0XjDL._SX355_.jpg");
        info.setFooter("hi :)", event.getMember().getUser().getAvatarUrl());
        event.getChannel().sendMessageEmbeds(info.build()).queue();
        info.clear();
        System.out.println("Info displayed");
    }

    /**
     * Displays a list of commands for the user to use.
     */
    public void helpCommand(MessageReceivedEvent event)
    {
        EmbedBuilder help = new EmbedBuilder( );
        help.setTitle(TestBot.BOT_NAME + " Commands");
        help.setDescription(Constants.CMD_LIST);
        help.setColor(Constants.HOT_PINK);

        event.getChannel().sendMessageEmbeds(help.build()).queue();
        help.clear();
        System.out.println("Help command executed");
    }

    /**
     * Shows the user's avatar, "exposing" their profile picture.
     */
    public void exposeCommand(MessageReceivedEvent event)
    {
        String url = event.getAuthor().getAvatarUrl();
        EmbedBuilder show = new EmbedBuilder();
        show.setTitle("Look, it's you!");
        show.setImage(url);

        event.getChannel().sendMessageEmbeds(show.build()).queue();
        show.clear();
        System.out.println("expose command executed");
    }

    /**
     * Join the voice channel that the user is in.
     */
    public void joinVoiceChannelCommand(MessageReceivedEvent event)
    {
        if (!event.getGuild().getSelfMember().hasPermission((GuildChannel) event.getChannel(), Permission.VOICE_CONNECT))
        {
            event.getChannel().sendMessage("I can't join the voice channel").queue();
            return;
        }

        VoiceChannel voice = (VoiceChannel) event.getMember().getVoiceState().getChannel();
        if (voice == null) {
            event.getChannel().sendMessage("You must join a voice channel for me to join").queue();
            return;
        }

        AudioManager audioManager = event.getGuild().getAudioManager();
        audioManager.openAudioConnection(voice);
    }

    /**
     * Leaves the voice channel that it's in.
     */
    public void leaveVoiceChannelCommand(MessageReceivedEvent event)
    {
        VoiceChannel voice = (VoiceChannel) event.getGuild().getSelfMember().getVoiceState().getChannel();

        if (voice == null)
        {
            event.getChannel().sendMessage("I am not in a voice channel").queue();
            return;
        }
        event.getGuild().getAudioManager().closeAudioConnection();
        event.getChannel().sendMessage("Left the channel").queue();
    }

    /**
     * Gets the bitrate of the voice channel it is in.
     */
    public void getBitrateCommand(MessageReceivedEvent event)
    {
        try
        {
            VoiceChannel voice = (VoiceChannel) event.getMember().getVoiceState().getChannel();

            AudioManager audioManager = event.getGuild().getAudioManager();
            int bitrate = audioManager.getConnectedChannel().getBitrate();

            bitrate /= 1000;

            event.getChannel().sendMessage("Voice Channel bitrate: " + bitrate + " kbps").queue();
        }
        catch (NullPointerException e)
        {
            event.getChannel().sendMessage("I must be in a voice channel").queue();
        }
        catch (Exception ex){
            event.getChannel().sendMessage("Unexpected error").queue();
        }
    }

    /**
     * Prints a random animal fact to the channel.
     */
    public void animalFactCommand(MessageReceivedEvent event)
    {
        int index = Constants.random.nextInt(Constants.animalFacts.length); // generates a random number within the size of the array containing the animal facts

        event.getChannel().sendMessage("**Animal Fact**: " + Constants.animalFacts[index]).queue();
        System.out.println("# of animal facts: " + Constants.animalFacts.length);
    }

}