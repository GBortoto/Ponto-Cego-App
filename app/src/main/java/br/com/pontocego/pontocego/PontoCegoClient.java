package br.com.pontocego.pontocego;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class PontoCegoClient {
    public PontoCegonterface buildClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://localhost:8080") //Dps criar aplication properties para injetar url
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit.create(PontoCegonterface.class);
    }

    public ServerResponse findBus(ServerRequest request) {
        PontoCegonterface client = buildClient();

        try {
            return client.findBus(request).execute().body();
        } catch (IOException Ex) {
            throw new RuntimeException();
        }
    }
}
