package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;

public class ShiftLeft implements Operation {

    public static final int OPCODE = 11;
    public static final Operation INSTANCE = new ShiftLeft();

    @Override
    public int getOpCode() {
        return OPCODE;
    }

    @Override
    public void execute(int rd, int ra, int rb) {
        RAM.write(rd, RAM.read(ra) << RAM.read(rb));
    }

    @Override
    public String decode(Boolean condition, int rd, int ra, int rb) {
        return InstructionDecoder.decodeRRR(condition, "shl", rd, ra, rb);
    }

}
