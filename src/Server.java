import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Server {

    public static void main(String[] args) throws IOException{
        int port = 1234;
        //Standarnummer
        if (args.length > 0){
            port = Integer.parseInt(args[0]);
        }

    MulticastSocket socket = new MulticastSocket(port);


    InetAddress groupAddress = InetAddress.getByName("225.4.5.6");

    socket.joinGroup(groupAddress);
        System.out.println("Har gått med i gruppen......");

    //Huvuddelen

    boolean isActive = true;
  while (isActive){
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String message = new String(packet.getData(),0,packet.getLength()).trim();

            System.out.println(proccessMessage(message));
        }

    socket.leaveGroup(groupAddress);
    socket.close();
    }

    public static String proccessMessage(String message){

        if(message.contains("*")) {
            String[] parts = message.split("\\*");
            int num1 = Integer.parseInt(parts[0]);
            int num2 = Integer.parseInt(parts[1]);
            return num1 + "*" + num2 + "= " + (num1 * num2);

        } else if (message.contains("/")){
            String[] parts = message.split("/");
            int num1 = Integer.parseInt(parts[0]);
            int num2 = Integer.parseInt(parts[1]);
            return num1 + "/" + num2 + "= " + (num1 / num2);

        } else if (message.contains("+")){
            String[] parts = message.split("\\+");
            int num1 = Integer.parseInt(parts[0]);
            int num2 = Integer.parseInt(parts[1]);
            return num2 + "+" + num1 + "= " + (num1 + num2);

        } else if (message.contains("-")){
            String[] parts = message.split("\\-");
            int num1 = Integer.parseInt(parts[0]);
            int num2 = Integer.parseInt(parts[1]);
            return num1 + "-" + num2 + "= " + (num1 - num2);
        }

        return "fel meddelande";
    }

}