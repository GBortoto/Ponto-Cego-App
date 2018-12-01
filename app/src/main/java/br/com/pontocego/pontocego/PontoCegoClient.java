package br.com.pontocego.pontocego;

import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class PontoCegoClient {

    public PontoCegoInterface buildClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://localhost:8080") //Dps criar aplication properties para injetar url
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit.create(PontoCegoInterface.class);
    }

    public ServerResponse findBus(final ServerRequest request) {
        PontoCegoInterface client = buildClient();

        Call<ServerResponse> call = client.findBus(request);

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                if(!response.isSuccessful()) {
                    // exemplo para receber response code
                    // textViewResult.setText("Code: " + response.code());
                    return;
                }

                ServerResponse post = response.body();
                


            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });

        try {
            return client.findBus(request).execute().body();
        } catch (IOException Ex) {
            throw new RuntimeException();
        }
    }
}
