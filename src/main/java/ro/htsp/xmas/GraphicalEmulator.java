package ro.htsp.xmas;

import ro.htsp.xmas.machine.DirectionPad;
import ro.htsp.xmas.machine.EthernetCard;
import ro.htsp.xmas.machine.Monitor;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static net.sourceforge.argparse4j.impl.Arguments.storeTrue;


public class GraphicalEmulator extends Application {

    private static final ArgumentParser parser = ArgumentParsers.newFor("EMU 1.0 Graphical Emulator")
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
        parser.addArgument("-s", "--scale")
                .help("Pixel scale factor (default: 5).")
                .type(Integer.class)
                .setDefault(5);
        parser.addArgument("-n", "--no-double-buffer")
                .help("Disables double buffering for screen (default: false).")
                .action(storeTrue());
        parser.addArgument("romFile")
                .help("The path of the ROM file")
                .type(Arguments.fileType().acceptSystemIn().verifyCanRead())
                .setDefault("-")
                .nargs(1);
        parser.addArgument("-c", "--connect")
                .help("Enabled Ethernet card and connects to destination.")
                .metavar("<address:port>")
                .type(String.class)
                .setDefault("");
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Namespace namespace = null;
        try {
             namespace = parser.parseArgs(getParameters().getRaw().toArray(new String[0]));
        } catch (ArgumentParserException e) {
            parser.handleError(e);
            System.exit(1);
        }

        stage.setTitle("EMU Monitor");

        Platform.setImplicitExit(true);

        int scale = namespace.getInt("scale");
        int clockPeriod = namespace.getInt("clock");
        boolean isEncoded = !namespace.getBoolean("raw");
        boolean doubleBuffer = !namespace.getBoolean("no_double_buffer");
        String connect = namespace.getString("connect");
        File romFile = (File)namespace.getList("romFile").get(0);

        if (!connect.isEmpty())
            EthernetCard.init(connect);

        Monitor monitor = new Monitor(doubleBuffer, scale);
        Rectangle[] onScreen = monitor.getBuffer();
        Group root = new Group(onScreen);
        Scene s = new Scene(root, 64 * scale, 64 * scale, Color.BLACK);

        if (clockPeriod != 0) {
            Emulator emulator = Emulator.loadProgram(romFile, isEncoded);
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(emulator::executeStep, 1000000000, clockPeriod, TimeUnit.NANOSECONDS);
        } else {
            Thread thread = new Thread(() -> {
                try {
                    Emulator.runProgram(romFile, isEncoded);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException();
                }
            });

            thread.start();
        }


        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case UP:
                    DirectionPad.INSTANCE.upKeyDown();
                    break;
                case DOWN:
                    DirectionPad.INSTANCE.downKeyDown();
                    break;
                case LEFT:
                    DirectionPad.INSTANCE.leftKeyDown();
                    break;
                case RIGHT:
                    DirectionPad.INSTANCE.rightKeyDown();
                    break;
                case X:
                    DirectionPad.INSTANCE.xKeyDown();
                    break;
                case Y:
                    DirectionPad.INSTANCE.yKeyDown();
                    break;
            }
            event.consume();
        });
        stage.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            switch (event.getCode()) {
                case UP:
                    DirectionPad.INSTANCE.upKeyUp();
                    break;
                case DOWN:
                    DirectionPad.INSTANCE.downKeyUp();
                    break;
                case LEFT:
                    DirectionPad.INSTANCE.leftKeyUp();
                    break;
                case RIGHT:
                    DirectionPad.INSTANCE.rightKeyUp();
                    break;
                case X:
                    DirectionPad.INSTANCE.xKeyUp();
                    break;
                case Y:
                    DirectionPad.INSTANCE.yKeyUp();
                    break;
            }
            event.consume();
        });

        stage.setScene(s);
        stage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                Rectangle[] buffer = monitor.getBuffer();
                for (int x = 0; x < 64; x++) {
                    for (int y = 0; y < 64; y++) {
                        int i = (y * 64) + x;
                        onScreen[i].setFill(buffer[i].getFill());
                    }
                }
            }

        }.start();
    }
}
