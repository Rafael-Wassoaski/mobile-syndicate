package com.midnight.sindicato.controller;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.view.View;

import com.midnight.sindicato.R;

public abstract class ActionController {
    private String actionName;
    private int color;
    private int icon;

    public ActionController(String actionName){
        this.actionName = actionName;
        this.icon = R.drawable.ic_info;
        this.color = 0xffff0000;
    }

    public ActionController(String actionName, int color, int icon) {
        this.actionName = actionName;
        this.color = color;
        this.icon = icon;
    }

    public String getActionName() {
        return actionName;
    }

    public int getColor() {
        return color;
    }

    public int getIcon() {
        return icon;
    }

    public abstract String performAction();

    public abstract void clickAction(View view);
}
