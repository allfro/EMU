package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;

public class ShiftLeftImmediate implements Operation {

    public static final Operation INSTANCE = new ShiftLeftImmediate();

    @Override
    public int getOpCode() {
        return -1;
    }

    @Override
    public void execute(int rd, int ra, int ib) {
        RAM.write(rd, (RAM.read(ra) << ib) & 0b111_111);
    }

}
