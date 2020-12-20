package ro.htsp.xmas.machine;

import java.util.List;

public class CPU {
    private static boolean conditionFlag = false;
    private static int programCounter = 0;
    private static int clock = 0;
    private static List<Instruction> instructions;

    public static void incrementClock()  {
        if (clock != 0b111_111_111_111)
            clock += 1;
    }

    public static boolean isConditionFlagSet() {
        return conditionFlag;
    }

    public static void setConditionFlag(boolean conditionFlag) {
        CPU.conditionFlag = conditionFlag;
    }

    public static void incrementProgramCounter() {
        programCounter = (programCounter + 1) % instructions.size();
    }

    public static void decrementProgramCounter() {
        programCounter -= 1;
        if (programCounter == -1)
            programCounter = instructions.size() - 1;
    }

    public static int getProgramCounter() {
        return programCounter;
    }

    public static void loadProgram(List<Instruction> instructions) {
        CPU.instructions = instructions;
        while (programCounter < instructions.size()) {
            executeInstruction();
        }
    }

    public static void setInstructions(List<Instruction> instructions) {
        CPU.instructions = instructions;
    }

    public static void executeInstruction() {
        incrementClock();
        Instruction instruction = instructions.get(programCounter);
        instruction.execute();
        incrementProgramCounter();
    }

    public static void jumpUp(int lab, int rc) {
        do {
            decrementProgramCounter();
        } while (!instructions.get(programCounter).isLabel(lab, rc));
    }

    public static void jumpDown(int lab, int rc) {
        do {
            incrementProgramCounter();
        } while (!instructions.get(programCounter).isLabel(lab, rc));
    }

    public static int getClockLow() {
        return clock & 0b111111;
    }

    public static int getClockHigh() {
        return (clock >>> 6) & 0b111111;
    }

    public static void resetClock() {
        clock = 0;
        Monitor.INSTANCE.draw();
    }

    public static void reset() {
        conditionFlag = false;
        clock = 0;
        programCounter = 0;
    }
}
