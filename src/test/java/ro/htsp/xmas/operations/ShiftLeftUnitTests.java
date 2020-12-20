package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShiftLeftUnitTests {

    @Test
    void testOpCode() {
        assertEquals(11, ShiftLeft.INSTANCE.getOpCode());
    }

    @Test
    void shiftRight() {
        RAM.write(1, 0b001100);
        RAM.write(2, 2);
        ShiftLeft.INSTANCE.execute(3, 1, 2);
        assertEquals(0b110000, RAM.read(3));
    }

}
