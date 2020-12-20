package ro.htsp.xmas.machine;

import ro.htsp.xmas.operations.Utils;

import java.util.Arrays;

public class RAM {
    private static final byte[] slots = new byte[64];

    static {
        reset();
    }

    public static void reset() {
        Arrays.fill(slots, (byte) 0);
    }

    public static void write(int slot, int value) {
        if (slot != 0)
            slots[slot] = (byte) (value & 0b111111);
    }

    public static byte read(int slot) {
        return slots[slot];
    }

    public static int readSigned(int slot) {
        return Utils.sixBitSigned(slots[slot]);
    }
}
