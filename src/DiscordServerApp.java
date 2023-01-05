import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class DiscordServerApp {
    public static void main(String[] args) {

        ArrayList<Socket> userList = new ArrayList<>();
        StringBuilder chatHistory = new StringBuilder();
        try (ServerSocket serverSocket = new ServerSocket(6060)){
            System.out.println("server started");
            while (true){
                System.out.println("Waiting for an incoming connection");
                Socket localSocket = serverSocket.accept();
                userList.add(localSocket);
                System.out.println("Incomming connection: "+localSocket.getRemoteSocketAddress());
                new Thread(() -> {
                    //Let's handle the newcomer
                    try {
                        OutputStream os = localSocket.getOutputStream();
                        os.write(chatHistory.toString().getBytes());

                        BufferedReader br = new BufferedReader(new InputStreamReader(localSocket.getInputStream()));
                        while (true){
                            String message = br.readLine();
                            chatHistory.append(message+"\n");
                            System.out.println(message);
                            for (Socket socket : userList) {
                             var bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                             bw.write(message);
                             bw.flush();
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }).start();
            }

        } catch (IOException e) {
            System.err.println("Failed to create the discord server");
            e.printStackTrace();//print exception
        }

    }

}
