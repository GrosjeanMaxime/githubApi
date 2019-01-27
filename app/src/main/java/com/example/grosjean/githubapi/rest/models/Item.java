package com.example.grosjean.githubapi.rest.models;

public class Item {
    private int id;
    private String name;
    private String full_name;
    private String description;
    private String language;

    public int getId()       { return id; }
    public String getName()     { return name; }
    public String getFullName() { return full_name; }
    public String getDescription() { return description; }
    public String getLanguage() { return language; }
}
