/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.dip.csclient.httpurlconnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.util.io.Streams;

/**
 *
 * @author pramoth
 */
public class App {

    public static void main(String[] args) throws MalformedURLException {
        HttpURLConnection openConnection = null;
        try {
            URL url = new URL("http://epayment.ipthailand.go.th/api/ws/counter-service/000000000000000999/201701301130590?amount=2000&txId=13438337");
            openConnection = (HttpURLConnection) url.openConnection();
            openConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            openConnection.setRequestProperty("Accept", "application/json;charset=UTF-8");
            String result = new String(Streams.readAll(openConnection.getInputStream()),StandardCharsets.UTF_8);
            System.out.println(result);
        } catch (IOException ex) {
            if (openConnection != null) {
                String result;
                try {
                    result = new String(Streams.readAll(openConnection.getErrorStream()),StandardCharsets.UTF_8);
                    System.out.println(result);
                } catch (IOException ex1) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }
}
