package ro.htsp.xmas.machine;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Monitor {
    private static final String[] COLOR_MAP = new String[]{
            "#000000","#000055","#0000aa","#0000ff","#005500","#005555","#0055aa","#0055ff","#00aa00","#00aa55",
            "#00aaaa","#00aaff","#00ff00","#00ff55","#00ffaa","#00ffff","#550000","#550055","#5500aa","#5500ff",
            "#555500","#555555","#5555aa","#5555ff","#55aa00","#55aa55","#55aaaa","#55aaff","#55ff00","#55ff55",
            "#55ffaa","#55ffff","#aa0000","#aa0055","#aa00aa","#aa00ff","#aa5500","#aa5555","#aa55aa","#aa55ff",
            "#aaaa00","#aaaa55","#aaaaaa","#aaaaff","#aaff00","#aaff55","#aaffaa","#aaffff","#ff0000","#ff0055",
            "#ff00aa","#ff00ff","#ff5500","#ff5555","#ff55aa","#ff55ff","#ffaa00","#ffaa55","#ffaaaa","#ffaaff",
            "#ffff00","#ffff55","#ffffaa","#ffffff"
    };

    public static Monitor INSTANCE;
    private final Rectangle[] buffer = new Rectangle[64 * 64];
    private final Rectangle[] onScreen = new Rectangle[64 * 64];
    private final int scale;
    private final boolean doubleBuffer;

    public Monitor(boolean doubleBuffer, int scale) {
        this.scale = scale;
        this.doubleBuffer = doubleBuffer;

        if (INSTANCE == null)
            INSTANCE = this;

        initBuffer(buffer);
        initBuffer(onScreen);
    }

    private void initBuffer(Rectangle[] buffer) {
        for (int x = 0; x < 64; x++) {
            for (int y = 0; y < 64; y++) {
                int i = (y * 64) + x;
                buffer[i] = new Rectangle(scale, scale, Color.BLACK);
                buffer[i].setTranslateX(scale * x);
                buffer[i].setTranslateY(scale * y);
            }
        }
    }

    public Rectangle[] copyBuffer(Rectangle[] src) {
        Rectangle[] copy = new Rectangle[64 * 64];
        for (int x = 0; x < 64; x++) {
            for (int y = 0; y < 64; y++) {
                int i = (y * 64) + x;
                copy[i] = new Rectangle(scale, scale, src[i].getFill());
                copy[i].setTranslateX(scale * x);
                copy[i].setTranslateY(scale * y);
            }
        }
        return copy;
    }

    public synchronized Rectangle[] getBuffer() {
        return copyBuffer(onScreen);
    }

    public synchronized void drawPixel(int x, int y, int value) {
        buffer[(y * 64) + x].setFill(Color.valueOf(COLOR_MAP[value]));
        if (!doubleBuffer)
            onScreen[(y * 64) + x].setFill(Color.valueOf(COLOR_MAP[value]));
    }

    public synchronized void draw() {
        if (doubleBuffer) {
            for (int x = 0; x < 64; x++) {
                for (int y = 0; y < 64; y++) {
                    int i = (y * 64) + x;
                    onScreen[i].setFill(buffer[i].getFill());
                }
            }
        }
    }
}
