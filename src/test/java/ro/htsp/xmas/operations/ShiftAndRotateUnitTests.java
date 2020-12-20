package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShiftAndRotateUnitTests {

    public static final int SHIFT_LEFT_IMMEDIATE = 0;
    public static final int SHIFT_RIGHT_IMMEDIATE = 8;
    public static final int SHIFT_ARITHMETIC_RIGHT = 16;
    public static final int ROTATE_LEFT_IMMEDIATE = 24;

    @BeforeEach
    void setupTest() {
        RAM.reset();
    }

    @Test
    void testOpCode() {
        assertEquals(10, ShiftAndRotate.INSTANCE.getOpCode());
    }

    @Test
    void logicalShiftLeftImmediate() {
        for (int i = 0; i < 6; i++) {
            RAM.write(1, 0b000001);
            ShiftAndRotate.INSTANCE.execute(2, 1, i + SHIFT_LEFT_IMMEDIATE);
            assertEquals(Math.pow(2, i), RAM.read(2));
        }
        ShiftAndRotate.INSTANCE.execute(2, 1, 6 + SHIFT_LEFT_IMMEDIATE);
        assertEquals(0, RAM.read(2));
    }

    @Test
    void logicalShiftRightImmediate() {
        for (int i = 0; i < 6; i++) {
            RAM.write(1, 0b100000);
            ShiftAndRotate.INSTANCE.execute(2, 1, i + SHIFT_RIGHT_IMMEDIATE);
            assertEquals(Math.pow(2, 5 - i), RAM.read(2));
        }
        ShiftAndRotate.INSTANCE.execute(2, 1, 6 + SHIFT_RIGHT_IMMEDIATE);
        assertEquals(0, RAM.read(2));
    }

    @Test
    void rotateLeftImmediate() {
        RAM.write(1, 0b101010);
        for (int i = 0; i < 8; i++) {
            ShiftAndRotate.INSTANCE.execute(2, 1, i + ROTATE_LEFT_IMMEDIATE);
            assertEquals((i % 2 == 1) ? 0b010101 : 0b101010, RAM.read(2));
        }
    }


    @Test
    void arithmeticShiftRightImmediateNegativeNumber() {
        RAM.write(1, 0b100000);
        ShiftAndRotate.INSTANCE.execute(2, 1, SHIFT_ARITHMETIC_RIGHT);
        assertEquals(0b100000, RAM.read(2));
        ShiftAndRotate.INSTANCE.execute(2, 1, 1 + SHIFT_ARITHMETIC_RIGHT);
        assertEquals(0b110000, RAM.read(2));
        ShiftAndRotate.INSTANCE.execute(2, 1, 2 + SHIFT_ARITHMETIC_RIGHT);
        assertEquals(0b111000, RAM.read(2));
        ShiftAndRotate.INSTANCE.execute(2, 1, 3 + SHIFT_ARITHMETIC_RIGHT);
        assertEquals(0b111100, RAM.read(2));
        ShiftAndRotate.INSTANCE.execute(2, 1, 4 + SHIFT_ARITHMETIC_RIGHT);
        assertEquals(0b111110, RAM.read(2));
        ShiftAndRotate.INSTANCE.execute(2, 1, 5 + SHIFT_ARITHMETIC_RIGHT);
        assertEquals(0b111111, RAM.read(2));
        ShiftAndRotate.INSTANCE.execute(2, 1, 6 + SHIFT_ARITHMETIC_RIGHT);
        assertEquals(0b111111, RAM.read(2));
    }


    @Test
    void arithmeticShiftRightImmediatePositiveNumber() {
        RAM.write(1, 0b010000);
        ShiftAndRotate.INSTANCE.execute(2, 1, SHIFT_ARITHMETIC_RIGHT);
        assertEquals(0b010000, RAM.read(2));
        ShiftAndRotate.INSTANCE.execute(2, 1, 1 + SHIFT_ARITHMETIC_RIGHT);
        assertEquals(0b001000, RAM.read(2));
        ShiftAndRotate.INSTANCE.execute(2, 1, 2 + SHIFT_ARITHMETIC_RIGHT);
        assertEquals(0b000100, RAM.read(2));
        ShiftAndRotate.INSTANCE.execute(2, 1, 3 + SHIFT_ARITHMETIC_RIGHT);
        assertEquals(0b000010, RAM.read(2));
        ShiftAndRotate.INSTANCE.execute(2, 1, 4 + SHIFT_ARITHMETIC_RIGHT);
        assertEquals(0b000001, RAM.read(2));
        ShiftAndRotate.INSTANCE.execute(2, 1, 5 + SHIFT_ARITHMETIC_RIGHT);
        assertEquals(0b000000, RAM.read(2));
        ShiftAndRotate.INSTANCE.execute(2, 1, 6 + SHIFT_ARITHMETIC_RIGHT);
        assertEquals(0b000000, RAM.read(2));
    }


}
