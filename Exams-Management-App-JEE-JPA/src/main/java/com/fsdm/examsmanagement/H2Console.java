package com.fsdm.examsmanagement;
import org.h2.tools.Server;

public class H2Console {
    public static void main(String[] args) throws Exception {
        Server server = Server.createTcpServer("-tcpAllowOthers", "-tcpPort", "9092").start();
        System.out.println("H2 console started at http://localhost:9092");
    }
}