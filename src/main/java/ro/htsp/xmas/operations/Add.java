package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;

public class Add implements Operation {
    public static final int OPCODE = 0;
    public static final Operation INSTANCE = new Add();

    @Override
    public int getOpCode() {
        return OPCODE;
    }

    @Override
    public void execute(int rd, int ra, int rb) {
        RAM.write(rd, RAM.read(ra) + RAM.read(rb));
    }

    public String decode(Boolean condition, int rd, int ra, int rb) {
        return InstructionDecoder.decodeRRR(condition, "add", rd, ra, rb);
    }

}
