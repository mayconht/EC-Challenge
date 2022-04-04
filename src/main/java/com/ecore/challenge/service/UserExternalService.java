package com.ecore.challenge.service;

import com.ecore.challenge.config.Helper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserExternalService {

    @Value("${usersEndpoint}")
    private String userEndpoint;

    public boolean hasUserById(final String userId) throws Exception {
        return Helper.retrieveJson(userEndpoint + userId).has("id");
    }

}
