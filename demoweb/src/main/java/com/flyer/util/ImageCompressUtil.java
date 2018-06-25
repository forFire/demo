
package com.flyer.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ImageCompressUtil {

    // 压缩图片使用日志输出
    private static Logger log = Logger.getLogger(ImageCompressUtil.class);

    // 执行个数(计数器)
    public static AtomicInteger count = new AtomicInteger();
    
	public static String upload(File file, String url) {
		return uploadParam(file, url, null,null);
	}
    
	/**
	 * 文件上传
	 * 
	 * @param file
	 * @return
	 */
	public static String uploadParam(File file, String url, String proportion,String ext) {
		String filePath = "";
		HttpClient client = new HttpClient();
		PostMethod filePost = new PostMethod(url);		
		//String ipRead = ConfigFileImpl.getInstance().getPicReadPath();
		String ipRead = EnvironmentUtil.getInstance().getPropertyValue("file.readPath");
		try {
			//如果有其它参数补全参数传递
			Part[] parts;
			if(StringUtils.isNotBlank(proportion)){
				parts = new Part[2];
				if(proportion.indexOf(":") > 0){
					parts[1] = new StringPart("proportion", proportion);
				} else {
					parts[1] = new StringPart("suffix", ext);
				}
				
			} else {
				parts = new Part[1];
			}
			parts[0] = new FilePart("file", file);
			
			filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
			client.getHttpConnectionManager().getParams().setConnectionTimeout(5000000);
			int status = client.executeMethod(filePost);
			if (status == HttpStatus.SC_OK) {
				filePath = new String(filePost.getResponseBodyAsString().getBytes("utf-8"));
				String ipWrite =  findIp(url);
				log.info("ImageCompressUtil upload filePath1:" + filePath + " ipWrite:" + ipWrite + " ipRead:" + ipRead);
				filePath = filePath.replaceAll(ipWrite, ipRead);
				log.info("ImageCompressUtil upload filePath2:" + filePath);
			} else {
				log.error("ImageCompressUtil：文件上传失败");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("ImageCompressUtil error",ex);
		} finally {
			filePost.releaseConnection();
		}
		return filePath;
	}
	
	private static String findIp(String url) {
		String mode = "([^>]*)/file-mobile-api";
		Pattern p = Pattern.compile(mode);
		Matcher m = p.matcher(url);
		String ip = null;
		while (m.find()) {
			ip = m.group(1);
		}
		return ip;
	}
	
    /**
     * @param imgOldPath
     *        原始图片文件路径
     * @param imgPathDir
     *        保存图片路径
     * @param imgNameString
     *        保存图片名称
     * @param outputWidth
     *        设置压缩图片宽度
     * @param outputHeight
     *        设置压缩图片高度
     *        线程池
     */
	public static void compress(final String imgOldPath, final String imgPathDir, final String imgNameString,
            final int outputWidth, final int outputHeight) {
        try {
            File uploadFile = new File(imgPathDir);
            File oldImgFile = new File(imgOldPath);
            if (!uploadFile.exists()) {
                uploadFile.mkdirs();
            }
            // 判断原文件是否存在，如果不存在，记录退出
            if (!oldImgFile.exists()) {
                log.error("image does not exist,path:" + imgOldPath);
                count.incrementAndGet();// +1操作
                return;
            }
            if (oldImgFile.length() < (1024 * 200)) {
                FileUtils.copyFile(oldImgFile, new File(imgPathDir + imgNameString));
                return;
            }

            try {
                int width, height;
                Image img = null;
                File oldFile = new File(imgOldPath);
                try {
                    // 以普通方式读取（遇到cmyk模式图片会报异常）
                    img = ImageIO.read(oldFile);
                } catch (Exception e) {
                    // 以cmyk方式读取
                    img = CMYKImageUtil.readImage(oldFile);
                }
                if (img == null || img.getWidth(null) == -1) {
                    // 不是图片
                    log.error("image file is error, imgFile:" + imgOldPath);
                } else {
                    // 为等比缩放计算输出的图片宽度及高度
                    double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1;
                    double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1;
                    // 根据缩放比率大的进行缩放控制
                    double rate = rate1 > rate2 ? rate1 : rate2;
                    width = (int) (((double) img.getWidth(null)) / rate);
                    height = (int) (((double) img.getHeight(null)) / rate);

                    BufferedImage bfi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                    /*
                     * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
                     */
                    bfi.getGraphics().drawImage(img.getScaledInstance(width, height, Image.SCALE_FAST), 0, 0, null);
                    
                    String compressFilePath = imgPathDir + File.separator + imgNameString ;
                    
//					FileOutputStream fos = new FileOutputStream(compressFilePath);
//					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
//					encoder.encode(bfi);
//					fos.close();
                    

                    ImageIO.write(bfi,  "jpeg" , new File(compressFilePath)); 
                    bfi.flush();
                }
            } catch (Exception e) {
                // log日志单独配置
                log.error("compress image error,imgFile:" + imgOldPath + ",imgPathDir:" + imgPathDir
                        + ",imgNameString:" + imgNameString, e);
            } finally {
                count.incrementAndGet();// +1操作
            }

        } catch (Exception e) {
            // log日志单独配置
            log.error("compress image error,imgFile:" + imgOldPath + ",imgPathDir:" + imgPathDir + ",imgNameString:"
                    + imgNameString);
        }

    }
	
	
	public static void main(String[] args) {
		String s = "http://10.30.36.52:8082/MobileFileServer";
		String mode = "([^>]*)/MobileFileServer";
		Pattern p = Pattern.compile(mode);
		Matcher m = p.matcher(s);
		while (m.find()) {
			String title = m.group(1);
			System.out.println(title);
		}
		
	}
	

	
}
