package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;

public class AndImmediate implements Operation {

    public static final int OPCODE = 9;
    public static final Operation INSTANCE = new AndImmediate();

    @Override
    public int getOpCode() {
        return OPCODE;
    }

    @Override
    public void execute(int rd, int ra, int ib) {
        RAM.write(rd, RAM.read(ra) & ib);
    }

}
