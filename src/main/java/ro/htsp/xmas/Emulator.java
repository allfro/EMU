package ro.htsp.xmas;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import ro.htsp.xmas.machine.CPU;
import ro.htsp.xmas.machine.Instruction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static net.sourceforge.argparse4j.impl.Arguments.storeTrue;

public class Emulator {

    private static final ArgumentParser parser = ArgumentParsers.newFor("EMU 1.0 Text Emulator")
            .build()
            .defaultHelp(true)
            .description("Run EMU 1.0 roms.");

    static {
        parser.addArgument("-r", "--raw")
                .action(storeTrue());
        parser.addArgument("-c", "--clock")
                .help("Clock cycle period in nanoseconds (0 for no delay, default: 1000).")
                .type(Integer.class)
                .setDefault(1000);
    }

    List<Instruction> instructions = new ArrayList<>();

    private Emulator(File file, boolean isEncoded) throws Exception {
        if (isEncoded)
            readBase64Program(file);
        else
            readProgram(file);
    }

    private void readProgram(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        System.err.print("Loading program... ");
        while (fis.available() > 0) {
            byte[] instruction = fis.readNBytes(3);
            if (instruction.length == 0) break;
            instructions.add(new Instruction(instruction));
        }
        System.err.println("done.");
    }

    private void readBase64Program(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        Base64.Decoder decoder = Base64.getMimeDecoder();
        System.err.print("Loading program... ");
        while (fis.available() > 0) {
            byte[] instruction = decoder.decode(fis.readNBytes(4));
            if (instruction.length == 0) break;
            instructions.add(new Instruction(instruction));
        }
        System.err.println("done.");
    }

    private void loadProgram() {
        CPU.setInstructions(instructions);
    }

    private void execute() {
        CPU.loadProgram(instructions);
    }

    public void executeStep() {
        CPU.executeInstruction();
    }

    public static void runProgram(File program, boolean isEncoded) throws Exception {
        Emulator emulator = new Emulator(program, isEncoded);
        emulator.execute();
    }

    public static Emulator loadProgram(File program, boolean isEncoded) throws Exception {
        Emulator emulator = new Emulator(program, isEncoded);
        emulator.loadProgram();
        return emulator;
    }

    public static void main(String[] args) throws Exception {
        Emulator.runProgram(new File("mandelflag.rom"), true);
//        Group root = new Group();
//        Scene s = new Scene(root, 300, 300, Color.BLACK);
//
//        final Canvas canvas = new Canvas(250,250);
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//
//        gc.setFill(Color.BLUE);
//        gc.fillRect(75,75,100,100);
//
//        root.getChildren().add(canvas);
    }
}
