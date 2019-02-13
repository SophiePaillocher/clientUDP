import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


public class ServeurUDP {
    public static void main( String[] args )
    {
        int port;

        try {
            if (args.length == 2) {
                port = Integer.parseInt(args[1]);
            } else {
                System.err.println("port manquant");
                return;
            }

            byte[] buffer = new byte[100];
            DatagramSocket ds = new DatagramSocket(port);

            System.out.println("serveur ok");

            String line;
            while (true) {
                for (int i = 0; i < buffer.length; ++i) {
                    buffer[i] = 0;
                }

                DatagramPacket received_packet = new DatagramPacket(buffer, buffer.length);
                ds.receive(received_packet);
                line = ">" + new String(received_packet.getData(), 0, received_packet.getLength());

                System.out.println(line);

                byte[] line_b = line.getBytes();
                DatagramPacket send_packet = new DatagramPacket(line_b, line_b.length, received_packet.getAddress(), received_packet.getPort());
                ds.send(send_packet);
            }

            //ds.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

