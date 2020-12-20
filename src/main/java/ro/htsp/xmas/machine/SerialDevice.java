package ro.htsp.xmas.machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SerialDevice implements Device {

    public static final SerialDevice INSTANCE = new SerialDevice();
    private static String buffer = "";

    public void write(int value) {
        System.out.print(Utils.toChar(value));
        System.out.flush();
    }

    public int read() {
        if (buffer.length() == 0) {
            return -1;
        }
        int value = Utils.toInteger(buffer.charAt(0));
        buffer = buffer.substring(1);
        return value;
    }

    public int readControl() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("> ");
            buffer = reader.readLine() + "\n";
            return buffer.length() & 0b111111;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
