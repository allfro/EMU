package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrImmediateUnitTests {

    @Test
    void testOpCode() {
        assertEquals(5, OrImmediate.INSTANCE.getOpCode());
    }

    @Test
    void or() {
        RAM.write(1, 0b100000);
        OrImmediate.INSTANCE.execute(3, 1, 0b100001);
        assertEquals(0b100001, RAM.read(3));
    }

}
