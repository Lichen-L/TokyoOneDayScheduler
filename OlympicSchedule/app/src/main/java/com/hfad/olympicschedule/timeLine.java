package com.hfad.olympicschedule;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class timeLine extends AppCompatActivity {
    String datePassed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);

        Intent intent = getIntent();
        datePassed = intent.getStringExtra("date");

        Toolbar toolbar = findViewById(R.id.toolbarTimeLine);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbarLayoutTimeline);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarLayout.setTitle(datePassed);
        toolbarLayout.setTitleEnabled(true);
        toolbarLayout.setExpandedTitleColor(Color.WHITE);
        toolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        FloatingActionButton fab = findViewById(R.id.fabTimeLine);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //         .setAction("Action", null).show();
                Intent intent = new Intent(timeLine.this, chooseSportToAdd.class);
                intent.putExtra("date",datePassed);
                startActivity(intent);
            }
        });
        ConstraintLayout constraintLayout = findViewById(R.id.ConsLayout);

        Button button = new Button(this);
        button.setText("Hello");

        int buttonId = View.generateViewId();
        button.setId(buttonId);

        constraintLayout.addView(button);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        DisplayMetrics metrics = timeLine.this.getResources().getDisplayMetrics();

        constraintSet.constrainWidth(button.getId(), (int) (320 * metrics.density));
        constraintSet.constrainHeight(button.getId(), (int) (50 * metrics.density));

        constraintSet.connect(button.getId(), ConstraintSet.TOP, R.id.lineEight, ConstraintSet.BOTTOM, (int) (20 * metrics.density));
        constraintSet.connect(button.getId(), ConstraintSet.START, R.id.textEight, ConstraintSet.END, 0);
        constraintSet.applyTo(constraintLayout);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int itemId = item.getItemId();
        if (itemId == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits; // ~ → inverse
        }
        win.setAttributes(winParams);
    }

}
