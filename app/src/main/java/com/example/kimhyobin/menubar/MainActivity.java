package com.example.kimhyobin.menubar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import kr.co.ucsit.csmenulibrary.CSMenuView;
import kr.co.ucsit.csmenulibrary.interfaces.CSMenuEventInterface;

public class MainActivity extends AppCompatActivity implements CSMenuEventInterface{

    private CSMenuView csMenuView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        csMenuView = (CSMenuView)findViewById(R.id.csMenu);
        csMenuView.setImageViewLogoResource(R.mipmap.ic_launcher);
        //csMenuView.setTitle("test");
        csMenuView.setCSMenuEventInterface(this);
    }

    @Override
    public void OnLeftImageClicked() {
        Toast.makeText(getApplicationContext(), "OnLeftImageClicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnRightImageClicked() {
        Toast.makeText(getApplicationContext(), "OnRightImageClicked", Toast.LENGTH_SHORT).show();
    }
}
