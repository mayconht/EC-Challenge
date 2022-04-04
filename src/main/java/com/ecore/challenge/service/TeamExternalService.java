package com.ecore.challenge.service;

import com.ecore.challenge.config.Helper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamExternalService {

    private static final String TEAM_LEAD_ID = "teamLeadId";
    private static final String TEAM_MEMBER_ID = "teamMemberIds";

    @Value("${teamsEndpoint}")
    private String teamEndpoint;

    public List<String> searchTeamById(final String teamId) throws Exception {
        final JSONObject teamObj = Helper.retrieveJson(teamEndpoint + teamId);
        final JSONArray userIds = teamObj.getJSONArray(TEAM_MEMBER_ID);
        final List<String> result = new ArrayList<>();
        result.add(teamObj.getString(TEAM_LEAD_ID));
        for (int i = 0; i < userIds.length(); i++) {
            result.add(userIds.getString(i));
        }
        return result;
    }
}
