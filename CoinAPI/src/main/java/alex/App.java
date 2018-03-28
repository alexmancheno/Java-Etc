package alex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;



public class App 
{
    public static void main(String[] args) throws Exception
    {

        try
        {
        	String address = "ws://demos.kaazing.com/echo";
        	// String address = "wss://echo.websocket.org";
        	// String address = "wss://ws.coinapi.io/v1/";

	        // Open websocket
	       final  WebSocketClientEndpoint client = new WebSocketClientEndpoint(new URI(address));

	        // Add listener

	        client.addMessageHandler(new WebSocketClientEndpoint.MessageHandler() {
	            public void handleMessage(String message) {
	                System.out.println(message);
	            }
	        });

	        // Send message to websocket
	        System.out.println("Did we get this far?");
	        // client.sendMessage(value.toString());

	        // Wait 5 seconds for messages from websocket
	        Scanner reader = new Scanner(System.in);
	        String input = reader.nextLine();
	        while (!input.equals("quit"))
	        {
	        	client.sendMessage(input);
	        	input = reader.nextLine();
	        }
	    }
	    // catch (InterruptedException e)
	    // {
	    // 	System.err.println("InterruptedException:\n" + e.getMessage());
	    // }
	    catch (URISyntaxException e)
	    {
	    	System.err.println("URISyntaxException:\n" + e.getMessage());
	    }
	    catch (Exception e)
	    {
	    	System.err.println("Exception in main:\n" + e);
	    }
    }
}


