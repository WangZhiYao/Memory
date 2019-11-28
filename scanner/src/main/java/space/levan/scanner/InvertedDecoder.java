package space.levan.scanner;

import space.levan.scanner.zxing.BinaryBitmap;
import space.levan.scanner.zxing.LuminanceSource;
import space.levan.scanner.zxing.Reader;
import space.levan.scanner.zxing.common.HybridBinarizer;

/**
 * Created by leighmd on 11/2/16.
 */
public class InvertedDecoder extends Decoder {

    /**
     * Create a new Decoder with the specified Reader.
     * <p/>
     * It is recommended to use an instance of MultiFormatReader in most cases.
     *
     * @param reader the reader
     */
    public InvertedDecoder(Reader reader) {
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

        return new BinaryBitmap(new HybridBinarizer(source.invert()));
    }
}
