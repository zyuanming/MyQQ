package org.yuanming.chat;

public class UserInfo
{
    private String name;
    private int password;
    
    public UserInfo(String name, int password)
    {
        this.name = name;
        this.password = password;
    }
    
    
    public String getName()
    {
        return name;
    }
     
    
    public int getPassword()
    {
        return password;
    } 
}
