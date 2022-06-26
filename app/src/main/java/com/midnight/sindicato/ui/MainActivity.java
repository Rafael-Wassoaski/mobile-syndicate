package com.midnight.sindicato.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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
        Drawable img = getApplicationContext().getResources().getDrawable(R.drawable.ic_info);
        img.setBounds( 0, 0, 256, 256 );
        img.setColorFilter( 0xffff0000, PorterDuff.Mode.MULTIPLY );

        Button button1 = new Button(this);
        button1.setBackground(getDrawable(R.drawable.square));
        button1.setCompoundDrawables(null, img, null , null);
        button1.setPadding(0, 60, 0, 0);
        button1.setCompoundDrawablePadding(-15);

        button1.setText("aaaa");

        addButtonToTable(button1);

        Button button2 = new Button(this);
        button2.setBackground(getDrawable(R.drawable.square));
        button2.setCompoundDrawables(null, img, null , null);
        button2.setPadding(0, 60, 0, 0);
        button2.setCompoundDrawablePadding(-15);
        button2.setText("aaaa");
        addButtonToTable(button2);

        Button button3 = new Button(this);
        button3.setBackground(getDrawable(R.drawable.square));
        button3.setCompoundDrawables(null, img, null , null);
        button3.setPadding(0, 60, 0, 0);

        button3.setCompoundDrawablePadding(-15);

        button3.setText("aaaa");
        addButtonToTable(button3);
    }

    private void addButtonToTable(Button button){
        int tableLayoutChildrens = tableLayout.getChildCount();
        TableRow tableRow = (TableRow) tableLayout.getChildAt(tableLayoutChildrens - 1);

        if(tableRow.getChildCount() == 2){
            tableRow = new TableRow(this);
            tableRow.setPadding(0, 0, 0, 50);
            TableRow.LayoutParams tableRowLayout = new TableRow.LayoutParams();
            tableRowLayout.rightMargin = 50;
            button.setLayoutParams(tableRowLayout);

            tableRow.setLayoutParams(tableRowLayout);
            tableLayout.addView(tableRow);
        }

        tableRow.addView(button);
    }
}