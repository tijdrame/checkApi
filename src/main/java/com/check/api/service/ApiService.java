package com.check.api.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;

import com.check.api.request.CheckApiRequest;
import com.check.api.response.GenericResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
    private final Logger log = LoggerFactory.getLogger(ApiService.class);

    public GenericResponse checkApi(CheckApiRequest apiRequest) {
        GenericResponse response = new GenericResponse();
        HttpURLConnection conn = null;
        try {
            conn = doConnexion(apiRequest.getUrl());
            if(conn.getResponseCode()==200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String ligne = br.readLine();
                String result ="";
                while (ligne != null) {
                    result += ligne;
                    ligne = br.readLine();
                }
                log.info("resp envoi ===== [{}]", result);
                JSONObject myObj = new JSONObject(result);
                log.info("resp code ===== [{}]", myObj.getString("code"));
                if(myObj.getInt("code")==200){
                    response.setCode(200);
                    response.description("Service is up and running.");
                    response.dateResponse(Instant.now());
                }else {
                    response.setCode(404);
                    response.description("Ping not responding .Service is down.");
                    response.dateResponse(Instant.now());
                }
                
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                String ligne = br.readLine();
                String result ="";
                while (ligne != null) {
                    result += ligne;
                    ligne = br.readLine();
                }
                log.info("resp envoi ===== [{}]", result);
                response.setCode(404);
                response.description("Ping not responding .Service is down.");
                response.dateResponse(Instant.now());
            }
        } catch (IOException e) {
            response.setCode(404);
            response.description("Ping not responding .Service is down. Message="+e.getMessage());
            response.dateResponse(Instant.now());
            return response;
        }
        
        
        return response;
    }
    
    public HttpURLConnection doConnexion(String endPoint) throws IOException {
        //OutputStream os = null;
        HttpURLConnection conn = null;
		try {
			log.info("end point wso2== [{}]", endPoint);
            URL url = new URL(endPoint);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
			conn.connect();
            
        }catch(Exception e){
            log.error("Error in doConn endpoint[{}], params [{}] & trace [{}]", e);
            return conn;
        }
        //os.close();
        return conn;
    }

}