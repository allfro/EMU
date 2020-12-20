package ro.htsp.xmas.machine;

public class GPU implements Device {
    public static final GPU INSTANCE = new GPU();

    private int x = 0;
    private int y = 0;

    @Override
    public void write(int value) {
        Monitor.INSTANCE.drawPixel(x, y, value & 0b111_111);
    }

    @Override
    public int read() {
        return 0;
    }

    public void writeX(int x) {
        this.x = x & 0b111_111;
    }

    public void writeY(int y) {
        this.y = y & 0b111_111;
    }
}
