package com.skaffman.loftcoin.data.api;

import com.skaffman.loftcoin.data.api.model.RateResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Api {

    String CONVERT = "USD,EUR,RUB";

    @GET("cryptocurrency/listings/latest")
    @Headers("X-CMC_PRO_API_KEY:9940d22c-d93e-4167-8556-9ae4b7d3c2c9")
    Observable<RateResponse> rates(@Query("convert") String convert);

}
