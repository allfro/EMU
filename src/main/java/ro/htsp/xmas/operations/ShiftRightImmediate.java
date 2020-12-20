package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;

public class ShiftRightImmediate implements Operation {

    public static final Operation INSTANCE = new ShiftRightImmediate();

    @Override
    public int getOpCode() {
        return -1;
    }

    @Override
    public void execute(int rd, int ra, int ib) {
        RAM.write(rd, RAM.read(ra) >>> ib);
    }

}
