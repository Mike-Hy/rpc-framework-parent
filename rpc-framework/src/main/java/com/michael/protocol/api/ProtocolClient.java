package com.michael.protocol.api;

import com.michael.common.Invocation;

public interface ProtocolClient {

         String  send(String hostName, int port, final Invocation invocation);

}
