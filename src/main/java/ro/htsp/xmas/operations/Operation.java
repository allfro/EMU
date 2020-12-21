package ro.htsp.xmas.operations;

public interface Operation {

    int getOpCode();

    void execute(int a, int b, int c);

    String decode(Boolean condition, int a, int b, int c);
}
