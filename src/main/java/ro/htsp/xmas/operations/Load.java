package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;

public class Load implements Operation {

    public static final int OPCODE = 13;
    public static final Operation INSTANCE = new Load();

    @Override
    public int getOpCode() {
        return OPCODE;
    }

    @Override
    public void execute(int rd, int ra, int ib) {
        RAM.write(rd, RAM.read((RAM.read(ra) + ib) % 64));
    }

    @Override
    public String decode(Boolean condition, int rd, int ra, int ib) {
        return InstructionDecoder.decodeLoad(condition, rd, ra, ib);
    }
}
