package ro.htsp.xmas;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.Namespace;
import ro.htsp.xmas.machine.CPU;
import ro.htsp.xmas.machine.EthernetCard;
import ro.htsp.xmas.machine.Instruction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
        parser.addArgument("-a", "--connect")
                .help("Enabled Ethernet card and connects to destination.")
                .metavar("<address:port>")
                .type(String.class)
                .setDefault("");
        parser.addArgument("romFile")
                .help("The path of the ROM file")
                .type(Arguments.fileType().acceptSystemIn().verifyCanRead())
                .setDefault("-")
                .nargs(1);
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
        Namespace namespace = parser.parseArgs(args);

        int clockPeriod = namespace.getInt("clock");
        boolean isEncoded = !namespace.getBoolean("raw");
        String connect = namespace.getString("connect");
        File romFile = (File)namespace.getList("romFile").get(0);

        if (!connect.isEmpty())
            EthernetCard.init(connect);

        if (clockPeriod != 0) {
            Emulator emulator = Emulator.loadProgram(romFile, isEncoded);
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(emulator::executeStep, 1000000000, clockPeriod, TimeUnit.NANOSECONDS);
            scheduler.awaitTermination(1, TimeUnit.DAYS);
        } else {
            Emulator.runProgram(romFile, isEncoded);
        }
    }
}
