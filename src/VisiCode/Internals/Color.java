package VisiCode.Internals;

public class Color {
    private byte[] comps;

    public Color(byte[] comps) {
        this.comps = comps;
    }
    public Color(float a, float b, float g, float r) {
        //TODO: Add this...
    }

    public byte[] getBytes() {
        return comps;
    }

    private static final byte[] BLACK_b = new byte[] {(byte) 0xff, 0x00, 0x00, 0x00};
    private static final byte[] WHITE_b = new byte[] {(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff};
    private static final byte[] GREEN_b = new byte[] {(byte) 0xff, 0x00, (byte) 0xff, 0x00};
    private static final byte[] BLUE_b = new byte[] {(byte) 0xff, 0x00, 0x00, (byte) 0xff};
    private static final byte[] RED_b = new byte[] {(byte) 0xff, (byte) 0xff, 0x00, 0x00};

    public static final Color BLACK = new Color(BLACK_b);
    public static final Color WHITE = new Color(WHITE_b);
    public static final Color GREEN = new Color(GREEN_b);
    public static final Color BLUE = new Color(BLUE_b);
    public static final Color RED = new Color(RED_b);
}
