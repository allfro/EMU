package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.CPU;
import ro.htsp.xmas.machine.RAM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompareUnitTests {

    public static final int COMPARE_TRUE = 0;
    public static final int COMPARE_FALSE = 1;
    public static final int COMPARE_EQUALS = 2;
    public static final int COMPARE_NOT_EQUALS = 3;
    public static final int COMPARE_SIGNED_LESS_THAN = 4;
    public static final int COMPARE_SIGNED_GREATER_THAN = 5;
    public static final int COMPARE_UNSIGNED_LESS_THAN = 6;
    public static final int COMPARE_UNSIGNED_GREATER_THAN = 7;

    public static final int COMPARE_SLOT_A_SLOT_B = 0;
    public static final int COMPARE_SLOT_B_SLOT_A = 8;
    public static final int COMPARE_SLOT_A_IMMEDIATE_B = 16;
    public static final int COMPARE_IMMEDIATE_A_SLOT_B = 24;

    @BeforeEach
    void setupTest() {
        RAM.reset();
        CPU.reset();
    }

    @Test
    void testOpCode() {
        assertEquals(3, Compare.INSTANCE.getOpCode());
    }

    @Test
    void testTrue() {
        Compare.INSTANCE.execute(COMPARE_TRUE, 0, 0);
        assertTrue(CPU.isConditionFlagSet());
    }

    @Test
    void testFalse() {
        Compare.INSTANCE.execute(COMPARE_FALSE, 0, 0);
        assertFalse(CPU.isConditionFlagSet());
    }

    @Test
    void testEqualsTrue() {
        RAM.write(1, 2);
        RAM.write(2, 2);
        assertTrue(RAM.read(1) == RAM.read(2));

        Compare.INSTANCE.execute(COMPARE_EQUALS | COMPARE_SLOT_A_SLOT_B, 1, 2);
        assertTrue(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_EQUALS | COMPARE_SLOT_B_SLOT_A, 1, 2);
        assertTrue(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_EQUALS | COMPARE_IMMEDIATE_A_SLOT_B, 2, 2);
        assertTrue(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_EQUALS | COMPARE_SLOT_A_IMMEDIATE_B, 2, 2);
        assertTrue(CPU.isConditionFlagSet());
    }

    @Test
    void testEqualsFalse() {
        RAM.write(1, 3);
        RAM.write(2, 2);
        assertTrue(RAM.read(1) != RAM.read(2));

        Compare.INSTANCE.execute(COMPARE_EQUALS | COMPARE_SLOT_A_SLOT_B, 1, 2);
        assertFalse(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_EQUALS | COMPARE_SLOT_B_SLOT_A, 1, 2);
        assertFalse(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_EQUALS | COMPARE_IMMEDIATE_A_SLOT_B, 3, 2);
        assertFalse(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_EQUALS | COMPARE_SLOT_A_IMMEDIATE_B, 2, 3);
        assertFalse(CPU.isConditionFlagSet());
    }

    @Test
    void testNotEqualsTrue() {
        RAM.write(1, 2);
        RAM.write(2, 3);
        assertTrue(RAM.read(1) != RAM.read(2));

        Compare.INSTANCE.execute(COMPARE_NOT_EQUALS | COMPARE_SLOT_A_SLOT_B, 1, 2);
        assertTrue(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_NOT_EQUALS | COMPARE_SLOT_B_SLOT_A, 1, 2);
        assertTrue(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_NOT_EQUALS | COMPARE_IMMEDIATE_A_SLOT_B, 2, 3);
        assertTrue(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_NOT_EQUALS | COMPARE_SLOT_A_IMMEDIATE_B, 2, 2);
        assertTrue(CPU.isConditionFlagSet());
    }

    @Test
    void testNotEqualsFalse() {
        RAM.write(1, 3);
        RAM.write(2, 3);
        assertTrue(RAM.read(1) == RAM.read(2));

        Compare.INSTANCE.execute(COMPARE_NOT_EQUALS | COMPARE_SLOT_A_SLOT_B, 1, 2);
        assertFalse(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_NOT_EQUALS | COMPARE_SLOT_B_SLOT_A, 1, 2);
        assertFalse(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_NOT_EQUALS | COMPARE_IMMEDIATE_A_SLOT_B, 3, 2);
        assertFalse(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_NOT_EQUALS | COMPARE_SLOT_A_IMMEDIATE_B, 2, 3);
        assertFalse(CPU.isConditionFlagSet());
    }

    @Test
    void testSignedLessThan() {
        RAM.write(1, -2);
        RAM.write(2, -1);

        assertTrue(RAM.readSigned(1) < RAM.readSigned(2));
        Compare.INSTANCE.execute(COMPARE_SIGNED_LESS_THAN | COMPARE_SLOT_A_SLOT_B, 1, 2);
        assertTrue(CPU.isConditionFlagSet());

        RAM.write(2, 0);

        assertTrue(RAM.readSigned(1) < RAM.readSigned(2));
        Compare.INSTANCE.execute(COMPARE_SIGNED_LESS_THAN | COMPARE_SLOT_A_SLOT_B, 1, 2);
        assertTrue(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_SIGNED_LESS_THAN | COMPARE_SLOT_B_SLOT_A, 2, 1);
        assertTrue(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_SIGNED_LESS_THAN | COMPARE_IMMEDIATE_A_SLOT_B, -3, 2);
        assertTrue(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_SIGNED_LESS_THAN | COMPARE_SLOT_A_IMMEDIATE_B, 1, 2);
        assertTrue(CPU.isConditionFlagSet());
    }

    @Test
    void testSignedGreaterThan() {
        RAM.write(1, -2);
        RAM.write(2, -1);

        assertTrue(RAM.readSigned(1) < RAM.readSigned(2));
        Compare.INSTANCE.execute(COMPARE_SIGNED_GREATER_THAN | COMPARE_SLOT_A_SLOT_B, 2, 1);
        assertTrue(CPU.isConditionFlagSet());

        RAM.write(2, 0);

        assertTrue(RAM.readSigned(1) < RAM.readSigned(2));
        Compare.INSTANCE.execute(COMPARE_SIGNED_GREATER_THAN | COMPARE_SLOT_A_SLOT_B, 2, 1);
        assertTrue(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_SIGNED_GREATER_THAN | COMPARE_SLOT_B_SLOT_A, 1, 2);
        assertTrue(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_SIGNED_GREATER_THAN | COMPARE_IMMEDIATE_A_SLOT_B, 1, 2);
        assertTrue(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_SIGNED_GREATER_THAN | COMPARE_SLOT_A_IMMEDIATE_B, 1, -3);
        assertTrue(CPU.isConditionFlagSet());
    }

    @Test
    void testUnsignedLessThan() {
        RAM.write(1, 0);
        RAM.write(2, 63);

        assertTrue(RAM.read(1) < RAM.read(2));
        Compare.INSTANCE.execute(COMPARE_UNSIGNED_LESS_THAN | COMPARE_SLOT_A_SLOT_B, 1, 2);
        assertTrue(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_UNSIGNED_LESS_THAN | COMPARE_SLOT_B_SLOT_A, 2, 1);
        assertTrue(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_UNSIGNED_LESS_THAN | COMPARE_IMMEDIATE_A_SLOT_B, 1, 2);
        assertTrue(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_UNSIGNED_LESS_THAN | COMPARE_SLOT_A_IMMEDIATE_B, 1, 2);
        assertTrue(CPU.isConditionFlagSet());
    }

    @Test
    void testUnsignedGreaterThan() {
        RAM.write(1, 63);
        RAM.write(2, 1);

        assertTrue(RAM.read(1) > RAM.read(2));
        Compare.INSTANCE.execute(COMPARE_UNSIGNED_GREATER_THAN | COMPARE_SLOT_A_SLOT_B, 1, 2);
        assertTrue(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_UNSIGNED_GREATER_THAN | COMPARE_SLOT_B_SLOT_A, 2, 1);
        assertTrue(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_UNSIGNED_GREATER_THAN | COMPARE_IMMEDIATE_A_SLOT_B, 2, 2);
        assertTrue(CPU.isConditionFlagSet());

        Compare.INSTANCE.execute(COMPARE_UNSIGNED_GREATER_THAN | COMPARE_SLOT_A_IMMEDIATE_B, 1, 2);
        assertTrue(CPU.isConditionFlagSet());
    }
}
