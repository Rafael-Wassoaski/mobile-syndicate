package com.midnight.sindicato.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.midnight.sindicato.R;
import com.midnight.sindicato.entity.Action;
import com.midnight.sindicato.entity.implementation.Certificate;
import com.midnight.sindicato.entity.implementation.News;
import com.midnight.sindicato.entity.implementation.Voucher;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TableLayout tableLayout;
    private List<Action> actions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actions = new ArrayList<>();
        actions.add(new Voucher());
        actions.add(new Certificate());
        actions.add(new News());

        tableLayout = (TableLayout) findViewById(R.id.tableLayout1);

        this.initButtonsByList(actions);
    }

    private void initButtonsByList(List<Action> actions){
        for (Action action: actions) {
            Drawable img = getApplicationContext().getResources().getDrawable(action.getIcon());
            img.setBounds( 0, 0, 256, 256 );
            img.setColorFilter( 0xffff0000, PorterDuff.Mode.MULTIPLY );

            Button button = new Button(this);
            button.setBackground(getDrawable(R.drawable.square));
            button.setCompoundDrawables(null, img, null , null);
            button.setPadding(50, 50, 50, 50);
            button.setCompoundDrawablePadding(-15);
            button.setText(action.getActionName());

            button.setOnClickListener(action::clickAction);

            this.addButtonToTable(button);
        }
    }

    private void addButtonToTable(Button button){
        int tableLayoutChildrens = tableLayout.getChildCount();
        TableRow tableRow = (TableRow) tableLayout.getChildAt(tableLayoutChildrens - 1);

        if(tableRow == null ||tableRow.getChildCount() == 2){
            tableRow = new TableRow(this);
            tableRow.setPadding(0, 50, 0, 50);
            TableRow.LayoutParams tableRowLayout = new TableRow.LayoutParams();
            tableRowLayout.rightMargin = 50;
            button.setLayoutParams(tableRowLayout);

            tableRow.setLayoutParams(tableRowLayout);
            tableLayout.addView(tableRow);
        }

        tableRow.addView(button);
    }
}