import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class Client {

    public void connectToServer() throws IOException {

        String serverAddress = "127.0.0.1";

        // Make connection and initialize streams
        Socket socket = new Socket(serverAddress, 9898);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Consume the initial welcoming messages from the server
        for (int i = 0; i < 3; i++) 
            System.out.println((in.readLine() + "\n"));

        Scanner reader = new Scanner(System.in);
        String input;
        while ((input = reader.nextLine()) != null)
        {
            out.println(input);
        }
        try
        {
            socket.close();
        }
        catch(Exception e)
        {
            System.out.println("There was en error closing the socket: " + e);
        }
    }


    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.connectToServer();
    }
}