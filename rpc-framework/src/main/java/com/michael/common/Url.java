package com.michael.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName URL
 * @Description TODO
 * @Author Michael.Ng
 * @Date 2021/2/28 10:31
 **/
@Data
@AllArgsConstructor
public class Url {

        private  int port;

        private String host;

}