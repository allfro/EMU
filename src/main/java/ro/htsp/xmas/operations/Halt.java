package ro.htsp.xmas.operations;

public class Halt implements Operation {

    public static final int OPCODE = -1;
    public static final Operation INSTANCE = new Halt();

    @Override
    public int getOpCode() {
        return OPCODE;
    }

    @Override
    public void execute(int a, int b, int c) {
        System.exit(0);
    }

}
