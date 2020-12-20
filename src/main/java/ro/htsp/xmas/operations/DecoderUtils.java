package ro.htsp.xmas.operations;

public class DecoderUtils {

    public static String getConditionPrefix(Boolean condition) {
        if (condition == null) {
            return "";
        } else if (condition) {
            return "+";
        }
        return "-";
    }

    public static void printInstructionRRR(String name, Boolean condition, int rd, int ra, int rb) {
        System.out.printf(
                "%s%s r%d, r%d, r%d\n",
                getConditionPrefix(condition),
                name, rd, ra, rb
        );
    }

    public static void printInstructionRRI(String name, Boolean condition, int rd, int ra, int ib) {
        System.out.printf(
                "%s%s r%d, r%d, %d\n",
                getConditionPrefix(condition),
                name, rd, ra, ib
        );
    }

    public static void printInstructionCMP(Boolean condition, int cm, int oa, int ob) {
        String a, b, cs = "";

        if ((cm & 030) == 030) {
            cm -= 030;
            a = Integer.toString(oa);
            b = String.format("r%d", ob);
        } else if ((cm & 020) == 020) {
            cm -= 020;
            a = Integer.toString(ob);
            b = String.format("r%d", oa);
        } else if ((cm & 010) == 010) {
            cm -= 010;
            a = String.format("r%d", ob);
            b = String.format("r%d", oa);
        } else {
            a = String.format("r%d", oa);
            b = String.format("r%d", ob);
        }

        switch (cm) {
            case 0:
                System.out.println("cmptr");
                return;
            case 1:
                System.out.println("cmpfa");
                return;
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

        System.out.printf(
                "%scmp%s %s, %s",
                getConditionPrefix(condition), cs, a, b
        );
    }

}
