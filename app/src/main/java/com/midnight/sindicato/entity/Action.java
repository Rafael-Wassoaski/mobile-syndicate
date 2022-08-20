package com.midnight.sindicato.entity;

import android.graphics.PorterDuff;
import android.view.View;

import com.midnight.sindicato.R;

public abstract class Action {
    private String actionName;
    private int color;
    private int icon;

    public Action(String actionName){
        this.actionName = actionName;
        this.icon = R.drawable.ic_info;
        this.color = 0xffff0000;
    }

    public Action(String actionName, int color, int icon) {
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

    public abstract String performeAction();

    public abstract void clickAction(View view);
}
