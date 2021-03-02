package com.michael.protocol.http;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import com.michael.protocol.api.ProtocolServer;

/**
 * @ClassName HttpServer
 * @Description TODO
 * @Author Michael.Ng
 * @Date 2021/2/28 10:10
 **/
public class HttpServer implements ProtocolServer {

       public void start(String hostName,int port){

               Tomcat tomcat = new Tomcat();
               Service service = tomcat.getServer().findService("Tomcat");

               Connector connector = new Connector();
               connector.setPort(port);

               Engine engine = new StandardEngine();
               engine.setDefaultHost(hostName);

               Host host = new StandardHost();
               host.setName(hostName);

               String contextPath = "";
               Context context = new StandardContext();
               context.setPath(contextPath);
               context.addLifecycleListener(new  Tomcat.FixContextListener());

               host.addChild(context);
               engine.addChild(host);

               service.setContainer(engine);
               service.addConnector(connector);

               tomcat.addServlet(contextPath,"dispatcherServlet",new DispatcherServlet());
               context.addServletMappingDecoded("/*","dispatcherServlet");

               try {
                       tomcat.start();
               } catch (LifecycleException e) {
                       e.printStackTrace();
               }
               tomcat.getServer().await();
       }
}