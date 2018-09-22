package com.derickfelix.githubrepository.factories;

import com.derickfelix.githubrepository.models.GithubRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonFactory {

    private JsonFactory()
    {
    }

    public static List<GithubRepository> githubRepositories(String json)
    {
        List<GithubRepository> githubRepositories = new ArrayList<>();

        try {
            JSONArray items = new JSONObject(json).getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);

                GithubRepository githubRepository = new GithubRepository();
                githubRepository.setTitle(item.getString("full_name"));
                githubRepository.setDescription(item.getString("description"));
                githubRepository.setLanguage(item.getString("language"));
                githubRepository.setStargazers(item.getInt("stargazers_count"));

                githubRepositories.add(githubRepository);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return githubRepositories;
    }

}
