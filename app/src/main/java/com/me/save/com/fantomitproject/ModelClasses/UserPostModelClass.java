package com.me.save.com.fantomitproject.ModelClasses;

/**
 * Created by acer on 6/8/2017.
 */

public class UserPostModelClass {

    private String story;
    private String id;
    private String createdTime;
    private String message;


    public UserPostModelClass(String story, String id, String createdTime, String message) {
        this.story = story;
        this.id = id;
        this.createdTime = createdTime;
        this.message = message;
    }

    public UserPostModelClass(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
