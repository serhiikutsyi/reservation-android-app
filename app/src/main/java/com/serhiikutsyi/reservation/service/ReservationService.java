package com.serhiikutsyi.reservation.service;

import com.serhiikutsyi.reservation.model.Reservation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * @author Serhii Kutsyi
 */
public interface ReservationService {

    @GET("/reservations")
    Call<List<Reservation>> getReservations();

    @POST("/reservations")
    Call<Reservation> createReservation(@Body Reservation reservation);

    @PUT("/reservations/{id}")
    Call<Reservation> editReservation(@Path("id") Long id, @Body Reservation reservation);

    @DELETE("/reservations/{id}")
    Call<Reservation> deleteReservation(@Path("id") Long id);

}
