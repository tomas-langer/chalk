package com.github.tomaslanger.chalk;

/**
 * ANSI codes for modification of console output.
 * <p>
 * User: tomas.langer
 * Date: 3.12.2015
 * Time: 14:42
 *
 * @author Tomas Langer (tomas.langer@gmail.com)
 */
public final class Ansi {
    private Ansi() {}

    /**
     * Common methods for all types of Ansi modifications.
     */
    public interface AnsiCode {
        int getBeginInt();

        int getEndInt();

        default String getStart() {
            return "\u001b[" + getBeginInt() + "m";
        }

        default String getEnd() {
            return "\u001b[" + getEndInt() + "m";
        }
    }

    /**
     * Modifiers of output that are not related to color.
     * Only kept the ones that work on most environments.
     */
    public enum Modifier implements AnsiCode {
        BOLD(1, 22), // 21 isn't widely supported and 22 does the same thing
        UNDERLINE(4, 24),
        INVERSE(7, 27);

        final int begin;
        final int end;

        Modifier(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }


        @Override
        public int getBeginInt() {
            return begin;
        }

        @Override
        public int getEndInt() {
            return end;
        }
    }

    /**
     * Foreground colors.
     */
    public enum Color implements AnsiCode {
        BLACK(30),
        RED(31),
        GREEN(32),
        YELLOW(33),
        BLUE(34),
        MAGENTA(35),
        CYAN(36),
        WHITE(37),
        GRAY(90),
        GREY(90);

        final int begin;
        final int end = 39;


        Color(int begin) {
            this.begin = begin;
        }


        @Override
        public int getBeginInt() {
            return begin;
        }

        @Override
        public int getEndInt() {
            return end;
        }
    }

    /**
     * Background colors.
     */
    public enum BgColor implements AnsiCode {
        BLACK(40),
        RED(41),
        GREEN(42),
        YELLOW(43),
        BLUE(44),
        MAGENTA(45),
        CYAN(46),
        WHITE(47);

        final int begin;
        final int end = 49;


        BgColor(int begin) {
            this.begin = begin;
        }


        @Override
        public int getBeginInt() {
            return begin;
        }

        @Override
        public int getEndInt() {
            return end;
        }
    }


    public static String cursorUp() {
        return cursorUp(1);
    }

    public static String cursorUp(int rows) {
        return escape('A', rows);
    }

    public static String cursorDown() {
        return cursorDown(1);
    }

    public static String cursorDown(int rows) {
        return escape('B', rows);
    }

    public static String cursorRight() {
        return cursorRight(1);
    }

    public static String cursorRight(int cols) {
        return escape('C', cols);
    }

    public static String cursorLeft() {
        return cursorLeft(1);
    }

    public static String cursorLeft(int cols) {
        return escape('D', cols);
    }

    public static String setCursorPosition(int x, int y) {
        return escape('H', y, x);
    }

    public static String eraseScreen() {
        return escape('J', 2);
    }

    /**
     * Erase from cursor down
     */
    public static String eraseScreenDown() {
        return escape('J', 0);
    }

    /**
     * Erase from cursor up
     */
    public static String eraseScreenUp() {
        return escape('J', 1);
    }

    public static String eraseLine() {
        return escape('K', 2);
    }

    /**
     * From line start to cursor
     */
    public static String eraseLineStart() {
        return escape('K', 1);
    }

    /**
     * From cursor to line end
     */
    public static String eraseLineEnd() {
        return escape('K', 0);
    }


    private static String escape(char command, int... options) {
        StringBuilder sb = new StringBuilder(6);
        sb.append('\u001b');
        sb.append('[');
        optionsToValue(sb, options);
        sb.append(command);

        return sb.toString();
    }

    private static void optionsToValue(final StringBuilder sb, final int[] options) {
        if (options.length == 1) {
            sb.append(options[0]);
        } else {
            sb.append(options[0]);
            for (int i = 1; i < options.length; i++) {
                sb.append(';');
                sb.append(options[i]);
            }
        }
    }
}