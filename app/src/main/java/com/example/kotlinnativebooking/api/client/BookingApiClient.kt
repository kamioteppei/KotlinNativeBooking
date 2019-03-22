package com.example.kotlinnativebooking.api.client


import com.example.kotlinnativebooking.model.BookableData
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface BookingApiClient {

    @GET("api/v1/bookables/")
    fun getBookables(@Query("dtFrom") dtFrom: String, @Query("dtTo") dtTo: String): Observable<List<BookableData>>

}