package com.midnight.sindicato.ui.news;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.TableLayout;

import com.midnight.sindicato.R;
import com.midnight.sindicato.controller.TableController;
import com.midnight.sindicato.databinding.ActivityNewsBinding;
import com.midnight.sindicato.entity.News;

public class NewsActivity extends AppCompatActivity {

    private ActivityNewsBinding binding;
    private TableLayout tableLayout;
    private TableController tableController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());
        this.tableLayout = (TableLayout) findViewById(R.id.tableLayout1);

        tableController = new TableController(tableLayout, this);

        News news = new News("Deu boa", "Resumo da news", "Minha news total");

        tableController.addRow(news);
    }
}