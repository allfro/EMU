package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.*;

public class IO implements Operation {

    public static final int OPCODE = 18;
    public static final Operation INSTANCE = new IO();

    @Override
    public int getOpCode() {
        return OPCODE;
    }

    @Override
    public void execute(int rd, int ix, int rs) {

        if (ix == 0) {
            RAM.write(rd, SerialDevice.INSTANCE.readControl());
        } else if (ix == 1) {
            RAM.write(rd, SerialDevice.INSTANCE.read());
        } else if (ix == 2) {
            SerialDevice.INSTANCE.write(RAM.read(rs));
            RAM.write(rd, -1);
        } else if (ix == 3) {
            RAM.write(rd, CPU.getClockLow());
            if (RAM.read(rs) != 0)
                CPU.resetClock();
        } else if (ix == 4) {
            RAM.write(rd, CPU.getClockHigh());
            if (RAM.read(rs) != 0)
                CPU.resetClock();
        } else if (ix == 8) {
            RAM.write(rd, EthernetCard.INSTANCE.readControl());
        }  else if (ix == 9) {
            RAM.write(rd, EthernetCard.INSTANCE.read());
        }  else if (ix == 10) {
            EthernetCard.INSTANCE.write(RAM.read(rs));
            RAM.write(rd, -1);
        } else if (ix == 16) {
            ExtendedRAM.INSTANCE.writeHigh(RAM.read(rs));
            RAM.write(rd, -1);
        } else if (ix == 17) {
            ExtendedRAM.INSTANCE.writeMiddle(RAM.read(rs));
            RAM.write(rd, -1);
        } else if (ix == 18) {
            ExtendedRAM.INSTANCE.writeLow(RAM.read(rs));
            RAM.write(rd, -1);
        } else if (ix == 19) {
            RAM.write(rd, ExtendedRAM.INSTANCE.read());
        } else if (ix == 20) {
            ExtendedRAM.INSTANCE.write(RAM.read(rs));
            RAM.write(rd, -1);
        } else if (ix == 21) {
            GPU.INSTANCE.writeX(RAM.read(rs));
            RAM.write(rd, -1);
        } else if (ix == 22) {
            GPU.INSTANCE.writeY(RAM.read(rs));
            RAM.write(rd, -1);
        } else if (ix == 23) {
            GPU.INSTANCE.write(RAM.read(rs));
            RAM.write(rd, -1);
        } else if (ix == 24) {
            RAM.write(rd, DirectionPad.INSTANCE.read());
        }
    }
}
