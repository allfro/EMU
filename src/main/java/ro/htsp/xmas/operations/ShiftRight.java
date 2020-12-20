package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;

public class ShiftRight implements Operation {

    public static final int OPCODE = 12;
    public static final Operation INSTANCE = new ShiftRight();

    @Override
    public int getOpCode() {
        return OPCODE;
    }

    @Override
    public void execute(int rd, int ra, int rb) {
        RAM.write(rd, RAM.read(ra) >>> RAM.read(rb));
    }
}
