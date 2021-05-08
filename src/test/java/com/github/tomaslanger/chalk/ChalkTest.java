package com.github.tomaslanger.chalk;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Unit test for Chalk.
 *
 * User: Tomas.Langer
 * Date: 16.12.2015
 * Time: 14:59
 *
 * @author Tomas Langer (tomas.langer@gmail.com)
 */
class ChalkTest {
    @BeforeAll
    static void prepare() {
        Chalk.setColorEnabled(true);
    }

    @Test
    void testEscape() {
        checkAndPrint("FG blue", "\u001B[34mtext\u001B[39m", Chalk.on("text").blue().toString());
        checkAndPrint("FG blue surrounded", "Green: \u001B[32mtext\u001B[39m, and normal", "Green: " + Chalk.on("text").green() + ", and normal");
        checkAndPrint("BG red", "Back: \u001B[41mtext\u001B[49m, and normal", "Back: " + Chalk.on("text").bgRed() + ", and normal");
        checkAndPrint("FG magenta underlined", "Combined \u001B[4m\u001B[35mtext\u001B[39m\u001B[24m, and normal", "Combined " + Chalk.on("text").magenta().underline() + ", and normal");
    }

    private void checkAndPrint(final String message, final String expected, final String actual) {
        System.out.println(message + ": " + expected + " : " + actual);
        assertEquals(message, expected, actual);
    }

    @Test
    void testApply() {
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


    @Test
    void testUtilMethods() {
        Chalk a = Chalk.on("text").red();
        Chalk b = Chalk.on("text").red();
        Chalk c = Chalk.on("text").blue();
        Chalk d = Chalk.on("other").blue();

        assertEquals(a, b, "Same text, same modifiers - must be equal");
        assertEquals(a.hashCode(), b.hashCode(), "Same text, same modifiers - must have same hash code");
        assertNotEquals(a, c, "Same text, different modifiers - must differ");
        assertNotEquals(b, c, "Same text, different modifiers - must differ");
        assertNotEquals(c, d, "Different text, same modifiers - must differ");
        assertNotEquals(b, d, "Different text, different modifiers - must differ");
    }

    private void checkAndPrint(final String message, final Chalk expected, final Chalk actual) {
        System.out.println(message + ": " + expected + " : " + actual);
        assertEquals(expected, actual, message);
    }
}