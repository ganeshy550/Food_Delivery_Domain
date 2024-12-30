package com.example.Restaurant.Dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthRequest {

    private String ownerName;

    private String passWord;

    public AuthRequest() {
    }

    public AuthRequest(String ownerName, String passWord) {
        this.ownerName = ownerName;
        this.passWord = passWord;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
