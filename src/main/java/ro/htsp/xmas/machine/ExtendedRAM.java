package ro.htsp.xmas.machine;

import java.util.Arrays;

public class ExtendedRAM implements Device {
    public static final ExtendedRAM INSTANCE = new ExtendedRAM();

    private int memoryPointer = 0;
    private final byte[] memory = new byte[0b111_111_111_111_111_111 + 1];

    public ExtendedRAM() {
        Arrays.fill(memory, (byte)0);
    }

    private void incrementMemoryPointer() {
        memoryPointer = memoryPointer + 1 % memory.length;
    }

    @Override
    public void write(int value) {
        memory[memoryPointer] = (byte)(value & 0b111_111);
        incrementMemoryPointer();
    }

    @Override
    public int read() {
        int value = memory[memoryPointer];
        incrementMemoryPointer();
        return value & 0b111_111;
    }

    public void writeLow(int value) {
        memoryPointer = (memoryPointer & 0b111_111_111_111_000_000) | (value & 0b111_111);
    }

    public void writeMiddle(int value) {
        memoryPointer = (memoryPointer & 0b111_111_000_000_111_111) | ((value & 0b111_111) << 6);
    }

    public void writeHigh(int value) {
        memoryPointer = (memoryPointer & 0b000_000_111_111_111_111) | ((value & 0b111_111) << 12);
    }

}
