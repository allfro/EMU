package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddImmediateUnitTests {

    @Test
    void addTwoSmallNumbers() {
        RAM.write(1, 1);
        AddImmediate.INSTANCE.execute(3, 1, 10);
        assertEquals(11, RAM.read(3));
    }

    @Test
    void selfIncrement() {
        RAM.write(1, 1);
        AddImmediate.INSTANCE.execute(1, 1, 10);
        assertEquals(11, RAM.read(1));
    }

    @Test
    void addNegativeWithPositive() {
        RAM.write(1, 1);
        AddImmediate.INSTANCE.execute(1, 1, -22);
        assertEquals(-21, RAM.readSigned(1));
    }

    @Test
    void addTwoLargeNumbers() {
        RAM.write(1, 32);
        AddImmediate.INSTANCE.execute(1, 1, 63);
        assertEquals(31, RAM.read(1));
    }

    @Test
    void addTwoLargeNegativeNumbers() {
        RAM.write(1, -32);
        AddImmediate.INSTANCE.execute(1, 1, -31);
        assertEquals(1, RAM.read(1));
    }

}
