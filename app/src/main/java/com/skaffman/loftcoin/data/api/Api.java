package com.skaffman.loftcoin.data.api;

import com.skaffman.loftcoin.data.api.model.RateResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Api {

    String CONVERT = "USD,EUR,RUB";

    @GET("cryptocurrency/listings/latest")
    @Headers("X-CMC_PRO_API_KEY: 9ee29142-146d-4e89-825d-385ab52b618a")
    Call<RateResponse> rates(@Query("convert") String convert);

}
