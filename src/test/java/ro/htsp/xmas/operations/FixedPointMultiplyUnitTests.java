package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FixedPointMultiplyUnitTests {

    public static final int UNSIGNED_MULTIPLY = 0;
    public static final int SIGNED_MULTIPLY = 16;

    @BeforeEach
    void setup() {
        RAM.reset();
    }

    @Test
    void testOpCode() {
        assertEquals(19, FixedPointMultiply.INSTANCE.getOpCode());
    }

    @Test
    void unsignedMultiplyTwoPositiveSmallNumbersZeroShift() {
        RAM.write(1, 2);
        RAM.write(2, 3);
        FixedPointMultiply.INSTANCE.execute(1, 2, UNSIGNED_MULTIPLY);
        assertEquals(6, RAM.read(1));
    }

    @Test
    void unsignedMultiplyTwoPositiveSmallNumbersFullShift() {
        RAM.write(1, 2);
        RAM.write(2, 3);
        FixedPointMultiply.INSTANCE.execute(1, 2, UNSIGNED_MULTIPLY + 15);
        assertEquals(0, RAM.read(1));
    }

    @Test
    void signedMultiplyTwoSmallNumbersZeroShift() {
        RAM.write(1, 2);
        RAM.write(2, -3);
        FixedPointMultiply.INSTANCE.execute(1, 2, SIGNED_MULTIPLY);
        assertEquals(-6, RAM.readSigned(1));
    }

    @Test
    void signedMultiplyTwoSmallNumbersFullShift() {
        RAM.write(1, 2);
        RAM.write(2, -3);
        FixedPointMultiply.INSTANCE.execute(1, 2, SIGNED_MULTIPLY + 12);
        assertEquals(-1, RAM.readSigned(1));
    }

    @Test
    void unsignedMultiplyMax() {
        RAM.write(1, 63);
        RAM.write(2, 63);
        FixedPointMultiply.INSTANCE.execute(1, 2, UNSIGNED_MULTIPLY);
        assertEquals(1, RAM.read(1));

        RAM.write(1, 63);
        RAM.write(2, 63);
        FixedPointMultiply.INSTANCE.execute(1, 2, UNSIGNED_MULTIPLY + 6);
        assertEquals(62, RAM.read(1));
    }

    @Test
    void signedMultiplyMax() {
        RAM.write(1, -1);
        RAM.write(2, -1);
        FixedPointMultiply.INSTANCE.execute(1, 2, SIGNED_MULTIPLY);
        assertEquals(1, RAM.read(1));

        RAM.write(1, -1);
        RAM.write(2, 0b011111);
        FixedPointMultiply.INSTANCE.execute(1, 2, SIGNED_MULTIPLY + 6);
        assertEquals(63, RAM.read(1));
    }
}
