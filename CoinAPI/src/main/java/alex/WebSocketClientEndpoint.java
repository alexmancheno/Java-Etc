package alex;

import java.net.URI;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import javax.websocket.DeploymentException;
import java.io.IOException;
import org.json.JSONObject;
import java.util.*;

@ClientEndpoint
public class WebSocketClientEndpoint
{
	Session userSession = null;
	private MessageHandler messageHandler;

	public WebSocketClientEndpoint(URI endpointURI)
	{
		try
		{
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			container.connectToServer(this, endpointURI);
		}		
		catch (DeploymentException e)
		{
			System.out.println("DeploymentException:\n" + e);
		}
		catch (IOException e)
		{
			System.out.println("IOException:\n" + e);
		}
		catch (Exception e)
		{
			System.out.println("Exception:\n" + e);
		}

	}

	@OnOpen
	public void onOpen(Session userSession)
	{

    	ArrayList<String> list = new ArrayList<String>();
    	list.add("trade");

    	ArrayList<String> symbols = new ArrayList<String>();
    	symbols.add("BITSTAMP_SPOT_BTC_USD");
    	symbols.add("BITFINEX_SPOT_BTC_LTC");
    	symbols.add("COINBASE_");
    	symbols.add("ITBIT_");

    	JSONObject value = new JSONObject()
    		.put("type", "hello")
    		.put("apikey", "3A38CACC-D82A-4417-BFD2-D8BC016E6EB7")
    		.put("heartbeat", false)
    		.put("subscribe_data_type", list)
    		.put("subscribe_filter_symbol_id", symbols);
    		
        // hSystem.out.println(value.toString());



		System.out.println("Opening websocket");
		this.userSession = userSession;
		// this.sendMessage(value.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason)
	{
		System.out.println("Closing websocket");
		this.userSession = null;
	}

	@OnMessage
	public void onMessage(String message)
	{
		String in = "Received: " + message;
		// if (this.messageHandler != null)
		// {
		// 	this.messageHandler.handleMessage(in);
		// }
		System.out.println(in);
	}

	public void addMessageHandler(MessageHandler msgHandler)
	{
		this.messageHandler = msgHandler;
	}

	public void sendMessage(String message)
	{
		String out = "Sending: " + message;
		try
		{
			this.userSession.getBasicRemote().sendText(out);
		}
		catch (IOException e)
		{
			System.out.println("IOException:\n" + e);
		}
	}

	public static interface MessageHandler
	{
		public void handleMessage(String message);
	}
}