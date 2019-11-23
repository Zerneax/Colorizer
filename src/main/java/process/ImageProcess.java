package process;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import model.DataImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageProcess {

    private final Logger logger = LoggerFactory.getLogger(ImageProcess.class);

    public ImageProcess() {
    }

    public BufferedImage openImage(File file) throws IOException {
        this.logger.info("Open image : " + file.getName());
        BufferedImage img = ImageIO.read(file);
        return img;

    }

    public List<DataImage> getRGBOfImage(BufferedImage img) {
        int height = img.getHeight();
        int width = img.getWidth();

        List<DataImage> dataImageList = new ArrayList<DataImage>();

        for(int w = 0; w < width; w ++) {
            for(int h = 0; h < height;  h ++) {
                int rgb = img.getRGB(w, h);
                int blue = rgb & 0xff;
                int green = (rgb & 0xff00) >> 8;
                int red = (rgb & 0xff0000) >> 16;
                int alpha = (rgb & 0xff000000) >> 24;

                DataImage dataImage = new DataImage();
                dataImage.setX(w);
                dataImage.setY(h);
                dataImage.setAlpha(alpha);
                dataImage.setRed(red);
                dataImage.setGreen(green);
                dataImage.setBlue(blue);

                dataImageList.add(dataImage);
            }
        }

        return dataImageList;
    }

    public File createNewInvertedImage(List<DataImage> dataImageList, BufferedImage oldImage, String newFileName) throws IOException {
        this.logger.info("Creating new inverted image : " + newFileName);

        File f = new File(newFileName);

        int width = oldImage.getWidth();
        int height = oldImage.getHeight();

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for(DataImage dataImage : dataImageList) {
            int rgb = dataImage.getAlpha() << 24
                    | (255 - dataImage.getRed()) << 16
                    | (255 - dataImage.getGreen()) << 8
                    | (255 - dataImage.getBlue());

            newImage.setRGB(dataImage.getX(), dataImage.getY(), rgb);
        }

        ImageIO.write(newImage, "png", f);

        return f;
    }

    public File createNewBlackAndWhiteImage(List<DataImage> dataImageList, BufferedImage oldImage, String newFileName) throws IOException {
        this.logger.info("Creating new black and white image : " + newFileName);

        File f = new File(newFileName);

        int width = oldImage.getWidth();
        int height = oldImage.getHeight();

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for(DataImage dataImage : dataImageList) {
            int red = dataImage.getRed();
            int green = dataImage.getGreen();
            int blue = dataImage.getBlue();
            int rgb = 0;

            int gray = (red + green + blue) / 3;
            rgb = dataImage.getAlpha() << 24
                    | gray << 16
                    | gray << 8
                    | gray;

            newImage.setRGB(dataImage.getX(), dataImage.getY(), rgb);
        }

        ImageIO.write(newImage, "png", f);

        return f;
    }


    public File createNewSepiaImage(List<DataImage> dataImageList, BufferedImage oldImage, String newFileName) throws IOException {
        this.logger.info("Creating new sepia image : " + newFileName);
        File f = new File(newFileName);

        int width = oldImage.getWidth();
        int height = oldImage.getHeight();

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for(DataImage dataImage : dataImageList) {
            int red = dataImage.getRed();
            int green = dataImage.getGreen();
            int blue = dataImage.getBlue();
            int rgb = 0;


            Double newRed = Math.min(255, (red * 0.393) + (green * 0.769) + (blue * 0.189));
            Double newGreen = Math.min(255, (red * 0.349) + (green * 0.686) + (blue * 0.168));
            Double newBlue = Math.min(255, (red * 0.272) + (green * 0.534) + (blue * 0.131));

            int gray = (red + green + blue) / 3;
            rgb = dataImage.getAlpha() << 24
                    | newRed.intValue() << 16
                    | newGreen.intValue() << 8
                    | newBlue.intValue();

            newImage.setRGB(dataImage.getX(), dataImage.getY(), rgb);
        }

        ImageIO.write(newImage, "png", f);

        return f;
    }



}
