package com.santander.user.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SaleClientAPI {

    private final RestTemplate restTemplate;
    @Value("${sale.base.url}")
    String baseURL;
    public SaleClientAPI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void createCart(String userID){
        restTemplate.postForLocation(baseURL+"/user/"+userID, void.class);
    }

    public void deleteCart(String userID){
        restTemplate.delete(baseURL+"/user/"+userID);
    }
}