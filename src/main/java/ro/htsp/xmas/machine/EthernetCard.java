package ro.htsp.xmas.machine;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class EthernetCard implements ControllableDevice {
    public static EthernetCard INSTANCE;

    private final InputStream inputStream;
    private final OutputStream outputStream;

    private final byte[] buffer = new byte[64];
    private ByteBuffer byteBuffer = ByteBuffer.allocate(0);

    public EthernetCard(String address, int port) throws IOException {
        EthernetCard.INSTANCE = this;
        Arrays.fill(buffer, (byte) 0);
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
        return byteBuffer.get() & 0b111_111;
    }

    @Override
    public int readControl() {
        try {
            if (!byteBuffer.hasRemaining()) {
                int available = inputStream.available();
                if (available == 0) {
                    return 0;
                }
                byteBuffer = ByteBuffer.wrap(inputStream.readNBytes(available));
            }
            return Math.min(byteBuffer.remaining(), 63);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
