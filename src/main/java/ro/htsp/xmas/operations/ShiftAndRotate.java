package ro.htsp.xmas.operations;

public class ShiftAndRotate implements Operation {

    public static final int OPCODE = 10;
    public static final Operation INSTANCE = new ShiftAndRotate();

    @Override
    public int getOpCode() {
        return OPCODE;
    }

    @Override
    public void execute(int rd, int ra, int oib) {
        if ((oib & 24) == 24) {
            RotateLeft.INSTANCE.execute(rd, ra, oib - 030);
        } else if ((oib & 16) == 16) {
            ShiftArithmeticRight.INSTANCE.execute(rd, ra, oib - 020);
        } else if ((oib & 8) == 8) {
            ShiftRightImmediate.INSTANCE.execute(rd, ra, oib - 010);
        } else {
            ShiftLeftImmediate.INSTANCE.execute(rd, ra, oib);
        }
    }

    @Override
    public String decode(Boolean condition, int rd, int ra, int oib) {
        if ((oib & 24) == 24) {
            return RotateLeft.INSTANCE.decode(condition, rd, ra, oib);
        } else if ((oib & 16) == 16) {
            return ShiftArithmeticRight.INSTANCE.decode(condition, rd, ra, oib - 16);
        } else if ((oib & 8) == 8) {
            return ShiftRightImmediate.INSTANCE.decode(condition, rd, ra, oib - 8);
        }
        return ShiftLeftImmediate.INSTANCE.decode(condition, rd, ra, oib);
    }

}
