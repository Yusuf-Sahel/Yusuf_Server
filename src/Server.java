import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Server {

    public static void main(String[] args) throws IOException{
        int port = 1234;
        if (args.length > 0){
            port = Integer.parseInt(args[0]);
        }

    MulticastSocket socket = new MulticastSocket(port);
    }
}