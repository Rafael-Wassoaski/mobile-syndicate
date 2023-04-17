package com.midnight.sindicato.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.midnight.sindicato.R;
import com.midnight.sindicato.controller.ActionController;
import com.midnight.sindicato.controller.implementation.CertificateController;
import com.midnight.sindicato.controller.implementation.NewsController;
import com.midnight.sindicato.controller.implementation.VoucherController;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TableLayout tableLayout;
    private List<ActionController> actionControllers;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionControllers = new ArrayList<>();
        actionControllers.add(new VoucherController());
        actionControllers.add(new CertificateController());
        actionControllers.add(new NewsController());

        tableLayout = (TableLayout) findViewById(R.id.tableLayout1);
        this.context = getApplicationContext();

        this.initButtonsByList(actionControllers);
    }

    public static Context getContext() {
        return context;
    }

    private void initButtonsByList(List<ActionController> actionControllers){
        for (ActionController actionController : actionControllers) {
            Drawable img = getApplicationContext().getResources().getDrawable(actionController.getIcon());
            img.setBounds( 0, 0, 256, 256 );
            img.setColorFilter( 0xffff0000, PorterDuff.Mode.MULTIPLY );

            Button button = new Button(this);
            button.setBackground(getDrawable(R.drawable.square));
            button.setCompoundDrawables(null, img, null , null);
            button.setPadding(50, 50, 50, 50);
            button.setCompoundDrawablePadding(-15);
            button.setText(actionController.getActionName());

            button.setOnClickListener(actionController::clickAction);

            this.addButtonToTable(button);
        }
    }

    private void setNewActivityView(Class<?> clss){
        Intent intent = new Intent(this,clss);
        startActivity(intent);
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