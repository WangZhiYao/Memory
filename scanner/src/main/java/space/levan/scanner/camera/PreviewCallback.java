package space.levan.scanner.camera;


import space.levan.scanner.SourceData;

/**
 * Callback for camera previews.
 */
public interface PreviewCallback {
    void onPreview(SourceData sourceData);

    void onPreviewError(Exception e);
}
