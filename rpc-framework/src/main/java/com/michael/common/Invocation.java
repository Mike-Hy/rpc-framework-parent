package com.michael.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Invocation
 * @Description TODO
 * @Author Michael.Ng
 * @Date 2021/2/28 11:43
 **/
@Data
@AllArgsConstructor
public class Invocation implements Serializable {

        private String interfaceName;
        private String methodName;
        private Class[] paramTypes;
        private Object[] params;
}