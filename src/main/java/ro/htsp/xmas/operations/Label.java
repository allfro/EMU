package ro.htsp.xmas.operations;

public class Label implements Operation {

    public static final int OPCODE = 15;
    public static final Operation INSTANCE = new Label();

    @Override
    public int getOpCode() {
        return OPCODE;
    }

    @Override
    public void execute(int la, int lb, int c) {

    }

    @Override
    public String decode(Boolean condition, int la, int lb, int c) {
        return InstructionDecoder.decodeLabel(condition, (64 * la) + lb, c);
    }

}
