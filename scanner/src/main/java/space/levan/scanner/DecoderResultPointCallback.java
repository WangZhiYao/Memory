package space.levan.scanner;

import space.levan.scanner.zxing.ResultPoint;
import space.levan.scanner.zxing.ResultPointCallback;

/**
 * ResultPointCallback delegating the ResultPoints to a decoder.
 */
public class DecoderResultPointCallback implements ResultPointCallback {
    private Decoder decoder;

    public DecoderResultPointCallback(Decoder decoder) {
        this.decoder = decoder;
    }

    public DecoderResultPointCallback() {
    }

    public Decoder getDecoder() {
        return decoder;
    }

    public void setDecoder(Decoder decoder) {
        this.decoder = decoder;
    }

    @Override
    public void foundPossibleResultPoint(ResultPoint point) {
        if (decoder != null) {
            decoder.foundPossibleResultPoint(point);
        }
    }
}
