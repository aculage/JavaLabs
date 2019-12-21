package com.arseniculage;

public class User{
    private String name;
    private String login;
    private String password;

    public User(String name, String login, String password)
    {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    Boolean enter(String login, String password)
    {
        if ((this.login.equals(login))&&(this.password.equals(password))) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    String getName()
    {
        return this.name;
    }
    String getLogin()
    {
        return this.login;
    }
}
