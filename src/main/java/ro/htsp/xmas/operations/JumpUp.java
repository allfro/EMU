package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.CPU;
import ro.htsp.xmas.machine.RAM;

public class JumpUp implements Operation {

    public static final int OPCODE = 16;
    public static final Operation INSTANCE = new JumpUp();

    @Override
    public int getOpCode() {
        return OPCODE;
    }

    @Override
    public void execute(int la, int lb, int rc) {
        CPU.jumpUp((64 * la) + lb, RAM.read(rc));
    }

}
