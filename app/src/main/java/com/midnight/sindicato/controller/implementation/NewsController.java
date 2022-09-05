package com.midnight.sindicato.controller.implementation;

import android.util.Log;
import android.view.View;

import com.midnight.sindicato.controller.ActionController;
import com.midnight.sindicato.ui.MainActivity;
import com.midnight.sindicato.ui.news.NewsActivity;

public class NewsController extends ActionController {

    public NewsController(){
        super("Notícias");
    }

    @Override
    public String performAction() {
        return null;
    }

    @Override
    protected Class<?> getControllerActivity() {
        return NewsActivity.class;
    }
}
