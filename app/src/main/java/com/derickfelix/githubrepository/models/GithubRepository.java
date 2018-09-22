package com.derickfelix.githubrepository.models;

public class GithubRepository {

    private String title;
    private String description;
    private String language;
    private int stargazers;

    public GithubRepository()
    {
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public int getStargazers()
    {
        return stargazers;
    }

    public void setStargazers(int stargazers)
    {
        this.stargazers = stargazers;
    }
}
