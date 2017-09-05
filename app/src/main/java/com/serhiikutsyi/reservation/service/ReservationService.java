package com.serhiikutsyi.reservation.service;

import com.serhiikutsyi.reservation.model.Reservation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author Serhii Kutsyi
 */
public interface ReservationService {

    @GET("/reservations")
    Call<List<Reservation>> getReservations();

}
