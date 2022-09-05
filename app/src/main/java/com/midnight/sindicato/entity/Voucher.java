package com.midnight.sindicato.entity;

import java.util.Date;

public class Voucher implements BaseDocument{

    private String name;
    private String description;
    private Date lastIssue;
    private Date nextIssue;

    public Voucher(String name, String description){
        this.name = name;
        this.description = description;
        this.lastIssue = null;
        this.nextIssue = null;
    }

    public Voucher(String name, String description, Date lastIssue){
        this(name, description);
        this.lastIssue = lastIssue;
    }

    public Voucher(String name, String description, Date lastIssue, Date nextIssue){
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

    public Date getNextIssue() {
        return nextIssue;
    }
}
