package com.ecore.challenge.service;

import com.ecore.challenge.config.RestHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserExternalService {

    @Value("${usersEndpoint}")
    private String userEndpoint;

    public boolean searchUserById(final String id) throws Exception {
        return RestHelper.getJson(userEndpoint + id).has("id");
    }

}
