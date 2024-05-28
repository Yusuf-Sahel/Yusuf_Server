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


    InetAddress groupAdress = Integer.getByName("225.4.5.6");


    //Huvuddelen

    boolean isActive = true;
    While (isActive) {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        String message = new String(packet.getData(),0,packet.getLength()).trim();

        }
    }
}