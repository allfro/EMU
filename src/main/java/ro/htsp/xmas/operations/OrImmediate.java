package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;

public class OrImmediate implements Operation {
    public static final int OPCODE = 5;
    public static final Operation INSTANCE = new OrImmediate();

    @Override
    public int getOpCode() {
        return OPCODE;
    }

    @Override
    public void execute(int rd, int ra, int ib) {
        RAM.write(rd, RAM.read(ra) | ib);
    }

    @Override
    public String decode(Boolean condition, int rd, int ra, int ib) {
        return InstructionDecoder.decodeRRI(condition, "or", rd, ra, ib);
    }

}
