package ro.htsp.xmas.machine;

public class Utils {
    private static final String CHARACTER_MAP = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ +-*/<=>()[]{}#$_?|^&!~,.:\n\u0000";

    public static char toChar(int value) {
        return CHARACTER_MAP.charAt(value);
    }

    public static int toInteger(char value) {
        return CHARACTER_MAP.indexOf(value);
    }
}
