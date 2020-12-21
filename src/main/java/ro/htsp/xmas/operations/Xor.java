package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;

public class Xor implements Operation {

    public static final int OPCODE = 6;
    public static final Operation INSTANCE = new Xor();

    @Override
    public int getOpCode() {
        return OPCODE;
    }

    @Override
    public void execute(int rd, int ra, int rb) {
        RAM.write(rd, RAM.read(ra) ^ RAM.read(rb));
    }

    @Override
    public String decode(Boolean condition, int rd, int ra, int rb) {
        return InstructionDecoder.decodeRRR(condition, "xor", rd, ra, rb);
    }

}
