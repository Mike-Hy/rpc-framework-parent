package com.michael.register;

import com.alibaba.fastjson.JSON;
import com.michael.common.Url;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RegisterFactory
 * @Description 注册中心工厂
 * @Author Michael.Ng
 * @Date 2021/2/28 10:06
 **/
public class RegisterFactory {

        public static final String PATH = "register.txt";
        //本地注册服务
        public static Map<String, Class> localServiceMap = new HashMap<>();

        //远程注册服务
        public static Map<String, List<Url>> remoteServiceMap = new HashMap<>();

        public static void registerLocalService(String key, Class value){
                localServiceMap.put(key,value);
        }

        public static Class getLocalService(String key){
                return localServiceMap.get(key);
        }

        public static void registerRemoteService(String key, Url url)  {
                FileOutputStream fileOutputStream = null;
                try {
                        fileOutputStream = new FileOutputStream(new File(PATH));
                        IOUtils.write(key+"="+JSON.toJSONString(url),fileOutputStream, StandardCharsets.UTF_8);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public static List<Url> getRemoteService(String key){
                String regex = "=";
                try {
                        FileInputStream fileInputStream = new FileInputStream(PATH);
                        String result = IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
                        if(!StringUtils.isEmpty(result)){
                                String[] split = result.split(regex);
                                Url url = JSON.parseObject(split[1], Url.class);
                                return Collections.singletonList(url);
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return null;
        }

}