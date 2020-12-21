package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.CPU;
import ro.htsp.xmas.machine.RAM;

public class JumpDown implements Operation {

    public static final int OPCODE = 17;
    public static final Operation INSTANCE = new JumpDown();

    @Override
    public int getOpCode() {
        return OPCODE;
    }

    @Override
    public void execute(int la, int lb, int rc) {
        CPU.jumpDown((64 * la) + lb, RAM.read(rc));
    }

    @Override
    public String decode(Boolean condition, int la, int lb, int rc) {
        return InstructionDecoder.decodeJumpDown(condition, (64 * la) + lb, rc);
    }

}
