package fr.ucfar0.quizzgsi.model;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class RequeteHTTP {

    private String url;


    public RequeteHTTP(String url) {
        this.url = url;
    }

    public String execute() {
        URI uri = null;
        HttpResponse res = null;
        String webcontent = null;
        try{
            uri = new URI(url);
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(uri);
            res = client.execute(httpGet);
            InputStream data = res.getEntity().getContent();
            webcontent = generateString(data);
        } catch (URISyntaxException e){
            e.printStackTrace();
        } catch (ClientProtocolException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return webcontent;
    }

    private String generateString(InputStream stream){
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader buffer = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        try{
            String cur;
            while((cur = buffer.readLine()) != null){
                sb.append(cur + System.getProperty("line.separator"));
            }
            stream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }
}
