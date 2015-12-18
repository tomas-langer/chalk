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
 */
public class Chalk {
    private static boolean colorEnabled;

    static {
        try {
            AnsiConsole.systemInstall();
            colorEnabled = AnsiConsole.isColorEnabled();
        } catch (UnsatisfiedLinkError e) {
            //this is some kind of non-windoze system, assume support.
            colorEnabled = !Boolean.getBoolean("jansi.strip");
        }
    }

    private String text;

    private Chalk(final String text) {
        this.text = text;
    }

    public static Chalk on(final String text) {
        //perf optimization - if not enabled, just do not escape (as it has to be de-escaped)
        String theText = (null == text?"null":text);


        if (colorEnabled) {
            return new Chalk(theText);
        }else {
            return new NoOpChalk(theText);
        }
    }

    public static synchronized void setColorEnabled(final boolean colorEnabled) {
        Chalk.colorEnabled = colorEnabled;
    }

    /**
     * Either use one of the explicit methods ({@link #red()}, {@link #bold()}, {@link #bgGreen()} etc.), or explicitly invoke this apply method.
     *
     * @param modifier Modifier - either one of the enumerations in {@link Ansi} or a custom modifier (not recommended).
     *
     * @return Chalk instance to chain commands.
     */
    public Chalk apply(Ansi.AnsiCode modifier) {
        this.text = modifier.getStart() + text + modifier.getEnd();

        return this;
    }

    @Override
    public String toString() {
        return text;
    }

    /*
     * Foreground colors
     */

    public Chalk black() {
        return apply(Ansi.Color.BLACK);
    }

    public Chalk red() {
        return apply(Ansi.Color.RED);
    }

    public Chalk green() {
        return apply(Ansi.Color.GREEN);
    }

    public Chalk yellow() {
        return apply(Ansi.Color.YELLOW);
    }
    public Chalk blue() {
        return apply(Ansi.Color.BLUE);
    }

    public Chalk magenta() {
        return apply(Ansi.Color.MAGENTA);
    }
    public Chalk cyan() {
        return apply(Ansi.Color.CYAN);
    }
    public Chalk white() {
        return apply(Ansi.Color.WHITE);
    }

    /**
     * Careful with gray - not supported on windows!!!
     * Will be black...
     */
    public Chalk gray() {
        return apply(Ansi.Color.GRAY);
    }

    /*
     * Modifiers
     */
    public Chalk bold() {
        return apply(Ansi.Modifier.BOLD);
    }
    public Chalk inverse() {return apply(Ansi.Modifier.INVERSE);}

    /**
     * WIll be highlighted in windows, not underlined
     */
    public Chalk underline() {
        return apply(Ansi.Modifier.UNDERLINE);
    }


    /*
     * Background colors
     */

    public Chalk bgBlack() {
        return apply(Ansi.BgColor.BLACK);
    }
    public Chalk bgRed() {
        return apply(Ansi.BgColor.RED);
    }
    public Chalk bgGreen() {
        return apply(Ansi.BgColor.GREEN);
    }
    public Chalk bgYellow() {
        return apply(Ansi.BgColor.YELLOW);
    }
    public Chalk bgBlue() {
        return apply(Ansi.BgColor.BLUE);
    }
    public Chalk bgMagenta() {
        return apply(Ansi.BgColor.MAGENTA);
    }
    public Chalk bgCyan() {
        return apply(Ansi.BgColor.CYAN);
    }
    public Chalk bgWhite() {
        return apply(Ansi.BgColor.WHITE);
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Chalk)) return false;

        Chalk chalk = (Chalk) o;

        return text.equals(chalk.text);

    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }
}
