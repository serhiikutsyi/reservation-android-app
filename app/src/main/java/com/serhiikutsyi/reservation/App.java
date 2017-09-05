package com.serhiikutsyi.reservation;

import android.app.Application;

import com.serhiikutsyi.reservation.service.ReservationService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Serhii Kutsyi
 */
public class App extends Application {

    private static ReservationService reservationService;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://reservation-service-demo.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        reservationService = retrofit.create(ReservationService.class);
    }

    public static ReservationService getApi() {
        return reservationService;
    }
}