package ro.htsp.xmas.machine;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EthernetCard implements ControllableDevice {
    public static EthernetCard INSTANCE;

    private final InputStream inputStream;
    private final OutputStream outputStream;

    public EthernetCard(String address, int port) throws IOException {
        EthernetCard.INSTANCE = this;
        Socket socket = new Socket(address, port);
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    public static void init(String connect) throws IOException {
        if (!connect.contains(":")) {
            System.err.println("ERROR: Port not specified in connection address!");
            System.exit(1);
        }

        String[] parts = connect.split(":");
        new EthernetCard(parts[0], Integer.parseInt(parts[1]));
    }

    @Override
    public void write(int value) {
        try {
            outputStream.write(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int read() {
        try {
            return inputStream.read() & 0b111_111;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int readControl() {
        try {
            return inputStream.available() & 0b111_111;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
