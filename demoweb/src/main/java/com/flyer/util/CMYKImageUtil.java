
package com.flyer.util;

import org.apache.log4j.Logger;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class CMYKImageUtil {

    private static Logger log = Logger.getLogger(CMYKImageUtil.class);

    public static BufferedImage readImage(File file) throws IOException {
        BufferedImage bi = null;
        try {
            // 默认调用方法一
            bi = readImage1(file);
            if (bi == null) {// 如果方法一读取失败使用方法2
                bi = readImage2(file);
                if (bi != null) {
                    log.warn("CMYKImageUtil.readImage2 filePath:" + file.getPath());
                }
            }
        } catch (Exception e) {

        }
        return bi;
    }

    /**
     * 方法一：一般的cmyk
     * 
     * @param file
     * @return
     * @throws IOException
     */
    private static BufferedImage readImage1(File file) throws IOException {
        try {
            ImageInputStream input = ImageIO.createImageInputStream(file);
            Iterator<?> readers = ImageIO.getImageReaders(input);
            if (readers == null || !readers.hasNext()) {
                return null;
            }

            ImageReader reader = (ImageReader) readers.next();
            reader.setInput(input);
            String format = reader.getFormatName();

            if ("JPEG".equalsIgnoreCase(format) || "JPG".equalsIgnoreCase(format)) {
                try {
                    IIOMetadata metadata = reader.getImageMetadata(0);
                    String metadataFormat = metadata.getNativeMetadataFormatName();
                    IIOMetadataNode iioNode = (IIOMetadataNode) metadata.getAsTree(metadataFormat);

                    NodeList children = iioNode.getElementsByTagName("app14Adobe");
                    if (children.getLength() > 0) {
                        try {
                            iioNode = (IIOMetadataNode) children.item(0);
                            int transform = Integer.parseInt(iioNode.getAttribute("transform"));
                            Raster raster = reader.readRaster(0, reader.getDefaultReadParam());
                            if (input != null) {
                                input.close();
                            }
                            reader.dispose();

                            return createJPEG4(raster, transform);
                        } catch (Exception e) {
                        }
                    }

                } catch (Exception e) {
                }
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return null;
    }

    /**
     * 默认方法一如果返回null 则试一下直接使用 xform（createJPEG4方法的参数)为2创建图片
     * 
     * @param file
     * @return
     * @throws IOException
     */
    private static BufferedImage readImage2(File file) throws IOException {
        try {
            ImageInputStream input = ImageIO.createImageInputStream(file);
            Iterator<?> readers = ImageIO.getImageReaders(input);
            if (readers == null || !readers.hasNext()) {
                return null;
            }

            ImageReader reader = (ImageReader) readers.next();
            reader.setInput(input);
            String format = reader.getFormatName();

            if ("JPEG".equalsIgnoreCase(format) || "JPG".equalsIgnoreCase(format)) {
                try {
                    Raster raster = reader.readRaster(0, reader.getDefaultReadParam());
                    return createJPEG4(raster, 2);
                } catch (Exception e) {
                }
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return null;
    }

    private static BufferedImage createJPEG4(Raster raster, int xform) {
        try {
            int w = raster.getWidth();
            int h = raster.getHeight();
            byte[] rgb = new byte[w * h * 3];

            if (xform == 2) { // YCCK --
                // Adobe
                float[] Y = raster.getSamples(0, 0, w, h, 0, (float[]) null);
                float[] Cb = raster.getSamples(0, 0, w, h, 1, (float[]) null);
                float[] Cr = raster.getSamples(0, 0, w, h, 2, (float[]) null);
                float[] K = raster.getSamples(0, 0, w, h, 3, (float[]) null);

                for (int i = 0, imax = Y.length, base = 0; i < imax; i++, base += 3) {
                    float k = 220 - K[i], y = 255 - Y[i], cb = 255 - Cb[i], cr = 255 - Cr[i];

                    double val = y + 1.402 * (cr - 128) - k;
                    val = (val - 128) * .65f + 128;
                    rgb[base] = val < 0.0 ? (byte) 0 : val > 255.0 ? (byte) 0xff : (byte) (val + 0.5);

                    val = y - 0.34414 * (cb - 128) - 0.71414 * (cr - 128) - k;
                    val = (val - 128) * .65f + 128;
                    rgb[base + 1] = val < 0.0 ? (byte) 0 : val > 255.0 ? (byte) 0xff : (byte) (val + 0.5);

                    val = y + 1.772 * (cb - 128) - k;
                    val = (val - 128) * .65f + 128;
                    rgb[base + 2] = val < 0.0 ? (byte) 0 : val > 255.0 ? (byte) 0xff : (byte) (val + 0.5);
                }

            } else {
                // assert xform==0: xform;
                // CMYK
                int[] C = raster.getSamples(0, 0, w, h, 0, (int[]) null);
                int[] M = raster.getSamples(0, 0, w, h, 1, (int[]) null);
                int[] Y = raster.getSamples(0, 0, w, h, 2, (int[]) null);
                int[] K = raster.getSamples(0, 0, w, h, 3, (int[]) null);

                for (int i = 0, imax = C.length, base = 0; i < imax; i++, base += 3) {
                    int c = 255 - C[i];
                    int m = 255 - M[i];
                    int y = 255 - Y[i];
                    int k = 255 - K[i];
                    float kk = k / 255f;

                    rgb[base] = (byte) (255 - Math.min(255f, c * kk + k));
                    rgb[base + 1] = (byte) (255 - Math.min(255f, m * kk + k));
                    rgb[base + 2] = (byte) (255 - Math.min(255f, y * kk + k));
                }
            }

            raster = Raster.createInterleavedRaster(new DataBufferByte(rgb, rgb.length), w, h, w * 3, 3, new int[] { 0,
                    1, 2 }, null);

            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
            ColorModel cm = new ComponentColorModel(cs, false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
            return new BufferedImage(cm, (WritableRaster) raster, true, null);
        } catch (Exception e) {
            return null;
        }
    }
}
