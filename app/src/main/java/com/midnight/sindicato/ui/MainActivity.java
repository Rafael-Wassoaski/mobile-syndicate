package com.midnight.sindicato.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.midnight.sindicato.R;

public class MainActivity extends AppCompatActivity {
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableLayout = (TableLayout) findViewById(R.id.tableLayout1);

        Intent intent = getIntent();
        String message = intent.getStringExtra("Message");

        Log.d("Message", message);

        Button button1 = new Button(this);
        button1.setBackground(getDrawable(R.drawable.square));
        button1.setText("aaaa");

        addButtonToTable(button1);

        Button button2 = new Button(this);
        button2.setBackground(getDrawable(R.drawable.square));
        Drawable img = getApplicationContext().getResources().getDrawable(R.drawable.ic_launcher_background);
        img.setBounds( 0, 0, 60, 60 );
        button2.setCompoundDrawables(null, img, null , null);
        button2.setPadding(0, 60, 0, 0);
        button2.setCompoundDrawablePadding(-15);
        button2.setWidth(10);
        button2.setHeight(50);
        button2.setText("aaaa");

        addButtonToTable(button2);

        Button button3 = new Button(this);
        button3.setBackground(getDrawable(R.drawable.square));
        button3.setText("aaaa");

        addButtonToTable(button3);
    }

    private void addButtonToTable(Button button){
        int tableLayoutChildrens = tableLayout.getChildCount();
        TableRow tableRow = (TableRow) tableLayout.getChildAt(tableLayoutChildrens - 1);

        if(tableRow.getChildCount() == 2){
            tableRow = new TableRow(this);

            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            tableLayout.addView(tableRow);
        }

        tableRow.addView(button);
    }
}