package ro.htsp.xmas.machine;

public class DirectionPad implements Device {

    public static final DirectionPad INSTANCE = new DirectionPad();

    private byte bitmap;

    @Override
    public void write(int value) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public synchronized int read() {
        return bitmap;
    }

    public synchronized void rightKeyUp() {
        System.err.println("DEBUG: right key up event");
        bitmap &= 0b111_110;
    }

    public synchronized void rightKeyDown() {
        System.err.println("DEBUG: right key down event");
        bitmap |= 0b000_001;
    }

    public synchronized void leftKeyUp() {
        System.err.println("DEBUG: left key up event");
        bitmap &= 0b111_101;
    }

    public synchronized void leftKeyDown() {
        System.err.println("DEBUG: left key down event");
        bitmap |= 0b000_010;
    }

    public synchronized void downKeyUp() {
        System.err.println("DEBUG: down key up event");
        bitmap &= 0b111_011;
    }

    public synchronized void downKeyDown() {
        System.err.println("DEBUG: down key down event");
        bitmap |= 0b000_100;
    }

    public synchronized void upKeyUp() {
        System.err.println("DEBUG: up key up event");
        bitmap &= 0b110_111;
    }


    public synchronized void upKeyDown() {
        System.err.println("DEBUG: up key down event");
        bitmap |= 0b001_000;
    }

    public synchronized void yKeyUp() {
        System.err.println("DEBUG: y key up event");
        bitmap &= 0b101_111;
    }

    public synchronized void yKeyDown() {
        System.err.println("DEBUG: y key down event");
        bitmap |= 0b010_000;
    }

    public synchronized void xKeyUp() {
        System.err.println("DEBUG: x key up event");
        bitmap &= 0b011_111;
    }

    public synchronized void xKeyDown() {
        System.err.println("DEBUG: x key down event");
        bitmap |= 0b100_000;
    }
}
