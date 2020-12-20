package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XorUnitTests {

    @Test
    void testOpCode() {
        assertEquals(6, Xor.INSTANCE.getOpCode());
    }

    @Test
    void xorZero() {
        RAM.write(1, 0b100000);
        RAM.write(2, 0b000000);
        Xor.INSTANCE.execute(3, 1, 2);
        assertEquals(0b100000, RAM.read(3));
    }

    @Test
    void xorPartialOne() {
        RAM.write(1, 0b100001);
        RAM.write(2, 0b000001);
        Xor.INSTANCE.execute(3, 1, 2);
        assertEquals(0b100000, RAM.read(3));
    }


    @Test
    void xorComplete() {
        RAM.write(1, 0b100001);
        RAM.write(2, 0b100001);
        Xor.INSTANCE.execute(3, 1, 2);
        assertEquals(0, RAM.read(3));
    }


    @Test
    void selfXor() {
        RAM.write(1, 0b100001);
        RAM.write(2, 0b100001);
        Xor.INSTANCE.execute(1, 1, 2);
        assertEquals(0, RAM.read(1));
    }

}
