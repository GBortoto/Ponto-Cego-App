package br.com.pontocego.pontocego;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServerClientInterface {
    @POST("buses")
    Call<ServerResponse> findBus(@Body ServerRequest request);
}
