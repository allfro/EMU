package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XorImmediateUnitTests {

    @Test
    void testOpCode() {
        assertEquals(7, XorImmediate.INSTANCE.getOpCode());
    }

    @Test
    void xorZero() {
        RAM.write(1, 0b100000);
        XorImmediate.INSTANCE.execute(3, 1, 0b000000);
        assertEquals(0b100000, RAM.read(3));
    }

    @Test
    void xorPartialOne() {
        RAM.write(1, 0b100001);
        XorImmediate.INSTANCE.execute(3, 1, 0b100000);
        assertEquals(1, RAM.read(3));
    }


    @Test
    void xorComplete() {
        RAM.write(1, 0b100001);
        XorImmediate.INSTANCE.execute(3, 1, 0b100001);
        assertEquals(0, RAM.read(3));
    }


    @Test
    void selfXor() {
        RAM.write(1, 0b100001);
        XorImmediate.INSTANCE.execute(1, 1, 0b100001);
        assertEquals(0, RAM.read(1));
    }

}
