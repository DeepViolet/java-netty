package client;

/**
 * Created by liuhuiyi on 2017/5/25.
 */
public class Main {
    public static void main(String args[]){
        EchoClient nettyClient = new EchoClient(8007,"127.0.0.1");
        try {
            nettyClient.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
