package com.example.momen.capstone_stage2.api;

import com.example.momen.capstone_stage2.model.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Momen on 4/14/2019.
 */

public interface TriviaService {

    @GET(".")
    Call<Response> getQuestions(@Query("amount") String amount,@Query("category") String category,
                                      @Query("difficulty") String difficulty,@Query("type") String type);
}
