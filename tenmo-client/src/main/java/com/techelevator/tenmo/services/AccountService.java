package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.User;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

public class AccountService {

    private String API_BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;

    public AccountService(String API_BASE_URL, AuthenticatedUser currentUser) {
        this.API_BASE_URL = API_BASE_URL;
        this.currentUser = currentUser;
    }

    public BigDecimal getAccountBalance() {
        BigDecimal accountBalance = new BigDecimal(0);
        try {
            accountBalance = restTemplate.exchange(API_BASE_URL + "balance/" + currentUser.getUser().getId(), HttpMethod.GET, makeAuthEntity(), BigDecimal.class).getBody();
            System.out.println("Your current account balance is: $" + accountBalance);
        } catch (RestClientException e) {
            System.out.println("Error getting balance");
        }
        return accountBalance;
    }

    public User[] findAllUsers(){
        // Method adds extra user in App
        User[] users = null;

        try {
            users = restTemplate.exchange(API_BASE_URL + "transfer/users", HttpMethod.GET, makeAuthEntity(), User[].class).getBody();
            for (User i : users) {
                System.out.println(i.toString());
            }
        } catch (RestClientResponseException e) {
            System.out.println("Error getting users");
        }
        return users;

    }



    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        return new HttpEntity<>(headers);
    }

}
