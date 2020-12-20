package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadUnitTests {

    @BeforeEach
    void setup() {
        RAM.reset();
    }

    @Test
    public void testOpCode() {
        assertEquals(13, Load.INSTANCE.getOpCode());
    }

    @Test
    public void loadSmallOffset() {
        RAM.write(1, 1);
        RAM.write(9, 9);
        Load.INSTANCE.execute(2, 1, 8);
        assertEquals(9, RAM.read(2));
    }

    @Test
    public void loadZeroOffset() {
        RAM.write(1, 3);
        RAM.write(3, 3);
        Load.INSTANCE.execute(2, 1, 0);
        assertEquals(3, RAM.read(2));
    }

    @Test
    public void loadZeroRegister() {
        RAM.write(3, 3);
        Load.INSTANCE.execute(2, 0, 3);
        assertEquals(3, RAM.read(2));
    }

    @Test
    public void loadLargeOffset() {
        RAM.write(1, 63);
        RAM.write(3, 3);
        Load.INSTANCE.execute(2, 1, 4);
        assertEquals(3, RAM.read(2));
    }
}
