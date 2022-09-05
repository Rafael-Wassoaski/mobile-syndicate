package com.midnight.sindicato.controller;

import android.content.Context;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.midnight.sindicato.entity.BaseDocument;
import com.midnight.sindicato.entity.Certificate;

public class TableController {

    private TableLayout tableLayout;
    private Context context;

    public TableController(TableLayout tableLayout, Context context) {
        this.tableLayout = tableLayout;
        this.context = context;
    }

    public void addRow(BaseDocument document){
        TableRow row = new TableRow(context);
        TextView certificateName = new TextView(context);
        TextView certificateDescription = new TextView(context);

        certificateName.setText(document.getName());
        certificateDescription.setText(document.getDescription());

        row.addView(certificateName);
        row.addView(certificateDescription);

        if(document.getLastIssue() != null){
            TextView certificateLastIssue = new TextView(context);
            certificateLastIssue.setText(document.getLastIssue().toString());
        }

        if(document.getNextIssue() != null){
            TextView certificateNextIssue = new TextView(context);
            certificateNextIssue.setText(document.getNextIssue().toString());
        }
        tableLayout.addView(row);
    }
}
