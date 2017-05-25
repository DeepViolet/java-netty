package server;

public class Main {

    public static void main(String[] args) {
        EchoServer echoServer = new EchoServer();
        try {
            echoServer.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
