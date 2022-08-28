package com.midnight.sindicato.entity;

import java.util.Date;

public class Certificate {
    private String name;
    private String description;
    private Date lastIssue;
    private Date nextIssue;

    public Certificate(String name, String description){
        this.name = name;
        this.description = description;
        this.lastIssue = null;
        this.nextIssue = null;
    }

    public Certificate(String name, String description, Date lastIssue){
        this(name, description);
        this.lastIssue = lastIssue;
    }

    public Certificate(String name, String description, Date lastIssue, Date nextIssue){
        this(name, description, lastIssue);
        this.nextIssue = nextIssue;
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

    public void setLastIssue(Date lastIssue) {
        this.lastIssue = lastIssue;
    }

    public Date getNextIssue() {
        return nextIssue;
    }

    public void setNextIssue(Date nextIssue) {
        this.nextIssue = nextIssue;
    }
}
