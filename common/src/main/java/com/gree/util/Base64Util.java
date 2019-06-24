package com.gree.util;

import com.gree.exception.KellyException;
import com.gree.result.ResponseInfoEnum;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

public class Base64Util {

    public static boolean generateFile (String fileBase64, String path, String file) {
        if(StringUtils.isEmpty(fileBase64)){
            return false;
        }
        fileBase64 = fileBase64.substring(fileBase64.indexOf("base64")+7);
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(fileBase64);

            for (int i= 0;i < b.length; i++) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            FileUtil.checkSavePath(path);
            OutputStream out = new FileOutputStream(path+file);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {
            throw new KellyException(ResponseInfoEnum.IO_ERROR);
        }
    }

    public static String makeBase64 (String file) {
        InputStream inputStream;
        byte[] data;
        try {
            inputStream = new FileInputStream(file);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (Exception e) {
            throw new KellyException(ResponseInfoEnum.IO_ERROR);
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
}
