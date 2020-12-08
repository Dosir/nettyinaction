package com.fd.netty.chapter01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Example01 {
    public static void main(String[] args) throws IOException {
        int portNumber = 9999;

        // 创建一个新的 ServerSocket, 用以监听指定端口上的连接请求
        ServerSocket serverSocket = new ServerSocket(portNumber);

        // 对 accept()方法的调用将被阻塞，直到一个连接建立，
        // 随后返回一个socket用于客户端和服务端之间的通信，
        // 该ServerSocket将继续监听传入的连接
        Socket clientSocket = serverSocket.accept();

        // 这些流对象都派生于该套接字的流对象
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String request, response;
        while ((request = in.readLine()) != null) {
            if ("Done".equals(request)) {
                // 如果客户端发送了“Done”,则退出处理循环
                break;
            }

            // 请求被传递给服务器的处理方法
            response = processRequest(request);

            // 服务器的响应被发送给了客户端
            out.println(response);
        }
    }

    private static String processRequest(String request) {
        System.out.println(request);
        return request;
    }
}
