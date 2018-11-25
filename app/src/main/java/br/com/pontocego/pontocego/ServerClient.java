package br.com.pontocego.pontocego;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ServerClient {
    public ServerClientInterface buildClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://localhost:8080") //Dps criar aplication properties para injetar url
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit.create(ServerClientInterface.class);
    }

    public ServerResponse findBus(ServerRequest request) {
        ServerClientInterface client = buildClient();

        try {
            return client.findBus(request).execute().body();
        } catch (IOException Ex) {
            throw new RuntimeException();
        }
    }
}
