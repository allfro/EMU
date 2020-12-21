package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;

public class And implements Operation {

    public static final int OPCODE = 8;
    public static final Operation INSTANCE = new And();

    @Override
    public int getOpCode() {
        return OPCODE;
    }

    @Override
    public void execute(int rd, int ra, int rb) {
        RAM.write(rd, RAM.read(ra) & RAM.read(rb));
    }

    @Override
    public String decode(Boolean condition, int rd, int ra, int rb) {
        return InstructionDecoder.decodeRRR(condition, "and", rd, ra, rb);
    }

}
