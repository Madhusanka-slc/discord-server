import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;

public class ListIPAddressDemo {
    public static void main(String[] args) throws SocketException {
        InetAddress loopbackAddress = InetAddress.getLoopbackAddress();
        System.out.println(loopbackAddress);
        System.out.println("==========================");

        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()){
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            System.out.println(networkInterface);
            System.out.println("Mac addresses");
          //  System.out.println(networkInterface.getInetAddresses());
            byte[] hardwareAddress = networkInterface.getHardwareAddress();
            System.out.println(Arrays.toString(hardwareAddress));
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            System.out.println("IP addressses");
            while (inetAddresses.hasMoreElements()){
                InetAddress inetAddress = inetAddresses.nextElement();
                if(inetAddress instanceof Inet4Address){
                    System.out.println(inetAddress);
                }

            }
            System.out.println("==============================");
        }
    }
}
