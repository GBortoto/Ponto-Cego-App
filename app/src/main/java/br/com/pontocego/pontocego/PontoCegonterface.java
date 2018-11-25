package br.com.pontocego.pontocego;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PontoCegonterface {
    @POST("buses")
    Call<ServerResponse> findBus(@Body ServerRequest request);
}
