package com.kaishengit.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import static com.google.zxing.BarcodeFormat.QR_CODE;

/**
 * 二维码工具类
 */
public class QrCodeUtil {

    //默认二维码的高度
    private static final int width = 300;
    //默认二维码的宽度
    private static final int height = 300;
    //默认二维码的文件格式
    private static final String format = "png";
    //二维码参数
    private static final Map<EncodeHintType,Object> hints = new HashMap();
    //初始化
    static {
        //字符编码
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        // 容错等级 L、M、Q、H 其中 L 为最低, H 为最高
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //二维码与图片边距
        hints.put(EncodeHintType.MARGIN,2);
    }

    /**
     * 将二维码图片输出到一个流中
     * @param content 二维码内容
     * @param stream  输出流
     * @param width   宽
     * @param height  高
     * @throws WriterException
     * @throws IOException
     */
    public static void writeToStream(String content, OutputStream stream,int width,int height) throws WriterException,IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, QR_CODE, width, height, hints);
        MatrixToImageWriter.writeToStream(bitMatrix,format,stream);
    }

    /**
     * 生成二维码图片文件
     * @param content 二维码内容
     * @param path    文件保存路径
     * @param width   宽
     * @param height  高
     * @throws WriterException
     * @throws IOException
     */
    public static void createQRCode(String content,String path,int width,int height) throws WriterException,IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE,width,height,hints);
        //toPath方法，jdk1.7以上提供
        MatrixToImageWriter.writeToPath(bitMatrix,format,new File(path).toPath());
    }

}
