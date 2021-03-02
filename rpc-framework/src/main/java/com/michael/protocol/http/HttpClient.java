package com.michael.protocol.http;

import com.michael.common.Invocation;
import org.apache.commons.io.IOUtils;
import com.michael.protocol.api.ProtocolClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName HttpClient
 * @Description TODO
 * @Author Michael.Ng
 * @Date 2021/2/28 11:42
 **/
public class HttpClient implements ProtocolClient {
        //发送请求
        public String  send(String hostName, int port, Invocation invocation){
                try {
                        URL url = new URL("http",hostName,port,"");
                        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                        connection.setRequestMethod("POST");
                        connection.setDoOutput(Boolean.TRUE);

                        OutputStream outputStream = connection.getOutputStream();
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                        objectOutputStream.writeObject(invocation);
                        objectOutputStream.flush();
                        objectOutputStream.close();

                        InputStream inputStream = connection.getInputStream();
                        return   IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return null;
        }

}