package com.github.tomaslanger;

import java.io.UnsupportedEncodingException;

/**
 * Test class to show what this tool can do. Remove asap.
 * Now kept for Idea bug reproducer for https://youtrack.jetbrains.com/issue/IDEA-149450
 * User: Tomas.Langer
 * Date: 16.12.2015
 * Time: 13:30
 *
 * @author Tomas Langer (tomas.langer@gmail.com)
 */
@Deprecated
public class Test {
    public void testEscape() {
        checkAndPrint("FG blue", "\u001B[34mtext\u001B[39m", Chalk.on("text").blue().toString());
        checkAndPrint("FG blue surrounded", "Green: \u001B[32mtext\u001B[39m, and normal", "Green: " + Chalk.on("text").green() + ", and normal");
        checkAndPrint("BG red", "Back: \u001B[41mtext\u001B[49m, and normal", "Back: " + Chalk.on("text").bgRed() + ", and normal");
        checkAndPrint("FG magenta underlined", "Combined \u001B[4m\u001B[35mtext\u001B[39m\u001B[24m, and normal", "Combined " + Chalk.on("text").magenta().underline() + ", and normal");

        checkAndPrint("BG white, FG grey", Chalk.on("Gray text with white background").gray().bgWhite().toString(), "");
    }

    private void checkAndPrint(final String message, final String expected, final String actual) {
        System.out.println(message + ": " + expected + " : " + actual);
    }

    public void testApply() {
        checkAndPrint("Modifier underline", Chalk.on("text").apply(Ansi.Modifier.UNDERLINE), Chalk.on("text").underline());
        checkAndPrint("Modifier bold", Chalk.on("text").apply(Ansi.Modifier.BOLD), Chalk.on("text").bold());
        checkAndPrint("Modifier inverse", Chalk.on("text").apply(Ansi.Modifier.INVERSE), Chalk.on("text").inverse());

        checkAndPrint("FG Black", Chalk.on("text").apply(Ansi.Color.BLACK), Chalk.on("text").black());
        checkAndPrint("FG Red", Chalk.on("text").apply(Ansi.Color.RED), Chalk.on("text").red());
        checkAndPrint("FG Green", Chalk.on("text").apply(Ansi.Color.GREEN), Chalk.on("text").green());
        checkAndPrint("FG Yellow", Chalk.on("text").apply(Ansi.Color.YELLOW), Chalk.on("text").yellow());
        checkAndPrint("FG Blue", Chalk.on("text").apply(Ansi.Color.BLUE), Chalk.on("text").blue());
        checkAndPrint("FG Magenta", Chalk.on("text").apply(Ansi.Color.MAGENTA), Chalk.on("text").magenta());
        checkAndPrint("FG Cyan", Chalk.on("text").apply(Ansi.Color.CYAN), Chalk.on("text").cyan());
        checkAndPrint("FG White", Chalk.on("text").apply(Ansi.Color.WHITE), Chalk.on("text").white());
        checkAndPrint("FG Gray", Chalk.on("text").apply(Ansi.Color.GRAY), Chalk.on("text").gray());
        checkAndPrint("FG Grey", Chalk.on("text").apply(Ansi.Color.GREY), Chalk.on("text").gray());


        checkAndPrint("BG Blue", Chalk.on("text").apply(Ansi.BgColor.BLUE), Chalk.on("text").bgBlue());
        checkAndPrint("BG Black", Chalk.on("text").apply(Ansi.BgColor.BLACK), Chalk.on("text").bgBlack());
        checkAndPrint("BG Red", Chalk.on("text").apply(Ansi.BgColor.RED), Chalk.on("text").bgRed());
        checkAndPrint("BG Green", Chalk.on("text").apply(Ansi.BgColor.GREEN), Chalk.on("text").bgGreen());
        checkAndPrint("BG Yellow", Chalk.on("text").apply(Ansi.BgColor.YELLOW), Chalk.on("text").bgYellow());
        checkAndPrint("BG Blue", Chalk.on("text").apply(Ansi.BgColor.BLUE), Chalk.on("text").bgBlue());
        checkAndPrint("BG Magenta", Chalk.on("text").apply(Ansi.BgColor.MAGENTA), Chalk.on("text").bgMagenta());
        checkAndPrint("BG Cyan", Chalk.on("text").apply(Ansi.BgColor.CYAN), Chalk.on("text").bgCyan());
        checkAndPrint("BG White", Chalk.on("text").apply(Ansi.BgColor.WHITE), Chalk.on("text").bgWhite());
    }

    private void checkAndPrint(final String message, final Chalk expected, final Chalk actual) {
        System.out.println(message + ": " + expected + " : " + actual);
    }

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws UnsupportedEncodingException {
        Test chalkTest = new Test();
        chalkTest.testApply();
        chalkTest.testEscape();
    }
}
