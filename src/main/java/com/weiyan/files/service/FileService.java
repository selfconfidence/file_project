package com.weiyan.files.service;

import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author mister_wei
 * @version 1.1.1
 * @title web_service
 * @package com.weiyan.files.service
 * @date 2019/5/30 16:23
 */
@Service
public class FileService {

    public void fileDelete(File file) throws Exception{
        for (File listFile : file.listFiles()) {
            if (listFile.isFile()) {
                listFile.delete();
            }else{
                fileDelete(listFile);
            }
            listFile.delete();
        }
    }
}
