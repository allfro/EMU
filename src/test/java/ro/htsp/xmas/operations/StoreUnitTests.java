package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoreUnitTests {

    @BeforeEach
    void setup() {
        RAM.reset();
    }

    @Test
    public void testOpCode() {
        assertEquals(14, Store.INSTANCE.getOpCode());
    }

    @Test
    public void storeSmallOffset() {
        RAM.write(1, 1);
        RAM.write(2, 2);
        Store.INSTANCE.execute(2, 1, 8);
        assertEquals(2, RAM.read(9));
    }

    @Test
    public void storeZeroOffset() {
        RAM.write(1, 3);
        RAM.write(2, 2);
        Store.INSTANCE.execute(2, 1, 0);
        assertEquals(2, RAM.read(3));
    }

    @Test
    public void storeZeroRegister() {
        RAM.write(2, 3);
        Store.INSTANCE.execute(2, 0, 3);
        assertEquals(3, RAM.read(3));
    }

    @Test
    public void storeLargeOffset() {
        RAM.write(1, 63);
        RAM.write(2, 3);
        Store.INSTANCE.execute(2, 1, 4);
        assertEquals(3, RAM.read(3));
    }
}
