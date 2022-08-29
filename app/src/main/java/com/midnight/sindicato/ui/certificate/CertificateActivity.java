package com.midnight.sindicato.ui.certificate;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.midnight.sindicato.R;
import com.midnight.sindicato.databinding.ActivityCertificateBinding;
import com.midnight.sindicato.entity.Certificate;

public class CertificateActivity extends AppCompatActivity {

    private ActivityCertificateBinding binding;
    private TableLayout tableLayout;

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

        Certificate certificate = new Certificate("Vale real", "teste de vale com esse tamanho");

        this.addCertificateRow(certificate);
    }


    private void addCertificateRow(Certificate certificate){
        TableRow row = new TableRow(this);
        TextView certificateName = new TextView(this);
        TextView certificateDescription = new TextView(this);

        certificateName.setText(certificate.getName());
        certificateDescription.setText(certificate.getDescription());

        row.addView(certificateName);
        row.addView(certificateDescription);

        if(certificate.getLastIssue() != null){
            TextView certificateLastIssue = new TextView(this);
            certificateLastIssue.setText(certificate.getLastIssue().toString());
        }

        if(certificate.getNextIssue() != null){
            TextView certificateNextIssue = new TextView(this);
            certificateNextIssue.setText(certificate.getNextIssue().toString());
        }
        tableLayout.addView(row);
    }
}