package com.midnight.sindicato.entity;

import java.util.Date;

public interface BaseDocument {

    public String getName();

    public String getDescription();

    public Date getLastIssue();

    public Date getNextIssue();
}
