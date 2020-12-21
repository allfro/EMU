package ro.htsp.xmas.operations;

public class InstructionDecoder {

    public static String getConditionPrefix(Boolean condition) {
        if (condition == null) {
            return "";
        } else if (condition) {
            return "+";
        }
        return "-";
    }

    public static String decodeRRR(Boolean condition, String name, int rd, int ra, int rb) {
        return String.format(
                "%s%s r%d, r%d, r%d",
                getConditionPrefix(condition),
                name, rd, ra, rb
        );
    }

    public static String decodeRRI(Boolean condition, String name, int rd, int ra, int ib) {
        return String.format(
                "%s%s r%d, r%d, %d",
                getConditionPrefix(condition),
                name, rd, ra, ib
        );
    }

    public static String decodeRIR(Boolean condition, String name, int rd, int ia, int rb) {
        return String.format(
                "%s%s r%d, r%d, %d",
                getConditionPrefix(condition),
                name, rd, ia, rb
        );
    }

    public static String decodeCompare(Boolean condition, int cm, int oa, int ob) {
        String a, b, cs = "";

        if ((cm & 24) == 24) {
            cm -= 24;
            a = Integer.toString(oa);
            b = String.format("r%d", ob);
        } else if ((cm & 16) == 16) {
            cm -= 16;
            a = Integer.toString(ob);
            b = String.format("r%d", oa);
        } else if ((cm & 8) == 8) {
            cm -= 8;
            a = String.format("r%d", ob);
            b = String.format("r%d", oa);
        } else {
            a = String.format("r%d", oa);
            b = String.format("r%d", ob);
        }

        switch (cm) {
            case 0:
                return "cmptr";
            case 1:
                return "cmpfa";
            case 2:
                cs = "eq";
                break;
            case 3:
                cs = "ne";
                break;
            case 4:
                cs = "ls";
                break;
            case 5:
                cs = "gs";
                break;
            case 6:
                cs = "ul";
                break;
            case 7:
                cs = "ug";
                break;
        }

        return String.format(
                "%scmp%s %s, %s",
                getConditionPrefix(condition), cs, a, b
        );
    }

    public static String decodeJumpDown(Boolean condition, int lab, int rc) {
        return String.format(
                "%sjdn %d, r%d",
                getConditionPrefix(condition),
                lab, rc
        );
    }

    public static String decodeJumpUp(Boolean condition, int lab, int rc) {
        return String.format(
                "%sjup %d, r%d",
                getConditionPrefix(condition),
                lab, rc
        );
    }

    public static String decodeLabel(Boolean condition, int lab, int c) {
        return String.format(
                "%slbl %d, %d",
                getConditionPrefix(condition),
                lab, c
        );
    }

    public static String decodeLoad(Boolean condition, int rd, int ra, int ib) {
        return String.format(
                "%sld r%s, [r%d + %d]",
                getConditionPrefix(condition),
                rd,
                ra,
                ib
        );
    }

    public static String decodeStore(Boolean condition, int rs, int ra, int ib) {
        return String.format(
                "%sst [r%d + %d], r%s",
                getConditionPrefix(condition),
                ra,
                ib,
                rs
        );
    }

    public static String decodeFixedPointMultiply(Boolean condition, int rd, int ra, int pr) {
        if ((pr & 16) == 16) {
            return String.format(
                    "%sfms/%d r%s, r%s",
                    getConditionPrefix(condition),
                    pr - 16,
                    rd,
                    ra
            );
        }
        return String.format(
                "%sfmu/%d r%s, r%s",
                getConditionPrefix(condition),
                pr,
                rd,
                ra
        );
    }

    public static String decodeIO(Boolean condition, int rd, int ix, int rs) {
        String deviceName = Integer.toString(ix);
        if (ix == 0) {
            deviceName = "SERIAL_INCOMING";
        } else if (ix == 1) {
            deviceName = "SERIAL_READ";
        } else if (ix == 2) {
            deviceName = "SERIAL_WRITE";
        } else if (ix == 3) {
            deviceName = "CLOCK_LO_CS";
        } else if (ix == 4) {
            deviceName = "CLOCK_HI_CS";
        } else if (ix == 8) {
            deviceName = "ENET_INCOMING";
        } else if (ix == 9) {
            deviceName = "ENET_RECV";
        } else if (ix == 10) {
            deviceName = "ENET_SEND";
        } else if (ix == 16) {
            deviceName = "MEM_ADDR_HI";
        } else if (ix == 17) {
            deviceName = "MEM_ADDR_MID";
        } else if (ix == 18) {
            deviceName = "MEM_ADDR_LO";
        } else if (ix == 19) {
            deviceName = "MEM_READ";
        } else if (ix == 20) {
            deviceName = "MEM_WRITE";
        } else if (ix == 21) {
            deviceName = "GPU_X";
        } else if (ix == 22) {
            deviceName = "GPU_Y";
        } else if (ix == 23) {
            deviceName = "GPU_DRAW";
        } else if (ix == 24) {
            deviceName = "DPAD";
        }

        return String.format(
                "%sio r%d, %s, %d",
                getConditionPrefix(condition),
                rd,
                deviceName,
                rs
        );
    }
}
