package com.gree.util;

import com.gree.constant.FileConstant;
import com.gree.exception.KellyException;
import com.gree.result.ResponseInfoEnum;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;

public class FileUtil {

    private FileInputStream fis;
    private FileOutputStream fos;
    private InputStream is;
    private OutputStream os;
    private static Integer BUFFER_SIZE = 1024;


    public static FileUtil createFileUtil() {
        return new FileUtil();
    }

    /**
     * Copy integer.
     *
     * @param fis 输入流
     * @param fos 输出流
     * @return 返回文件的字节数，-1上传失败
     * @description 使用文件输入输出流进行文件复制
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:04 02:09:25.
     */
    private Integer copy(FileInputStream fis,FileOutputStream fos){
        Integer byteCount = 0;
        Integer bytesRead;
        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            while((bytesRead = fis.read(buffer,0,BUFFER_SIZE))!=-1){
                fos.write(buffer,0,bytesRead);
                byteCount += bytesRead;
            }
        } catch (IOException e) {
            throw new KellyException(ResponseInfoEnum.IO_ERROR);
        }finally {
            close();
        }
        return byteCount;
    }

    /**
     * Format is blob byte [ ].
     *
     * @param blob the blob
     * @return the byte [ ]
     * @description 将Blob格式的文件转成byte[]
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 09:09:09.
     */
    public byte[] formatByteByBlob(Blob blob){
        byte[] buffer = null;
        try {
            is = blob.getBinaryStream();
            buffer = new byte[is.available()];
            is.read(buffer);
        } catch (SQLException | IOException e) {
            throw new KellyException(ResponseInfoEnum.IO_ERROR);
        }finally {
            if(is!=null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new KellyException(ResponseInfoEnum.IO_CLOSE_ERROR);
                }
            }
        }
        return buffer;
    }

    /**
     * Format by string clob.
     *
     * @param note 字符串
     * @return 返回Clob格式
     * @description 将字符串转成Clob格式
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 11:09:18.
     */
    public Clob formatClobByString(String note){
        Clob clob = null;
        if(note!=null) {
            try {
                clob = new SerialClob(note.toCharArray());
            } catch (SQLException e) {
                throw new KellyException(ResponseInfoEnum.CLOB_CONVERT_ERROR);
            }
        }
        return clob;
    }

    /**
     * Convert clob string.
     *
     * @param clob the clob
     * @return the string
     * @description 将Clob
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 01:09:26.
     */
    public String convertClob(Clob clob){
        String result = "";
        if(clob != null) {
            try {
                Reader reader = clob.getCharacterStream();
                BufferedReader br = new BufferedReader(reader);
                String s = br.readLine();
                StringBuilder sb = new StringBuilder();
                while (s != null) {
                    sb.append(s);
                    s = br.readLine();
                }
                result = sb.toString();
            } catch (IOException | SQLException e) {
                throw new KellyException(ResponseInfoEnum.CLOB_CONVERT_ERROR);
            }
        }
        return result;
    }

    /**
     * Format blob by byte blob.
     *
     * @param buffer the buffer
     * @return the blob
     * @description 将buffer包装成可用的Blob格式的文本
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 01:09:44.
     */
    public Blob formatBlobByByte(byte[] buffer){
        Blob blob = null;
        if(buffer!=null) {
            try {
                blob = new SerialBlob(buffer);
            } catch (SQLException e) {
                throw new KellyException(ResponseInfoEnum.BLOB_CONVERT_ERROR);
            }
        }
        return blob;
    }

    public void download(HttpServletResponse response, String duta, String fileName, String path){
        String suffix = getFileSuffix(fileName);
        String realName = duta.substring(2);
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
        try {
            response.setHeader("Content-Disposition", "attachment;fileName="+ new String((fileName).getBytes("gb2312"),"ISO8859_1"));
        } catch (UnsupportedEncodingException e) {
            throw new KellyException(ResponseInfoEnum.CHARSET_CONVERT_ERROR);
        }
        try {
            fis = new FileInputStream(new File(path+realName+suffix));
            os = response.getOutputStream();
            byte[] buffer = new byte[BUFFER_SIZE];
            int byteRead;
            while((byteRead = fis.read(buffer,0,BUFFER_SIZE))!=-1){
                os.write(buffer,0,byteRead);
            }
        } catch (IOException e) {
            throw new KellyException(ResponseInfoEnum.ERROR);
        }
    }

    /**
     * Check save path.
     *
     * @param savePath the save path
     * @description 检查储存文件的地址是否存在，不存在便创建一个.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 03:09:05.
     */

    public static void checkSavePath(String savePath){
        File path = new File(savePath);
        if(!path.exists()){
            path.mkdirs();
        }
    }

    /**
     * Get file name string.
     *
     * @param fileName 文件的名称加尾椎
     * @return 文件的名称
     * @description 截取文件的名称.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:18 09:10:45.
     */
    public String getFileName(String fileName){
        int postion = fileName.indexOf(".");
        return fileName.substring(0,postion);
    }

    /**
     * Get file suffix string.
     *
     * @param fileName 文件名
     * @return the string
     * @description 获得文件的扩展名.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:20 03:10:18.
     */
    public String getFileSuffix(String fileName){
        int position = fileName.indexOf(".");
        return fileName.substring(position);
    }

    /**
     * Gen http resp header content disposition string.
     *
     * @param fnm 文件名
     * @param ua  user-agent
     * @return the string
     * @description 设置文件的respHeader,解决返回文件名乱码问题
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :12:06 09:12:31.
     */
     public String genHttpRespHeaderContentDisposition(String fnm, String ua) {
        try {
            // Safari 狗屎
            if (null != ua && ua.contains(" Safari/")) {
                String e_fnm = new String(fnm.getBytes("UTF-8"), "ISO8859-1");
                return "attachment; filename=\"" + e_fnm + "\"";
            }
            // 其他用标准
            else {
                String e_fnm = URLEncoder.encode(fnm, "utf-8");
                return "attachment; filename*=utf-8'zh_cn'" + e_fnm;
            }
        }
        catch (UnsupportedEncodingException e) {
            throw new KellyException(ResponseInfoEnum.ERROR);
        }
    }

    /**
     * Close.
     *
     * @description 关闭所有流
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:06 09:09:34.
     */
    private void close(){
        if (fis != null) {
            try {
                fis.close();
            } catch (IOException e) {
                throw new KellyException(ResponseInfoEnum.FOS_CLOSE_ERROR);
            }
        }
        if (fos != null) {
            try {
                fos.close();
            } catch (IOException e) {
                throw new KellyException(ResponseInfoEnum.FOS_CLOSE_ERROR);
            }
        }
    }
}
