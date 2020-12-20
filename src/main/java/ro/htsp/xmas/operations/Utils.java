package ro.htsp.xmas.operations;

public class Utils {

    public static int sixBitSigned(int value) {
        if ((value & 0b100_000) == 0b100_000)
            return -((~value + 1) & 0b111_111);
        return value;
    }

    public static int twelveBitSigned(int value) {
        if ((value & 0b100_000_000_000) == 0b100_000_000_000)
            return -((~value + 1) & 0b111_111_111_111);
        return value;
    }
}
