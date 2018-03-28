package alex;

import java.io.BufferedReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import org.json.JSONObject;

public class App {

    public static void main(String[] args) {
        try {
            // open websocket
            final WebClientEndpoint clientEndPoint = new WebClientEndpoint(new URI("wss://ws.coinapi.io/v1/"));

            // add listener
            clientEndPoint.addMessageHandler(new WebClientEndpoint.MessageHandler() {
                public void handleMessage(String message) {
                    System.out.println(message);
                }
            });

            String msg = "{\"type\":\"hello\",\"apikey\":\"73034021-0EBC-493D-8A00-E0F138111F41\",\"heartbeat\": false,\"subscribe_data_type\":[\"quote\"],\"subscribe_filter_asset_id\":[\"BTC\",\"ETH\"]}";

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


            // send message to websocket
            clientEndPoint.sendMessage(msg);

            // wait 5 seconds for messages from websocket
            Thread.sleep(2000);

            // System.out.println("Start typing messages:");
            // Wait 5 seconds for messages from websocket
            // Scanner reader = new Scanner(System.in);
            // String input = reader.nextLine();
            // while (!input.equals("quit"))
            // {
            //     clientEndPoint.sendMessage(input);
            //     input = reader.nextLine();
            // }

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }
}


