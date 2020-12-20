package ro.htsp.xmas.machine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RAMUnitTests {

    @Test
    void slot0IsFrozen() {
        RAM.write(0, 64);
        assertEquals(0, RAM.read(0));
    }

    @Test
    void storeLargeNumber() {
        RAM.write(1, 64);
        assertEquals(0, RAM.read(1));
    }

    @Test
    void storeNegativeNumber() {
        RAM.write(1, -32);
        assertEquals(-32, RAM.readSigned(1));

        RAM.write(1, 63);
        assertEquals(-1, RAM.readSigned(1));
        assertEquals(63, RAM.read(1));
    }

    @Test
    void storeLargeNegativeNumber() {
        RAM.write(1, -33);
        assertEquals(31, RAM.readSigned(1));
    }
}
