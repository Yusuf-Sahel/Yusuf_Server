import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Server {
    public static void main(String[] args) throws IOException {
        // Standardportnummer att använda
        int portnumber = 1234;
        if (args.length >= 1) {
            portnumber = Integer.parseInt(args[0]);
        }

        // Skapa en MulticastSocket
        MulticastSocket serverMulticastSocket = new MulticastSocket(portnumber);

        // Bestäm IP-adressen för gruppen
        InetAddress group = InetAddress.getByName("225.4.5.6");

        serverMulticastSocket.joinGroup(group);
        System.out.println("joinGroup method is called...");

        boolean infinite = true;
        // Fortsätt att ta emot data och skriv ut dem
        while (infinite) {
            byte[] buf = new byte[1024];
            DatagramPacket data = new DatagramPacket(buf, buf.length);
            serverMulticastSocket.receive(data);
            String msg = new String(data.getData()).trim();

            System.out.println(readMsg(msg));
        }
        serverMulticastSocket.close();
    }

    public static String readMsg(String msg) {
        if (msg.indexOf("*") > 0) {
            int sum1 = Integer.valueOf(msg.substring(0, msg.indexOf("*")));
            int sum2 = Integer.valueOf(msg.substring(msg.indexOf("*") + 1, msg.length()));
            return sum1 + " * " + sum2 + " = " + sum1 * sum2;
        }
        if (msg.indexOf("/") > 0) {
            int sum1 = Integer.valueOf(msg.substring(0, msg.indexOf("/")));
            int sum2 = Integer.valueOf(msg.substring(msg.indexOf("/") + 1, msg.length()));
            return sum1 + " / " + sum2 + " = " + sum1 / sum2;
        }
        if (msg.indexOf("+") > 0) {
            int sum1 = Integer.valueOf(msg.substring(0, msg.indexOf("+")));
            int sum2 = Integer.valueOf(msg.substring(msg.indexOf("+") + 1, msg.length()));
            return sum1 + " + " + sum2 + " = " + (sum1 + sum2);
        }
        if (msg.indexOf("-") > 0) {
            int sum1 = Integer.valueOf(msg.substring(0, msg.indexOf("-")));
            int sum2 = Integer.valueOf(msg.substring(msg.indexOf("-") + 1, msg.length()));
            return sum1 + " - " + sum2 + " = " + (sum1 - sum2);
        }
        return "0";
    }
}
