package com.cybersoft.food_project.utils;

public enum Url {
    Root("http://localhost:8080/api/images/");

    private String path;
    Url(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

