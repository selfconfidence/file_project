package com.weiyan.files.controller;

import com.weiyan.files.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mister_wei
 * @version 1.1.1
 * @title web_service
 * @package com.weiyan.files.webflux
 * @date 2019/5/30 16:14
 */
@RequestMapping("file")
@RestController
@CrossOrigin
public class FileWebController {
    //用来读取配置文件中得数据
    @Autowired
    private Environment environment;
    @Autowired
    private FileService fileService;
    @RequestMapping(value = "/deleteFiles",method = RequestMethod.POST)
    public Map deleteFile(@RequestBody(required = false) Map par){
        String localPath = par.get("fileLocal").toString();
        Map resultMap = new HashMap();
        //用来对文件进行删除
        try {
            File file = new File(localPath);
            if (!file.isDirectory()){
                resultMap.put("flag",false);
                resultMap.put("message","该路径不是一个文件路径~");
                return resultMap;
            }
            fileService.fileDelete(file);


        }catch (Exception e){
            resultMap.put("flag",false);
            resultMap.put("message",e.getMessage());
            return resultMap;
        }
        resultMap.put("flag",true);
        resultMap.put("message","删除成功~");
        return resultMap;
    }
}
