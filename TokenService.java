package com.Kishan.ReceipeManagementSystem.service;

import com.Kishan.ReceipeManagementSystem.repository.ITokenRepo;
import com.Kishan.ReceipeManagementSystem.model.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Autowired
    ITokenRepo tokenRepo;
    public void saveToken(AuthenticationToken token) {
        tokenRepo.save(token);
    }

    public boolean authenticate(String email , String token){
        if(token==null && email==null)
            return false;
        AuthenticationToken authToken = tokenRepo.findFirstByToken(token);
        if(authToken == null)
            return false;
        String exceptedEmail = authToken.getUser().getEmail();
        return exceptedEmail.equals(email);
    }

    public void deleteToken(String token){
        AuthenticationToken generatedToken = tokenRepo.findFirstByToken(token);
        tokenRepo.deleteById(generatedToken.getTokenId());
    }
}
