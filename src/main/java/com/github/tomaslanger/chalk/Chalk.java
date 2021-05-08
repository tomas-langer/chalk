package com.github.tomaslanger.chalk;

import org.fusesource.jansi.AnsiConsole;

/**
 * Chalk allows you to color output going to console.
 * To start, use {@link #on(String)} and then chain modification.
 * Obviously one background and one foreground color may be used (such as {@link #red()} and {@link #bgRed()},
 * in addition one or more modifiers may be used ({@link #bold()}, {@link #underline()}, {@link #inverse()}).
 *
 * <code>
 *     System.out.println("This message is " + Chalk.on("IMPORTANT").red().underline());
 * </code>
 *
 * User: tomas.langer
 * Date: 3.12.2015
 * Time: 12:23
 *
 * @author Tomas Langer (tomas.langer@gmail.com)
 *
 * @see #on(String)
 */
public class Chalk {
    private static boolean colorEnabled;

    static {
        try {
            AnsiConsole.systemInstall();
            colorEnabled = AnsiConsole.isInstalled();
        } catch (UnsatisfiedLinkError e) {
            //this is some kind of non-windoze system, assume support.
            colorEnabled = !Boolean.getBoolean("jansi.strip");
            //assume this is a console and command is enabled
            //TODO make the check here again against Hudson, Jenkins, Idea etc -> externalize to a class that
            //cannot fail initialization
        }
    }

    private String text;

    private Chalk(final String text) {
        this.text = text;
    }

    /**
     * Start with this method, to chalk some text.
     *
     * @param text text to chalk
     * @return a new chalk instance, that can be modified through color or modifier methods
     */
    public static Chalk on(final String text) {
        //perf optimization - if not enabled, just do not escape (as it has to be de-escaped)
        String theText = (null == text ? "null" : text);

        if (colorEnabled) {
            return new Chalk(theText);
        } else {
            return new NoOpChalk(theText);
        }
    }

    /**
     * Whether coloring is enabled.
     *
     * @return if coloring is enabled
     */
    public static boolean isColorEnabled() {
        return colorEnabled;
    }

    /**
     * Explicitly enable/disable coloring,
     * even when the automated discovery method may think it should be disabled/enabled.
     *
     * @param colorEnabled whether to have color enabled
     */
    public static synchronized void setColorEnabled(final boolean colorEnabled) {
        Chalk.colorEnabled = colorEnabled;
    }

    /**
     * Either use one of the explicit methods ({@link #red()}, {@link #bold()}, {@link #bgGreen()} etc.), or explicitly invoke
     * this apply method.
     *
     * @param modifier Modifier - either one of the enumerations in {@link Ansi} or a custom modifier (not recommended).
     *
     * @return Chalk instance to chain commands.
     */
    public Chalk apply(Ansi.AnsiCode modifier) {
        this.text = modifier.getStart() + text + modifier.getEnd();

        return this;
    }

    /*
     * Foreground colors
     */

    @Override
    public String toString() {
        return text;
    }

    /**
     * Black color.
     *
     * @return updated instance
     */
    public Chalk black() {
        return apply(Ansi.Color.BLACK);
    }

    /**
     * Red color.
     *
     * @return updated instance
     */
    public Chalk red() {
        return apply(Ansi.Color.RED);
    }

    /**
     * Green color.
     *
     * @return updated instance
     */
    public Chalk green() {
        return apply(Ansi.Color.GREEN);
    }

    /**
     * Yellow color.
     *
     * @return updated instance
     */
    public Chalk yellow() {
        return apply(Ansi.Color.YELLOW);
    }

    /**
     * Blue color.
     *
     * @return updated instance
     */
    public Chalk blue() {
        return apply(Ansi.Color.BLUE);
    }

    /**
     * Magenta color.
     *
     * @return updated instance
     */
    public Chalk magenta() {
        return apply(Ansi.Color.MAGENTA);
    }

    /**
     * Cyan color.
     *
     * @return updated instance
     */
    public Chalk cyan() {
        return apply(Ansi.Color.CYAN);
    }

    /**
     * White color.
     *
     * @return updated instance
     */
    public Chalk white() {
        return apply(Ansi.Color.WHITE);
    }

    /*
     * Modifiers
     */

    /**
     * Careful with gray - not supported on windows!!!
     * Will be black...
     *
     * @return updated instance
     */
    public Chalk gray() {
        return apply(Ansi.Color.GRAY);
    }

    /**
     * Bold text.
     *
     * @return updated instance
     */
    public Chalk bold() {
        return apply(Ansi.Modifier.BOLD);
    }

    /**
     * Inverse colors.
     *
     * @return updated instance
     */
    public Chalk inverse() {
        return apply(Ansi.Modifier.INVERSE);
    }


    /*
     * Background colors
     */

    /**
     * Will be highlighted in windows, not underlined.
     *
     * @return updated instance
     */
    public Chalk underline() {
        return apply(Ansi.Modifier.UNDERLINE);
    }

    /**
     * Set background color to black.
     *
     * @return updated instance
     */
    public Chalk bgBlack() {
        return apply(Ansi.BgColor.BLACK);
    }

    /**
     * Set background color to red.
     *
     * @return updated instance
     */
    public Chalk bgRed() {
        return apply(Ansi.BgColor.RED);
    }

    /**
     * Set background color to green.
     *
     * @return updated instance
     */
    public Chalk bgGreen() {
        return apply(Ansi.BgColor.GREEN);
    }

    /**
     * Set background color to yellow.
     *
     * @return updated instance
     */
    public Chalk bgYellow() {
        return apply(Ansi.BgColor.YELLOW);
    }

    /**
     * Set background color to blue.
     *
     * @return updated instance
     */
    public Chalk bgBlue() {
        return apply(Ansi.BgColor.BLUE);
    }

    /**
     * Set background color to magenta.
     *
     * @return updated instance
     */
    public Chalk bgMagenta() {
        return apply(Ansi.BgColor.MAGENTA);
    }

    /**
     * Set background color to cyan.
     *
     * @return updated instance
     */
    public Chalk bgCyan() {
        return apply(Ansi.BgColor.CYAN);
    }

    /**
     * Set background color to white.
     *
     * @return updated instance
     */
    public Chalk bgWhite() {
        return apply(Ansi.BgColor.WHITE);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Chalk)) {
            return false;
        }

        Chalk chalk = (Chalk) o;

        return text.equals(chalk.text);

    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    private static class NoOpChalk extends Chalk {
        public NoOpChalk(final String text) {
            super(text);
        }

        @Override
        public Chalk apply(final Ansi.AnsiCode modifier) {
            return this;
        }
    }
}
