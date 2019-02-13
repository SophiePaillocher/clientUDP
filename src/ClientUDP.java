import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientUDP {
    public static void main( String[] args )
    {
        InetAddress addr;
        int port;

        try {
            if (args.length == 3) {
                addr = InetAddress.getByName(args[1]);
                port = Integer.parseInt(args[2]);
            } else {
                System.err.println("serveur et/ou port manquant");
                return;
            }

            //byte[] buffer = new byte[100];
            DatagramSocket ds = new DatagramSocket();

            System.out.println("client ok");

            Scanner input = new Scanner(System.in);
            String line;
            do {
                line = input.nextLine() + "\n";
                byte[] line_b = line.getBytes();
                DatagramPacket packet = new DatagramPacket(line_b, line_b.length, addr, port);
                ds.send(packet);
            } while (input.hasNextLine());

            ds.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
