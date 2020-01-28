package com.example.buttons;

import java.util.HashMap;
import java.util.Map;

public class Button
{
    public String id;
    public String color;
    public  int clicks;

    public Button(String id, String color, int clicks) {
        this.id = id;
        this.color = color;
        this.clicks = clicks;
    }

    public Button(String id, Map<String, Object> data)
    {
        this.id = id;
        this.color = data.get("color").toString();
        this.clicks = (int)(long)data.get("clicks");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public void addClick()
    {
        clicks++;
    }

    public Map<String, Object> toMap()
    {
        Map<String, Object> map = new HashMap<>();

        map.put("color", color);
        map.put("clicks", clicks);

        return map;
    }
}
