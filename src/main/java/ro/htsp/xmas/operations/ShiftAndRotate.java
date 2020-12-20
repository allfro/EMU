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
        if ((oib & 030) == 030) {
            RotateLeft.INSTANCE.execute(rd, ra, oib - 030);
        } else if ((oib & 020) == 020) {
            ShiftArithmeticRight.INSTANCE.execute(rd, ra, oib - 020);
        } else if ((oib & 010) == 010) {
            ShiftRightImmediate.INSTANCE.execute(rd, ra, oib - 010);
        } else {
            ShiftLeftImmediate.INSTANCE.execute(rd, ra, oib);
        }
    }

}
