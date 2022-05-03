package bot;

import java.util.Random;

/**
 * @author Luis Perez
 */
public class Constants
{
    public static final int HOT_PINK = 0xf707f3; // hex value for hot pink

    public static Random random = new Random();

    public static final String CMD_LIST = "-hi\n" +
            "-info\n" +
            "-expose\n" +
            "-join\n" +
            "-leave\n" +
            "-bitrate\n" +
            "-animal fact\n" +
            "-spam\n" +
            "-clear\n" +
            "-add\n" +
            "-subtract\n" +
            "-multiply\n" +
            "-divide\n" +
            "-square";

    /*
    Facts taken from; https://www.thefactsite.com/300-random-animal-facts/
     */
    public static final String[] animalFacts = {"Gorillas can catch human colds and other illnesses",
    "A newborn Chinese water deer is so small it can almost be held in the palm of the hand",
    "Ostriches can run faster than horses, and the males can roar like lions",
    "A lion in the wild usually makes no more than twenty kills a year",
    "The female lion does ninety percent of the hunting",
    "The world’s smallest dog was a Yorkshire Terrier, which weighed just four ounces",
    "Turtles, water snakes, crocodiles, alligators, dolphins, whales, and other water going creatures will drown if kept underwater too long",
    "Almost half the pigs in the world are kept by farmers in China",
    "On average, dogs have better eyesight than humans, although not as colorful",
    "Deer have no gall bladders",
    "There is an average of 50,000 spiders per acre in green areas",
    "Snakes are carnivores, which means they only eat animals, often small ones such as insects, birds, frogs and other small mammals",
    "In Alaska it is illegal to whisper in someone’s ear while they’re moose hunting",
    "The bat is the only mammal that can fly",
    "The leg bones of a bat are so thin that out of the 1,200 species of bats, only 2 can walk on ground. These are the Vampire bat and the Burrowing bat",
    "Some male songbirds sing more than 2,000 times each day",
    "The only mammals to undergo menopause are elephants, humpback whales and human females",
    "Blue-eyed lemurs are one of two (non-human) primates to have truly blue eyes",
    "A tarantula spider can survive for more than two years without food",
    "For every human in the world there are one million ants"};

    /*
    List of commands
     */
    public static final String GREETING = "hi";
    public static final String INFO = "info";
    public static final String HELP = "help";
    public static final String EXPOSE = "expose";
    public static final String JOIN = "join";
    public static final String LEAVE = "leave";
    public static final String BITRATE = "bitrate";
    public static final String ANIMAL = "animal";
    public static final String ANIMAL_FACT = "fact";
    public static final String SPAM = "spam";
    public static final String CLEAR = "clear";
    /*
    Math stuff
     */
    public static final String ADD = "add";
    public static final String SUBTRACT = "subtract";
    public static final String MULTIPLY = "multiply";
    public static final String DIVIDE = "divide";
    public static final String SQUARE = "square";
}
