package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddUnitTests {

    @Test
    void testOpCode() {
        assertEquals(0, Add.INSTANCE.getOpCode());
    }

    @Test
    void addTwoSmallNumbers() {
        RAM.write(1, 1);
        RAM.write(2, 10);
        Add.INSTANCE.execute(3, 1, 2);
        assertEquals(11, RAM.read(3));
    }

    @Test
    void selfIncrement() {
        RAM.write(1, 1);
        RAM.write(2, 10);
        Add.INSTANCE.execute(1, 1, 2);
        assertEquals(11, RAM.read(1));
    }

    @Test
    void addNegativeWithPositive() {
        RAM.write(1, 1);
        RAM.write(2, -1);
        Add.INSTANCE.execute(1, 1, 2);
        assertEquals(0, RAM.read(1));
    }

    @Test
    void addTwoLargeNumbers() {
        RAM.write(1, 32);
        RAM.write(2, 63);
        Add.INSTANCE.execute(1, 1, 2);
        assertEquals(31, RAM.read(1));
    }

    @Test
    void addTwoLargeNegativeNumbers() {
        RAM.write(1, -32);
        RAM.write(2, -31);
        Add.INSTANCE.execute(1, 1, 2);
        assertEquals(1, RAM.read(1));
    }

}
