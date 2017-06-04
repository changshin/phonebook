package com.chang.ng.phone.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class ImageUtil {

	private static final Logger logger = LogManager.getLogger(ImageUtil.class);

	// Get File Extension
	public static String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	// Get File Name
	public static String getFileName(String srcFileName) {

		String tarfileName = srcFileName.substring(0,
				srcFileName.lastIndexOf("."));

		return tarfileName;
	}

	// Get File Name
	public static String getPathWithFileName(String path, String filename,String fileextension) {
		return path + Constants.FILE_SEPARATOR + filename + "." + fileextension;
	}

	// upload image to gallery folder
	public static File uploadImage(MultipartFile uploadFile, String path) {

		try {

			File file = new File(path);
			uploadFile.transferTo(file);
			return file;

		} catch (IllegalStateException | IOException e) {
			logger.error("Upload has failed :" + path, e);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Upload has failed :" + path, e);

		}
		return null;
	}

	
	//  does not support AlhoaChannel image
	public static void fixAlphaChannel(File file,String fileType) {
		BufferedImage bufferImage;
		try {
			bufferImage = ImageIO.read(file);
			if (bufferImage.getColorModel().hasAlpha()) {
				BufferedImage copy = new BufferedImage(bufferImage.getWidth(), bufferImage.getHeight(), BufferedImage.TYPE_INT_RGB);
				Graphics2D g2d = copy.createGraphics();
				g2d.setColor(new Color(242,242,242) ); // Light grey (95%)
				//g2d.setColor(new Color(230,230,230) ); // Light grey (90%)
				g2d.fillRect(0, 0, copy.getWidth(), copy.getHeight());
				g2d.drawImage(bufferImage, 0, 0, null);
				g2d.dispose();
		    	ImageIO.write(copy, fileType, file);
		    	logger.info("fixAlphaChannel :" + file.getName());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// create 150X150 thumbnail ...
	public static String createThumbnail(File file, String path, String fileName, String fileExtension) {

		return resizeAndUpload(file, path, fileName, fileExtension, 150, 150);

	}

	
	// better quality
	public static void createThumbnail(String sourceImagePath,String thumbnailPath) throws Exception {
		
		int height = 300;
		int width = 300;
		File inFile = new File(sourceImagePath);
		BufferedImage origBuffImg = ImageIO.read(inFile);
		int fileWidth = origBuffImg.getWidth();
		int fileHeight = origBuffImg.getHeight();
		float ratio = ((float) fileWidth / (float) fileWidth);
		if  ( fileWidth < width ) {
			width = fileWidth;
		}
		if  ( fileHeight < height ) {
			height = fileHeight;
		}
		width =  (int) (ratio * width);
		Thumbnails.of(inFile)
		.size(width,height)
		.toFile(new File(thumbnailPath));

	}
	
	public static void createThumbnail(String sourceImagePath,String thumbnailPath,int height, int width) throws Exception {
		
		File inFile = new File(sourceImagePath);
		BufferedImage origBuffImg = ImageIO.read(inFile);
		int fileWidth = origBuffImg.getWidth();
		int fileHeight = origBuffImg.getHeight();
		float ratio = ((float) fileWidth / (float) fileWidth);
		if  ( fileWidth < width ) {
			width = fileWidth;
		}
		if  ( fileHeight < height ) {
			height = fileHeight;
		}
		width =  (int) (ratio * width);
		Thumbnails.of(inFile)
		.size(width,height)
		.toFile(new File(thumbnailPath));

	}

	public static void createThumbnail(File inFile,String thumbnailPath,int height, int width) throws Exception {
		
		BufferedImage origBuffImg = ImageIO.read(inFile);
		int fileWidth = origBuffImg.getWidth();
		int fileHeight = origBuffImg.getHeight();
		float ratio = ((float) fileWidth / (float) fileWidth);
		if  ( fileWidth < width ) {
			width = fileWidth;
		}
		if  ( fileHeight < height ) {
			height = fileHeight;
		}
		width =  (int) (ratio * width);
		Thumbnails.of(inFile)
		.size(width,height)
		.toFile(new File(thumbnailPath));

	}

	// Will be removed after testing
	public static void createThumbnailBackup(String sourceImagePath,String thumbnailPath) throws Exception {
		
		BufferedImage origBuffImg = ImageIO.read(new File(sourceImagePath));
		int type = origBuffImg.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : origBuffImg.getType();
		int width = origBuffImg.getWidth();
		int height = origBuffImg.getHeight();
		float ratio = ((float) width / (float) height);
		//System.out.println("createThumbnail(),ratio="+ratio);
		int tbHeight = 200;
		int tbWidth = (int) (ratio * 200);
		BufferedImage resizedBuffImg = new BufferedImage(tbWidth, tbHeight,	type);
		Graphics2D g = resizedBuffImg.createGraphics();
		g.drawImage(origBuffImg, 0, 0, tbWidth, tbHeight, null);
		g.dispose();
		
		ImageIO.write(resizedBuffImg, "png", new File(thumbnailPath));

	}

	


	// Resize image
	public static String resizeAndUpload(File file, String path,
			String fileName, String fileExtension, int width, int height) {

		try {

			// Create Thumbnail
			StringBuffer newPath = new StringBuffer();
			newPath.append(path).append(Constants.FILE_SEPARATOR)
					.append(fileName).append("_").append(width).append("x")
					.append(height).append(".").append(fileExtension);

			Thumbnails.of(file)
					.size(width,height)
					.toFile(new File(newPath.toString()));

			return newPath.toString();

		} catch (IllegalStateException | IOException e) {
			logger.error("ImageUtil:resizeAndUpload" + path + ":" + fileName, e);
		} catch (Exception e) {
			logger.error("ImageUtil:resizeAndUpload" + path + ":" + fileName, e);

		}
		return null;
	}

	// create Thumbnail for same pic
	public static String resizeImage(File file, String newPath, int width) {

		try {
			// Create Thumbnail
			Thumbnails.of(file)
				.size(width,width)
				.toFile(new File(newPath));

			return newPath;

		} catch (IllegalStateException | IOException e) {
			logger.error("ImageUtil:resizeAndUpload" + newPath + ":", e);
		} catch (Exception e) {
			logger.error("ImageUtil:resizeAndUpload" + newPath + ":", e);

		}
		return null;
	}

	public static int getRandomNumberInRange(int min, int max) {

		Random r = new Random();
		while (true) {
			int num = r.nextInt((max - min) + 1) + min;
			if  ( num >= min && num <= max ) {
				return num;
			}
		}
	}
	
	public static int getRandomNumber(int number) {
		Random randomGenerator = new Random();
		int value = randomGenerator.nextInt(number);

		for (;;) {
			if (value == 0) {
				value = randomGenerator.nextInt(number);
			} else
				break;
		}

		return randomGenerator.nextInt(number);
	}

	public static int getBorkerMarkerRandomNumber(int number) {
		int index = 0;
		while (true) {
			index = getRandomNumber(number);
			break;
		}
		return index;
	}

	// Crop Image
	private static byte[] cropImage(BufferedImage src, String extension,Rectangle rect) throws Exception {
		BufferedImage cropedImage = src.getSubimage(0, 0, rect.width,rect.height);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(cropedImage, extension, baos);

		return baos.toByteArray();
	}

	
	public static void processCroppingOfImage(String sourceFileName,String targetFileName, Rectangle rect) throws Exception {
		BufferedImage sourceImage = ImageIO.read(new File(sourceFileName));
		BufferedImage croppedImage = cropImage(sourceImage, rect);
		String extension = getExtension(sourceFileName);
		//extension = "png";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(croppedImage, extension, baos);
		byte[] imageBytes = baos.toByteArray();
		//byte[] b = cropImage(sourceImage, extension, rect);
		saveFile(targetFileName, imageBytes);

	}

	// Set Borders
	private static byte[] setBorder(String fileName, Color color) throws Exception {

		BufferedImage im = ImageIO.read(new File(fileName));
		int x = 0, y = 0;
		int width = im.getWidth();
		int height = im.getHeight();

		Graphics2D ig2 = im.createGraphics();
		ig2.setColor(color);
		ig2.setStroke(new BasicStroke(10));

		ig2.draw(new Rectangle2D.Double(x, y, width, height));

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(im, "png", baos);

		return baos.toByteArray();

	}

	public static boolean saveFile(String targetFileName, String fileExtension,	BufferedImage bufferedImage) {
		boolean returnValue = false;

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, fileExtension, baos);

			byte[] imageBytes = baos.toByteArray();

			FileOutputStream fos = new FileOutputStream(targetFileName);
			fos.write(imageBytes);
			fos.close();

			returnValue = true;
		} catch (Exception e) {
			logger.error("ImageUtil:saveFile" + targetFileName + ":" + fileExtension, e);
		}

		return returnValue;
	}

	public static boolean saveFile(String targetFileName, byte[] saveBytes) {
		boolean returnValue = false;

		try {

			FileOutputStream fos = new FileOutputStream(targetFileName);
			fos.write(saveBytes);
			fos.close();

		} catch (Exception e) {
			logger.error("ImageUtil:saveFile" + targetFileName, e);
		}

		return returnValue;
	}

	public static void setImageBorder(String sourceFileName,String targetFileName, Color color) {
		try {
			byte[] b = setBorder(sourceFileName, color);

			saveFile(targetFileName, b);
		} catch (Exception e) {
			logger.error("ImageUtil:setImageBorder" + targetFileName + ":"	+ sourceFileName, e);
		}
	}

	public static boolean copyBinaryFile(String sourceFileName,	String targetFileName) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		boolean returnValue = false;

		try {

			fis = new FileInputStream(sourceFileName);
			fos = new FileOutputStream(targetFileName);

			byte[] buffer = new byte[1024];
			int noOfBytes = 0;

			while ((noOfBytes = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, noOfBytes);
			}

			returnValue = true;
		} catch (IOException e) {
			logger.error("ImageUtil:copyBinaryFile" + targetFileName + ":" 	+ sourceFileName, e);
		} finally {
			// close the streams using close method
			try {
				if (fis != null) {
					fis.close();
				}

				if (fos != null) {
					fos.close();
				}
			} catch (IOException ioe) {
				logger.error("Error while closing stream: " + ioe);
			}
		}
		return returnValue;
	}

	public static BufferedImage readImage(final String fileLocation) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(fileLocation));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return img;
	}

	// Crop Image
	private static BufferedImage cropImage(BufferedImage src, Rectangle rect) 	throws Exception {

		BufferedImage cropedImage = src.getSubimage(rect.x, rect.y, rect.width, rect.height);

		return cropedImage;
	}
	//frameImageUrl, frame.getBorderColor(), originalFile, updatedImageUrl,	croppingRect);
	public static void processCroppingOfImage(String borderFileName,
			String borderColor, String sourceFileName, String borderedImage, Rectangle rect) throws Exception {
		
		BufferedImage sourceImage = ImageIO.read(new File(sourceFileName));

		String extension = getExtension(sourceFileName);
		BufferedImage croppedImage = cropImage(sourceImage, rect);
		sourceImage.flush();
		// ImageIO.write(croppedImage, extension, )

		// Load border File Name
		BufferedImage borderWatermarkImage = ImageIO.read(new File(	borderFileName));
		int borderWidth = 300;
		int borderHeight = 300;
	

		BufferedImage scaledBorderWatermarkImage = getScaledImage(borderWatermarkImage, borderWidth, borderHeight);
		borderWatermarkImage.flush();

		// Color Border
		BufferedImage colorBorderWatermarkImage = replaceColor(scaledBorderWatermarkImage, borderColor);
		scaledBorderWatermarkImage.flush();

		// Scale cropped Image to size of Border
		BufferedImage scaledCroppedImage = getScaledImage(croppedImage,	borderWidth, borderHeight);
		croppedImage.flush();

		// Overlay Image
		// resize the original image to the size of border and initializes
		// necessary graphic properties
		Graphics2D g2d = (Graphics2D) scaledCroppedImage.getGraphics();

		// paints the image watermark
		g2d.drawImage(colorBorderWatermarkImage, 0, 0, borderWidth,	borderHeight, null);
		colorBorderWatermarkImage.flush();
		g2d.dispose();

		String targetFile = getFileName(borderedImage);
		//	2016-02-23, does not overwrite the borderedImagfe file, So create new temp file and then rename
		String newTargetFileName = targetFile + "-tmp.png";

		// Save it to target File Name
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(scaledCroppedImage, extension, baos);
		scaledCroppedImage.flush();
		byte[] imageBytes = baos.toByteArray();

		FileOutputStream borderedFrameStream = new FileOutputStream(newTargetFileName);
		borderedFrameStream.write(imageBytes);
		borderedFrameStream.close();

		// Let us save the source image too, not just the bordered one.
		ByteArrayOutputStream frameByteStream = new ByteArrayOutputStream();
		ImageIO.write(croppedImage, extension, frameByteStream);
		croppedImage.flush();
		byte[] frameImageBytes = frameByteStream.toByteArray();
		FileOutputStream sourceFrameStream = new FileOutputStream(
				CommonUtils.concat(targetFile,sourceFileName.substring(sourceFileName.lastIndexOf(".") , sourceFileName.length())));
		sourceFrameStream.write(frameImageBytes);
		sourceFrameStream.close();
		
		//	2016-02-23, does not overwrite the borderedImagfe file, So create new temp file and then rename
		File file = new File(borderedImage);
		File newFile = new File(newTargetFileName);
		newFile.renameTo(file);


	}

	// image crop with frame border
	public static void processCroppingOfImage(String borderFileName, String borderColor, String sourceFileName, String newFrameFile
			, Rectangle rect,int borderWidth,int borderHeight) throws Exception {
		
		BufferedImage sourceImage = ImageIO.read(new File(sourceFileName));

		String extension = getExtension(sourceFileName);
		BufferedImage croppedImage = cropImage(sourceImage, rect);
		sourceImage.flush();
		// ImageIO.write(croppedImage, extension, )

		// Load border File Name
		BufferedImage borderWatermarkImage = null;
		BufferedImage scaledBorderWatermarkImage = null;
		BufferedImage colorBorderWatermarkImage  = null;
		if  ( borderFileName != null ) {
			borderWatermarkImage = ImageIO.read(new File(borderFileName) );
			scaledBorderWatermarkImage = getScaledImage(borderWatermarkImage, borderWidth, borderHeight);
			borderWatermarkImage.flush();
			colorBorderWatermarkImage = replaceColor(scaledBorderWatermarkImage, borderColor);
			scaledBorderWatermarkImage.flush();
		}
	



		// Scale cropped Image to size of Border
		BufferedImage scaledCroppedImage = getScaledImage(croppedImage,	borderWidth, borderHeight);
		croppedImage.flush();

		// Overlay Image
		// resize the original image to the size of border and initializes
		// necessary graphic properties
		Graphics2D g2d = (Graphics2D) scaledCroppedImage.getGraphics();

		if  ( borderFileName != null ) { 	// for Vuforia, xFrame is null
			// paints the image watermark
			g2d.drawImage(colorBorderWatermarkImage, 0, 0, borderWidth,	borderHeight, null);
			colorBorderWatermarkImage.flush();
			g2d.dispose();
		}

		String targetFile = getFileName(newFrameFile);
		//	2016-02-23, does not overwrite the borderedImagfe file, So create new temp file and then rename
		String newTargetFileName = targetFile + "-tmp.png";

		// Save it to target File Name
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(scaledCroppedImage, extension, baos);
		scaledCroppedImage.flush();
		byte[] imageBytes = baos.toByteArray();

		FileOutputStream borderedFrameStream = new FileOutputStream(newTargetFileName);
		borderedFrameStream.write(imageBytes);
		borderedFrameStream.close();

		// Let us save the source image too, not just the bordered one.
		ByteArrayOutputStream frameByteStream = new ByteArrayOutputStream();
		ImageIO.write(croppedImage, extension, frameByteStream);
		croppedImage.flush();
		byte[] frameImageBytes = frameByteStream.toByteArray();
		FileOutputStream sourceFrameStream = new FileOutputStream(
				CommonUtils.concat(targetFile,sourceFileName.substring(sourceFileName.lastIndexOf(".") , sourceFileName.length())));
		sourceFrameStream.write(frameImageBytes);
		sourceFrameStream.close();
		
		//	2016-02-23, does not overwrite the borderedImagfe file, So create new temp file and then rename
		File file = new File(newFrameFile);
		File newFile = new File(newTargetFileName);
		newFile.renameTo(file);
		return;
	}

	
	// from image crop with border line image
	public static void processCroppingOfImage(BufferedImage borderImage, String sourceFileName, String newFrameFile, Rectangle rect) throws Exception {
		BufferedImage sourceImage = ImageIO.read(new File(sourceFileName));
		BufferedImage croppedImage = cropImage(sourceImage, rect);
		int width = croppedImage.getWidth();
		int height = croppedImage.getHeight();
		sourceImage.flush();
		
		Graphics2D g2d = (Graphics2D) croppedImage.getGraphics();
		g2d.drawImage(borderImage, 0, 0, width, height,	null);
		borderImage.flush();
		g2d.dispose();

		// Save it to target File Name
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(croppedImage, "png", baos);
		croppedImage.flush();
		byte[] imageBytes = baos.toByteArray();

		FileOutputStream fos = new FileOutputStream(newFrameFile);
		fos.write(imageBytes);
		fos.close();
		sourceImage = null;
		croppedImage = null;
		baos.close();
		return;
	}
	

	private static BufferedImage replaceColor(BufferedImage image, String preferredColor) {
		int width = image.getWidth();
		int height = image.getHeight();

		int red = Integer.valueOf(preferredColor.substring(1, 3), 16);
		int blue = Integer.valueOf(preferredColor.substring(3, 5), 16);
		int green = Integer.valueOf(preferredColor.substring(5, 7), 16);

		int white = Integer.valueOf("ff", 16);

		// create a new image that will have transparent colors
		BufferedImage result = new BufferedImage(width, height,	BufferedImage.TYPE_4BYTE_ABGR_PRE);

		// make color transparent
		int oldRGB = new Color(0, 0, 0).getRGB();
		int newRGB = Color.decode(preferredColor).getRGB();
		int whiteRGB = new Color(white, white, white).getRGB();
		int replaceColor = -15592942;
		int currRGB;
		int newRGB1 = new Color(red, green, blue, 0).getRGB();
		int y = 0;
		for (int x = 0; x < width; x++) {
			for (y = 0; y < height; y++) {
				currRGB = image.getRGB(x, y);

				if (oldRGB == currRGB) {
					result.setRGB(x, y, newRGB);
				} else if (whiteRGB == currRGB) {
					result.setRGB(x, y, currRGB);
				} else if (replaceColor == currRGB) {
					result.setRGB(x, y, newRGB);
				} else {
					result.setRGB(x, y, newRGB1);
				}
			}
		}

		return result;
	}

	public static BufferedImage getScaledImage(BufferedImage image, int width,int height) throws IOException {
		int imageWidth = image.getWidth();
		int imageHeight = image.getHeight();

		double scaleX = (double) width / imageWidth;
		double scaleY = (double) height / imageHeight;
		AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
		AffineTransformOp bilinearScaleOp  = null;
		if  ( width < 100 ) {
			bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);
		} else {
			bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		}
		//AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);
		int imageType= image.getType();
		//logger.info("image type is " + imageType);
		if (imageType == 0){
			//This is problem, 
			logger.error("This should never be 0, will return with exception at 541 line");
		}
		return bilinearScaleOp.filter(image, new BufferedImage(width, height,imageType));
	}

	
	
	public static boolean isProperImage(String imageFilePath, int width, int height)  {
		boolean result = false;
		try {
			BufferedImage bufferImage = ImageIO.read( new File(imageFilePath) );
			if(bufferImage.getWidth() >=width && bufferImage.getHeight() >=height){
				result =true;
			}
		} catch (IOException e) {
			logger.error("error during isProperImage, unable to load image", e); 
		}
		return result;
	}
	
	public static boolean isProperImage(File imageFile, int width, int height)  {
		boolean result = false;
		try {
			BufferedImage bufferImage = ImageIO.read( imageFile );
			if(bufferImage.getWidth() >=width && bufferImage.getHeight() >=290){
				result =true;
			}
		} catch (IOException e) {
			logger.error("error during isProperImage, unable to load image", e); 
		}
		return result;
	}
	
	public static boolean isProperImage(String imageFilePath)  {
		boolean result = false;
		try {
			BufferedImage bufferImage = ImageIO.read( new File(imageFilePath) );
			if(bufferImage.getWidth() >=290 && bufferImage.getHeight() >=290){
				result =true;
			}
		} catch (IOException e) {
			logger.error("error during isProperImage, unable to load image", e); 
		}
		return result;
	}
	
	public static boolean isProperImage(File imageFile)  {
		boolean result = false;
		try {
			BufferedImage bufferImage = ImageIO.read( imageFile );
			if(bufferImage.getWidth() >=290 && bufferImage.getHeight() >=290){
				result = true;
			}
		} catch (IOException e) {
			logger.error("error during isProperImage, unable to load image", e); 
		}
		return result;
	}



	public boolean isImageFileExtention(String fileExt) {
    	if  ( fileExt == null ) {
    		return false;
    	}
    	try {
	    	if  ( fileExt.equalsIgnoreCase("png") || fileExt.equalsIgnoreCase("jpg")
	    	   || fileExt.equalsIgnoreCase("jpeg") || fileExt.equalsIgnoreCase("gif")
	    	   || fileExt.equalsIgnoreCase("tiff") || fileExt.equalsIgnoreCase("bmp")
	    	   || fileExt.equalsIgnoreCase("psd") || fileExt.equalsIgnoreCase("thm")
	    	   || fileExt.equalsIgnoreCase("ppm") || fileExt.equalsIgnoreCase("pgm")
	    	   || fileExt.equalsIgnoreCase("pbm") || fileExt.equalsIgnoreCase("pnm")
	    	   || fileExt.equalsIgnoreCase("cgm") || fileExt.equalsIgnoreCase("svg")
	    	   || fileExt.equalsIgnoreCase("webp") || fileExt.equalsIgnoreCase("pns")
	    	   || fileExt.equalsIgnoreCase("mpo") || fileExt.equalsIgnoreCase("jps")
	    	   || fileExt.equalsIgnoreCase("tif") || fileExt.equalsIgnoreCase("yuv")  ) {
	    		return true;
	    	}
		} catch ( Exception ex) {
		}
    	return false;
    }
	
	public static Color getColor(String colorStr) {
		
    	if  ( colorStr == null ) {
    		return new Color(0,0,0);	// white
    	}
    	if  ( colorStr.length() != 7 || !colorStr.startsWith("#") ) {
    		return new Color(0,0,0);	// white
    	}
    	return new Color(
                Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
                Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
                Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
    }
	
	
	public static int getRandomNumber(int min,int max) {
		Random generator = new Random();
		int num = max - generator.nextInt(max-min);
		if  ( num < min ) {
			return min;
		}
		return num;
	}

	
	public static void main1(String[] args) {
		int width = 500;
		int height = 425;
		float ratio = ((float) width / (float) height);
		System.out.println(ratio);
		int tbHeight = 200;
		int tbWidth = (int) (ratio * 200);
		System.out.println("the tb width and height are:" + tbWidth + " : "
				+ tbHeight);
	}

	public static void main(String[] args)  {

		
	}


}
