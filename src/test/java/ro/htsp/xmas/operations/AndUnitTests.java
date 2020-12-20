package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AndUnitTests {

    @Test
    void testOpCode() {
        assertEquals(8, And.INSTANCE.getOpCode());
    }

    @Test
    void andZero() {
        RAM.write(1, 0b100000);
        RAM.write(2, 0b000000);
        And.INSTANCE.execute(3, 1, 2);
        assertEquals(0, RAM.read(3));
    }

    @Test
    void andPartialOne() {
        RAM.write(1, 0b100001);
        RAM.write(2, 0b000001);
        And.INSTANCE.execute(3, 1, 2);
        assertEquals(1, RAM.read(3));
    }


    @Test
    void andComplete() {
        RAM.write(1, 0b100001);
        RAM.write(2, 0b100001);
        And.INSTANCE.execute(3, 1, 2);
        assertEquals(0b100001, RAM.read(3));
    }


    @Test
    void selfAnd() {
        RAM.write(1, 0b100001);
        RAM.write(2, 0b100001);
        And.INSTANCE.execute(1, 1, 2);
        assertEquals(0b100001, RAM.read(1));
    }

}
