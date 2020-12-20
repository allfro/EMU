package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.CPU;
import ro.htsp.xmas.machine.RAM;

public class Compare implements Operation {
    public static final int OPCODE = 3;
    public static final Operation INSTANCE = new Compare();

    @Override
    public int getOpCode() {
        return OPCODE;
    }

    @Override
    public void execute(int cm, int b, int c) {
        if ((cm & 030) == 030) {
            compare(cm - 030, b, RAM.read(c));
        } else if ((cm & 020) == 020) {
            compare(cm - 020, RAM.read(b), c);
        } else if ((cm & 010) == 010) {
            compare(cm - 010, RAM.read(c), RAM.read(b));
        } else {
            compare(cm, RAM.read(b), RAM.read(c));
        }
    }

    private void compare(int comparison, int a, int b) {
        switch (comparison) {
            case 0:
                CPU.setConditionFlag(true);
                break;
            case 1:
                CPU.setConditionFlag(false);
                break;
            case 2:
                CPU.setConditionFlag(a == b);
                break;
            case 3:
                CPU.setConditionFlag(a != b);
                break;
            case 4:
                CPU.setConditionFlag(Utils.sixBitSigned(a) < Utils.sixBitSigned(b));
                break;
            case 5:
                CPU.setConditionFlag(Utils.sixBitSigned(a) > Utils.sixBitSigned(b));
                break;
            case 6:
                CPU.setConditionFlag(a < b);
                break;
            case 7:
                CPU.setConditionFlag(a > b);
                break;
        }
    }

}
