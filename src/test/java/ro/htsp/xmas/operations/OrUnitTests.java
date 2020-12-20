package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrUnitTests {

    @Test
    void testOpCode() {
        assertEquals(4, Or.INSTANCE.getOpCode());
    }

    @Test
    void or() {
        RAM.write(1, 0b100000);
        RAM.write(2, 0b100001);
        Or.INSTANCE.execute(3, 1, 2);
        assertEquals(0b100001, RAM.read(3));
    }

}
