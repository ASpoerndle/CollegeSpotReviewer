/**
 * TODO:
 * 1. Consider splitting up PictureObject (parent class) and then have children classes for image format type (PNGPicture, JPEGPicture, etc.)?
 */


package edu.elon.NearU.models;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class Picture {
    // This is intentional. Instead of four separate values, images will be packaged as a record class to keep data on an image together.
    public record ProcessedImage(byte[] data, int width, int height, String contentType) {}
    private static final int MAX_DIMENSION = 1080;
    private ProcessedImage processedImage;


    public ProcessedImage getProcessedImage() {
        return processedImage;
    }

    public void processNewPngImageUpload(InputStream input) throws IOException {
        BufferedImage resized = resizePngImage(readPngImage(input));
        byte[] png = transformUploadIntoPng(resized);

        // TODO: save to the database once that part of the project is figured out
        processedImage = new ProcessedImage(png, resized.getWidth(), resized.getHeight(), "image/png");
    }


    private static BufferedImage resizePngImage(BufferedImage original) {
        int longestSide = Math.max(original.getWidth(), original.getHeight());
        double scale = Math.min(1.0, (double) MAX_DIMENSION / longestSide);
        int width = (int) Math.round(original.getWidth() * scale);
        int height = (int) Math.round(original.getHeight() * scale);

        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = resized.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics.drawImage(original, 0, 0, width, height, null);
        graphics.dispose();
        return resized;
    }

    private static BufferedImage readPngImage(InputStream input) throws IOException {
        return ImageIO.read(input);
    }

    private static byte[] transformUploadIntoPng(BufferedImage image) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(image, "png", output);
        return output.toByteArray();
    }
}
