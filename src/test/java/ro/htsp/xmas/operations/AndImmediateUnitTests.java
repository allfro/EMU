package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AndImmediateUnitTests {

    @Test
    void testOpCode() {
        assertEquals(9, AndImmediate.INSTANCE.getOpCode());
    }

    @Test
    void andZero() {
        RAM.write(1, 0b100000);
        AndImmediate.INSTANCE.execute(3, 1, 0b000000);
        assertEquals(0, RAM.read(3));
    }

    @Test
    void andPartialOne() {
        RAM.write(1, 0b100001);
        AndImmediate.INSTANCE.execute(3, 1, 0b000001);
        assertEquals(1, RAM.read(3));
    }


    @Test
    void andComplete() {
        RAM.write(1, 0b100001);
        AndImmediate.INSTANCE.execute(3, 1, 0b100001);
        assertEquals(0b100001, RAM.read(3));
    }


    @Test
    void selfAnd() {
        RAM.write(1, 0b100001);
        AndImmediate.INSTANCE.execute(1, 1, 0b100001);
        assertEquals(0b100001, RAM.read(1));
    }

}
