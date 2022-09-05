package com.midnight.sindicato.entity;

import java.util.Date;

public class News implements BaseDocument {
    private String name;
    private String description;
    private Date lastIssue;
    private Date nextIssue;
    private String news;

    public News(String name, String description, String news) {
        this.name = name;
        this.description = description;
        this.lastIssue = null;
        this.nextIssue = null;
        this.news = news;
    }

    public News(String name, String description, Date lastIssue, String news) {
        this(name, description, news);
        this.lastIssue = lastIssue;
    }

    public News(String name, String description, Date lastIssue, Date nextIssue, String news) {
        this(name, description, lastIssue, news);
        this.nextIssue = nextIssue;
    }

    public String getNews() {
        return news;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getLastIssue() {
        return lastIssue;
    }

    public Date getNextIssue() {
        return nextIssue;
    }

}
