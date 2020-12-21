package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;

public class RotateLeft implements Operation {

    public static final Operation INSTANCE = new RotateLeft();

    @Override
    public int getOpCode() {
        return -1;
    }

    @Override
    public void execute(int rd, int ra, int ib) {
        int value = RAM.read(ra);
        for (int i = 0; i < ib; i++) {
            value = ((value & 0b100000) >>> 5) | ((value & 0b011_111) << 1);
        }
        RAM.write(rd, value);
    }

    @Override
    public String decode(Boolean condition, int rd, int ra, int ib) {
        return InstructionDecoder.decodeRRI(condition, "rol", rd, ra, ib);
    }

}
