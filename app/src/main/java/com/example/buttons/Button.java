package com.example.buttons;

public class Button
{
    public String color;
    public  int clicks;

    public Button(String color, int clicks)
    {
        this.color = color;
        this.clicks = clicks;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public int getClicks()
    {
        return clicks;
    }

    public void setClicks(int clicks)
    {
        this.clicks = clicks;
    }
}
