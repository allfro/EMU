package ro.htsp.xmas.machine;

import ro.htsp.xmas.operations.*;

import java.util.HashMap;
import java.util.Map;

public class Instruction {
    private static final Map<Integer, Map.Entry<Boolean, Operation>> SUPPORTED_OPERATIONS = new HashMap<>() {{
        put(1 + Add.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(null, Add.INSTANCE));
        put(22 + Add.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(true, Add.INSTANCE));
        put(43 + Add.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(false, Add.INSTANCE));

        put(1 + AddImmediate.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(null, AddImmediate.INSTANCE));
        put(22 + AddImmediate.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(true, AddImmediate.INSTANCE));
        put(43 + AddImmediate.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(false, AddImmediate.INSTANCE));

        put(1 + Subtract.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(null, Subtract.INSTANCE));
        put(22 + Subtract.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(true, Subtract.INSTANCE));
        put(43 + Subtract.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(false, Subtract.INSTANCE));

        put(1 + Compare.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(null, Compare.INSTANCE));
        put(22 + Compare.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(true, Compare.INSTANCE));
        put(43 + Compare.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(false, Compare.INSTANCE));

        put(1 + Or.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(null, Or.INSTANCE));
        put(22 + Or.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(true, Or.INSTANCE));
        put(43 + Or.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(false, Or.INSTANCE));

        put(1 + OrImmediate.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(null, OrImmediate.INSTANCE));
        put(22 + OrImmediate.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(true, OrImmediate.INSTANCE));
        put(43 + OrImmediate.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(false, OrImmediate.INSTANCE));

        put(1 + Xor.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(null, Xor.INSTANCE));
        put(22 + Xor.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(true, Xor.INSTANCE));
        put(43 + Xor.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(false, Xor.INSTANCE));

        put(1 + XorImmediate.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(null, XorImmediate.INSTANCE));
        put(22 + XorImmediate.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(true, XorImmediate.INSTANCE));
        put(43 + XorImmediate.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(false, XorImmediate.INSTANCE));

        put(1 + And.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(null, And.INSTANCE));
        put(22 + And.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(true, And.INSTANCE));
        put(43 + And.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(false, And.INSTANCE));

        put(1 + AndImmediate.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(null, AndImmediate.INSTANCE));
        put(22 + AndImmediate.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(true, AndImmediate.INSTANCE));
        put(43 + AndImmediate.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(false, AndImmediate.INSTANCE));

        put(1 + ShiftAndRotate.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(null, ShiftAndRotate.INSTANCE));
        put(22 + ShiftAndRotate.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(true, ShiftAndRotate.INSTANCE));
        put(43 + ShiftAndRotate.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(false, ShiftAndRotate.INSTANCE));

        put(1 + ShiftLeft.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(null, ShiftLeft.INSTANCE));
        put(22 + ShiftLeft.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(true, ShiftLeft.INSTANCE));
        put(43 + ShiftLeft.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(false, ShiftLeft.INSTANCE));

        put(1 + ShiftRight.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(null, ShiftRight.INSTANCE));
        put(22 + ShiftRight.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(true, ShiftRight.INSTANCE));
        put(43 + ShiftRight.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(false, ShiftRight.INSTANCE));

        put(1 + Load.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(null, Load.INSTANCE));
        put(22 + Load.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(true, Load.INSTANCE));
        put(43 + Load.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(false, Load.INSTANCE));

        put(1 + Store.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(null, Store.INSTANCE));
        put(22 + Store.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(true, Store.INSTANCE));
        put(43 + Store.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(false, Store.INSTANCE));

        put(1 + Label.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(null, Label.INSTANCE));
        put(22 + Label.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(true, Label.INSTANCE));
        put(43 + Label.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(false, Label.INSTANCE));

        put(1 + JumpUp.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(null, JumpUp.INSTANCE));
        put(22 + JumpUp.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(true, JumpUp.INSTANCE));
        put(43 + JumpUp.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(false, JumpUp.INSTANCE));

        put(1 + JumpDown.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(null, JumpDown.INSTANCE));
        put(22 + JumpDown.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(true, JumpDown.INSTANCE));
        put(43 + JumpDown.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(false, JumpDown.INSTANCE));

        put(1 + IO.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(null, IO.INSTANCE));
        put(22 + IO.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(true, IO.INSTANCE));
        put(43 + IO.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(false, IO.INSTANCE));

        put(1 + FixedPointMultiply.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(null, FixedPointMultiply.INSTANCE));
        put(22 + FixedPointMultiply.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(true, FixedPointMultiply.INSTANCE));
        put(43 + FixedPointMultiply.INSTANCE.getOpCode(), new HashMap.SimpleEntry<>(false, FixedPointMultiply.INSTANCE));
    }};
    private final int opc;
    private final int a;
    private final int b;
    private final int c;
    Map.Entry<Boolean, Operation> halt = new HashMap.SimpleEntry<>(null, Halt.INSTANCE);
    private Boolean condition;
    private Operation operation;

    public Instruction(byte[] instruction) {
        int inst = ((instruction[0] & 255) << 16) | ((instruction[1] & 255) << 8) | (instruction[2] & 255);
        opc = (inst >>> 18) & 0b111111;
        a = (inst >>> 12) & 0b111111;
        b = (inst >>> 6) & 0b111111;
        c = (inst & 0b111111);

        Map.Entry<Boolean, Operation> op = SUPPORTED_OPERATIONS.getOrDefault(opc, halt);
        operation = op.getValue();
        condition = op.getKey();
    }

    public boolean isLabel(int lab, int rc) {
        return operation instanceof Label && isActive() && lab == ((64 * a) + b) && rc == c;
    }

    public boolean isActive() {
        return condition == null || condition == CPU.isConditionFlagSet();
    }

    public void execute() {
        if (isActive())
            operation.execute(a, b, c);
    }

    public String toString() {
        return operation.decode(condition, a, b, c);
    }
}
