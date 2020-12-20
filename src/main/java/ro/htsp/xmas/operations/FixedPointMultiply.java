package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;

public class FixedPointMultiply implements Operation {

    public static final int OPCODE = 19;
    public static final Operation INSTANCE = new FixedPointMultiply();

    @Override
    public int getOpCode() {
        return OPCODE;
    }

    @Override
    public void execute(int rd, int ra, int pr) {
        if ((pr & 16) == 16) {
            signedMultiply(rd, ra, pr - 16);
        } else {
            unsignedMultiply(rd, ra, pr);
        }
    }

    private void signedMultiply(int rd, int ra, int shift) {
        RAM.write(rd, (RAM.readSigned(rd) * RAM.readSigned(ra)) >> shift);
    }

    private void unsignedMultiply(int rd, int ra, int shift) {
        RAM.write(rd, ((RAM.read(rd) * RAM.read(ra)) & 0b111_111_111_111) >>> shift);
    }

}
