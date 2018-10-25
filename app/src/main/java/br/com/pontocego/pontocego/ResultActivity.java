package br.com.pontocego.pontocego;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class ResultActivity extends AppCompatActivity {

    private String[] rawSearchResult;
    private TextView resultTextTextView;
    private TextView resultNumberTextView;


    // result[0] --> search result text
    // result[1] --> search result numbers
    private String[] result = new String[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // ------------------ Create references -------------------------------------
        resultTextTextView = (TextView) findViewById(R.id.result_text);
        resultNumberTextView = (TextView) findViewById(R.id.result_number);

        // ------------------ Get Intent Content ------------------------------------
        rawSearchResult = getIntent().getStringArrayExtra("searchResult");

        // ------------------ Processing --------------------------------------------
        result[0] = filterText(rawSearchResult[0]);
        result[1] = filterNumbers(rawSearchResult[0]);

        // ------------------ Setting Values ----------------------------------------
        resultTextTextView.setText(result[0]);
        resultNumberTextView.setText(result[1]);

        // ------------------ Accept Button -----------------------------------------
        final Button accept_btn = findViewById(R.id.accept_btn);
        accept_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
            }
        });

        // ------------------ Reject Button -----------------------------------------
        final Button reject_btn = findViewById(R.id.reject_btn);
        reject_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, Home.class);
                startActivity(intent);
            }
        });


    }

    public String charArrayListToString(ArrayList<Character> original){
        StringBuilder result = new StringBuilder(original.size());
        for (Character c : original) {
            result.append(c);
        }
        return result.toString();
    }

    private String filterText(String rawInput){
        ArrayList<Character> filteredText = new ArrayList<Character>();
        rawInput = rawInput.toLowerCase();

        // For each char in the input
        for(int i=0; i<rawInput.length(); i++){
            // If the char is a letter or a space, add it in the final string
            if(Character.isLetter(rawInput.charAt(i)) || rawInput.charAt(i)==' '){
                filteredText.add(rawInput.charAt(i));
            }
        }
        return charArrayListToString(filteredText).trim().replaceAll(" +", " ");
    }

    private String filterNumbers(String rawInput){
        ArrayList<Character> filteredText = new ArrayList<Character>();

        // For each char in the input
        for(int i=0; i<rawInput.length(); i++){
            // If the char is a number, add it in the final string
            if(Character.isDigit(rawInput.charAt(i))){
                filteredText.add(rawInput.charAt(i));
            }
        }

        return charArrayListToString(filteredText).trim();
    }
}