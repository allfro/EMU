package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;

public class ShiftArithmeticRight implements Operation {

    public static final Operation INSTANCE = new ShiftArithmeticRight();

    @Override
    public int getOpCode() {
        return -1;
    }

    @Override
    public void execute(int rd, int ra, int ib) {
        RAM.write(rd, RAM.readSigned(ra) >> ib);
    }

    @Override
    public String decode(Boolean condition, int rd, int ra, int ib) {
        return InstructionDecoder.decodeRRI(condition, "sar", rd, ra, ib);
    }

}
