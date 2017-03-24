/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.dip.csclient.httpurlconnection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.util.io.Streams;
import org.junit.Test;

/**
 *
 * @author pramoth
 */
public class EpaymentTest {
    @Test
    public void test_confirm_with_json(){
        HttpURLConnection openConnection = null;
        try {
            URL url = new URL("http://epayment.ipthailand.go.th/api/ws/counter-service/000000000000001000/201703162310309");
            openConnection = (HttpURLConnection) url.openConnection();
            openConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            openConnection.setRequestProperty("Accept", "application/json;charset=UTF-8");
            openConnection.setRequestMethod("POST");
            String json ="{\"txId\": \"100001\", \"reference1\": \"000000000000001000\",\"reference2\": \"201703162310309\",\"amount\": \"4000\",\"paymentDateTime\": \"2017-03-04T16:30:48\"}";
            openConnection.setDoOutput(true);
            openConnection.getOutputStream().write(json.getBytes(StandardCharsets.UTF_8));
            openConnection.getOutputStream().flush();
            String result = new String(Streams.readAll(openConnection.getInputStream()),StandardCharsets.UTF_8);
            System.out.println(result);
        } catch (IOException ex) {
            if (openConnection != null && openConnection.getErrorStream()!=null) {
                String result;
                try {
                    result = new String(Streams.readAll(openConnection.getErrorStream()),StandardCharsets.UTF_8);
                    System.out.println(result);
                } catch (IOException ex1) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }else{
                ex.printStackTrace();
            }
        }
    }
}
