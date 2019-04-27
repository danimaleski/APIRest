package com.example.udesc.apirestaula270419;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MoviesService{

    private String baseUrl;
    private String repositoryName;
    private String fullUrl;
    private URL url;
    private HttpURLConnection connection;

    public MoviesService(){
        this.baseUrl = "http://10.0.2.2:8080"; //Eh o mesmo que localhost:8080
        this.repositoryName = "movies";
        this.fullUrl = this.baseUrl+"/"+this.repositoryName;
    }

    public Void getAll(){

        StringBuffer content = new StringBuffer();

        try {
            this.url = new URL(this.fullUrl);
            this.connection = (HttpURLConnection) this.url.openConnection();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(this.connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            JSONArray list = new JSONArray(content.toString());

            for (int i = 0; i < list.length(); i++) {
                JSONObject movie = list.getJSONObject(i);
                System.out.println(movie);
            }

            //System.out.println(content.toString());

        } catch (Throwable e) {
            e.printStackTrace();
        }

        return null;
    }
}
