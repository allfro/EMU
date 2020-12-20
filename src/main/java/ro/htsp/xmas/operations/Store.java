package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;

public class Store implements Operation {

    public static final int OPCODE = 14;
    public static final Operation INSTANCE = new Store();

    @Override
    public int getOpCode() {
        return OPCODE;
    }

    @Override
    public void execute(int rs, int ra, int ib) {
        RAM.write((RAM.read(ra) + ib) % 64, RAM.read(rs));
    }
}
