package com.roblesc.model;

public class User
{
    private String username;
    private String bio;
    private String webpage;
    private int age;

    public String getUsername()
    { return this.username; }
    public String getBio()
    { return this.bio; }
    public String getWebpage()
    { return this.webpage; }
    public int getAge()
    { return this.age; }

    public void setUsername(String username)
    { this.username = username; }
    public void setBio(String bio)
    { this.bio = bio; }
    public void setWebpage(String webpage)
    { this.webpage = webpage; }
    public void setAge(int age)
    { this.age = age; }
}
