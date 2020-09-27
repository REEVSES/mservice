package com.liuyao.user.thrift;

import com.liuyao.thrift.message.MessageService;
import com.liuyao.thrift.user.UserService;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:1209725274@qq.com">刘瑶</a>
 * @date 2020/09/06 15:48
 * Created by Yao Liu.
 */
@Component
public class ServiceProvider {
    @Value("${thrift.user.ip}")
    private String serverIp;
    @Value("${thrift.user.port}")
    private int serverPort;

    @Value("${thrift.message.ip}")
    private String messageServerIp;
    @Value("${thrift.message.port}")
    private int messageServerPort;

    private enum ServiceType{
        USER,MESSAGE
    }

    public UserService.Client getUserService(){
        return getService(serverIp,serverPort,ServiceType.USER);
    }


    public MessageService.Client getMessageService(){
        return getService(messageServerIp,messageServerPort,ServiceType.MESSAGE);
    }

    public <T> T getService(String ip,int port,ServiceType serviceType){
        TSocket socket = new TSocket(ip,port,3000);
        //对应客户端的帧模式和二进制协议
        TTransport transport = new TFastFramedTransport(socket);
        try{
            transport.open();
        }catch (TTransportException e){
            e.printStackTrace();
            return null;
        }
        TProtocol protocol = new TBinaryProtocol(transport);
//        定义统一的父类返回
        TServiceClient result=null;
        switch (serviceType){
            case USER:
                result = new UserService.Client(protocol);
                break;
            case MESSAGE:
                result= new MessageService.Client(protocol);
                break;
        }

        return (T)result;
    }


}
