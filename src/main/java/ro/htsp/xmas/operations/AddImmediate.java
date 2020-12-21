package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;

public class AddImmediate implements Operation {
    public static final int OPCODE = 1;
    public static final Operation INSTANCE = new AddImmediate();

    @Override
    public int getOpCode() {
        return OPCODE;
    }

    @Override
    public void execute(int rd, int ra, int ib) {
        RAM.write(rd, RAM.read(ra) + ib);
    }

    @Override
    public String decode(Boolean condition, int rd, int ra, int ib) {
        return InstructionDecoder.decodeRRI(condition, "add", rd, ra, ib);
    }
}
