package com.weiyan.files.service;

import org.springframework.stereotype.Service;

import java.io.*;

/**
 * @author mister_wei
 * @version 1.1.1
 * @title web_service
 * @package com.weiyan.files.service
 * @date 2019/5/30 16:23
 */
@Service
public class FileService {
    private  String fileLocal = null;

    public void fileDelete(File file) throws Exception {
        for (File listFile : file.listFiles()) {
            if (listFile.isFile()) {
                listFile.delete();
            } else {
                fileDelete(listFile);
            }
            listFile.delete();
        }
    }

    /**
     * @param destFile   写入流
     * @param sourceFile 把该路径数据 输出的destFILE中  读取流
     * @throws FileNotFoundException
     */

    public void copyFiles(File destFile, File sourceFile) throws FileNotFoundException {
        OutputStream fileOutputStream = null;
        InputStream fileInputStream = null;
        if (fileLocal == null) {
            fileLocal = destFile.getAbsolutePath();
        }

        if (sourceFile.isDirectory()) {
            String fileName = sourceFile.getName();
            destFile = new File(destFile + "\\" + fileName);
            if (!destFile.exists()) {
                destFile.mkdir();
            }
        }

        try {
                for (File file : sourceFile.listFiles()) {
                    if (file.isFile()) {
                        String fileNames = file.getName();
                        fileInputStream = new BufferedInputStream(new FileInputStream(file));
                        fileOutputStream = new BufferedOutputStream(new FileOutputStream(destFile+"\\"+fileNames));
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = fileInputStream.read(buffer)) > -1) {
                            fileOutputStream.write(buffer, 0, length);
                        }
                        fileOutputStream.flush();
                    }
                        if (file.isDirectory()) {
                            copyFiles(destFile,file);
                        }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
