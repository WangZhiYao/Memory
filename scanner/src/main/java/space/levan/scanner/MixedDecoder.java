package space.levan.scanner;

import space.levan.scanner.zxing.BinaryBitmap;
import space.levan.scanner.zxing.LuminanceSource;
import space.levan.scanner.zxing.Reader;
import space.levan.scanner.zxing.common.HybridBinarizer;

/**
 * Decoder that performs alternating scans in normal mode and inverted mode.
 */
public class MixedDecoder extends Decoder {
    private boolean isInverted = true;

    /**
     * Create a new Decoder with the specified Reader.
     * <p/>
     * It is recommended to use an instance of MultiFormatReader in most cases.
     *
     * @param reader the reader
     */
    public MixedDecoder(Reader reader) {
        super(reader);
    }

    /**
     * Given an image source, convert to a binary bitmap.
     * <p>
     * Override this to use a custom binarizer.
     *
     * @param source the image source
     * @return a BinaryBitmap
     */
    @Override
    protected BinaryBitmap toBitmap(LuminanceSource source) {
        if (isInverted) {
            isInverted = false;
            return new BinaryBitmap(new HybridBinarizer(source.invert()));
        } else {
            isInverted = true;
            return new BinaryBitmap(new HybridBinarizer(source));
        }
    }

}
