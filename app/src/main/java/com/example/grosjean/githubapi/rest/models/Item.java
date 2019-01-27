package com.example.grosjean.githubapi.rest.models;

public class Item {
    private int id;
    private String name;
    private String full_name;
    private String description;
    private String language;
    private String branches_url;
    private String contributors_url;

    public int getId()       { return id; }
    public String getName()     { return name; }
    public String getFullName() { return full_name; }
    public String getDescription() { return description; }
    public String getLanguage() { return language; }
    public String getBranches_url() { return branches_url; }
    public String getContributors_url() { return contributors_url; }

}
