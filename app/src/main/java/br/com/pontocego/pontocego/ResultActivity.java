package br.com.pontocego.pontocego;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class ResultActivity extends AppCompatActivity {

    private String[] rawSearchResult;
    private TextView resultTextTextView;
    private TextView resultNumberTextView;


    // result[0] --> search result text
    // result[1] --> search result numbers
    private String[] result = new String[2];

    private FusedLocationProviderClient client;

    ServerRequest request = new ServerRequest();
    PontoCegoClient pontoCegoClient = new PontoCegoClient();

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
        //final Button accept_btn = findViewById(R.id.accept_btn);
        //accept_btn.setOnClickListener(new View.OnClickListener() {


            requestPermission();

            client = LocationServices.getFusedLocationProviderClient(this);

            Button button = findViewById(R.id.accept_btn);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (ActivityCompat.checkSelfPermission( ResultActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    client.getLastLocation().addOnSuccessListener(ResultActivity.this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {

                            if (location!=null){
                                request.setLatitude(location.getLatitude());
                                request.setLongitude(location.getLongitude());
                                request.setDesiredLine(result[1]+" "+result[0]);
                                ServerResponse response = pontoCegoClient.findBus(request);
                            }

                        }
                    });
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


    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
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