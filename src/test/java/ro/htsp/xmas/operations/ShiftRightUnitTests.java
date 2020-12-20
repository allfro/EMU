package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShiftRightUnitTests {

    @Test
    void testOpCode() {
        assertEquals(12, ShiftRight.INSTANCE.getOpCode());
    }

    @Test
    void shiftRight() {
        RAM.write(1, 0b110000);
        RAM.write(2, 2);
        ShiftRight.INSTANCE.execute(3, 1, 2);
        assertEquals(0b001100, RAM.read(3));
    }

}
