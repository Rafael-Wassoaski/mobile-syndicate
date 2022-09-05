package com.midnight.sindicato.ui.voucher;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TableLayout;

import com.midnight.sindicato.R;
import com.midnight.sindicato.controller.TableController;
import com.midnight.sindicato.databinding.ActivityVoucherBinding;
import com.midnight.sindicato.entity.News;
import com.midnight.sindicato.entity.Voucher;

public class VoucherActivity extends AppCompatActivity {

    private ActivityVoucherBinding binding;
    private TableLayout tableLayout;
    private TableController tableController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityVoucherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());
        this.tableLayout = (TableLayout) findViewById(R.id.tableLayout1);

        tableController = new TableController(tableLayout, this);

        Voucher news = new Voucher("Meu vale", "Vale cesta básica");

        tableController.addRow(news);
    }
}