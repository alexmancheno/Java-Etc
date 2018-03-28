import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Http
{
	public static void main(String[] args) throws IOException
	{
		ServerSocket server = new ServerSocket(8080);
		{
			System.out.println("Listening on 8080..");
			while(true)
			{
				try (Socket socket = server.accept())
				{
					String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + new Date().toString();
					socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
				}
			}
		}
	}
}