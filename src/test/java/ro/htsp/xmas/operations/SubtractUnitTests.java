package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubtractUnitTests {

    @Test
    void testOpCode() {
        assertEquals(2, Subtract.INSTANCE.getOpCode());
    }

    @Test
    void subtractTwoSmallNumbers() {
        RAM.write(1, 1);
        RAM.write(2, 10);
        Subtract.INSTANCE.execute(3, 1, 2);
        assertEquals(-9, RAM.readSigned(3));
    }

    @Test
    void selfDecrement() {
        RAM.write(1, 1);
        RAM.write(2, 2);
        Subtract.INSTANCE.execute(1, 1, 2);
        assertEquals(-1, RAM.readSigned(1));
    }

    @Test
    void subtractNegativeFromPositive() {
        RAM.write(1, 1);
        RAM.write(2, -1);
        Subtract.INSTANCE.execute(1, 1, 2);
        assertEquals(2, RAM.read(1));
    }

    @Test
    void subtractPositiveFromNegative() {
        RAM.write(1, -1);
        RAM.write(2, 1);
        Subtract.INSTANCE.execute(1, 1, 2);
        assertEquals(-2, RAM.readSigned(1));
    }

    @Test
    void subtractTwoLargeNumbers() {
        RAM.write(1, 32);
        RAM.write(2, 63);
        Subtract.INSTANCE.execute(1, 1, 2);
        assertEquals(-31, RAM.readSigned(1));
    }

    @Test
    void subtractTwoLargeNegativeNumbers() {
        RAM.write(1, -63);
        RAM.write(2, -32);
        Subtract.INSTANCE.execute(1, 1, 2);
        assertEquals(-31, RAM.readSigned(1));
    }

}
