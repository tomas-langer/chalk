package com.github.tomaslanger;

import java.io.UnsupportedEncodingException;

/**
 * Class to show what this tool can do. 
 * User: Tomas.Langer
 * Date: 16.12.2015
 * Time: 13:30
 *
 * @author Tomas Langer (tomas.langer@gmail.com)
 */
public class Features {
    public void showFeatures() {
        //this line is needed to initialize the system (windows).
        Chalk.on("test");

        System.out.println("First test " + Chalk.on("text").green());

        System.out.println("Modifier underline " + Chalk.on("text").apply(Ansi.Modifier.UNDERLINE));
        System.out.println("Modifier bold " + Chalk.on("text").apply(Ansi.Modifier.BOLD));
        System.out.println("Modifier inverse " + Chalk.on("text").apply(Ansi.Modifier.INVERSE));

        System.out.println("FG Black " + Chalk.on("text").apply(Ansi.Color.BLACK));
        System.out.println("FG Red " + Chalk.on("text").apply(Ansi.Color.RED));
        System.out.println("FG Green " + Chalk.on("text").apply(Ansi.Color.GREEN));
        System.out.println("FG Yellow " + Chalk.on("text").apply(Ansi.Color.YELLOW));
        System.out.println("FG Blue " + Chalk.on("text").apply(Ansi.Color.BLUE));
        System.out.println("FG Magenta " + Chalk.on("text").apply(Ansi.Color.MAGENTA));
        System.out.println("FG Cyan " + Chalk.on("text").apply(Ansi.Color.CYAN));
        System.out.println("FG White " + Chalk.on("text").apply(Ansi.Color.WHITE));
        System.out.println("FG Gray " + Chalk.on("text").apply(Ansi.Color.GRAY));
        System.out.println("FG Grey " + Chalk.on("text").apply(Ansi.Color.GREY));


        System.out.println("BG Blue " + Chalk.on("text").apply(Ansi.BgColor.BLUE));
        System.out.println("BG Black " + Chalk.on("text").apply(Ansi.BgColor.BLACK));
        System.out.println("BG Red " + Chalk.on("text").apply(Ansi.BgColor.RED));
        System.out.println("BG Green " + Chalk.on("text").apply(Ansi.BgColor.GREEN));
        System.out.println("BG Yellow " + Chalk.on("text").apply(Ansi.BgColor.YELLOW));
        System.out.println("BG Blue " + Chalk.on("text").apply(Ansi.BgColor.BLUE));
        System.out.println("BG Magenta " + Chalk.on("text").apply(Ansi.BgColor.MAGENTA));
        System.out.println("BG Cyan " + Chalk.on("text").apply(Ansi.BgColor.CYAN));
        System.out.println("BG White " + Chalk.on("text").apply(Ansi.BgColor.WHITE));

        System.out.println("Combinations:");
        System.out.println("System.out.println(\"This message is \" + Chalk.on(\"IMPORTANT\").red().underline());");
        System.out.println("This message is " + Chalk.on("IMPORTANT").red().underline());
        System.out.println("System.out.println(\"My \" + Chalk.on(\"life\").bgBlue().white().bold() + \" is good\");");
        System.out.println("My " + Chalk.on("life").bgBlue().white().bold() + " is good");
        System.out.println("System.out.println(\"More \" + Chalk.on(\"burgers\").yellow().inverse() + \" on \" + Chalk.on(\"the way\").bgGreen().yellow());");
        System.out.println("More " + Chalk.on("burgers").yellow().inverse() + " on " + Chalk.on("the way").bgGreen().yellow());
    }

    

    public static void main(String[] args) throws UnsupportedEncodingException {
        Features chalkTest = new Features();
        chalkTest.showFeatures();
    }
}
