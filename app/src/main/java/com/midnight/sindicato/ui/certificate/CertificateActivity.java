package com.midnight.sindicato.ui.certificate;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.midnight.sindicato.R;
import com.midnight.sindicato.controller.TableController;
import com.midnight.sindicato.databinding.ActivityCertificateBinding;
import com.midnight.sindicato.entity.Certificate;

public class CertificateActivity extends AppCompatActivity {

    private ActivityCertificateBinding binding;
    private TableLayout tableLayout;
    private TableController tableController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCertificateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());
        this.tableLayout = (TableLayout) findViewById(R.id.tableLayout1);
        tableController = new TableController(tableLayout, this);

        Certificate certificate = new Certificate("Vale real", "teste de vale com esse tamanho");

        tableController.addRow(certificate);
    }
}