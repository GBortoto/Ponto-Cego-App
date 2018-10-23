package br.com.pontocego.pontocego;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private String[] searchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        this.searchResult = getIntent().getStringArrayExtra("searchResult");
    }
}
