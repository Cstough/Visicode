package VisiCode.Internals;

public class Color {
    private byte[] comps;

    public Color(byte[] comps) {
        this.comps = comps;
    }

    public byte[] getBytes() {
        return comps;
    }

    private static final byte[] BLACK_b = new byte[] {(byte) 0xff, 0x00, 0x00, 0x00};
    private static final byte[] WHITE_b = new byte[] {(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff};

    public static final Color BLACK = new Color(BLACK_b);
    public static final Color WHITE = new Color(WHITE_b);
}
