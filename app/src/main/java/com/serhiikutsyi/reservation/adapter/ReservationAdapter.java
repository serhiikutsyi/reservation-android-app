package com.serhiikutsyi.reservation.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.serhiikutsyi.reservation.R;
import com.serhiikutsyi.reservation.model.Reservation;

import java.util.List;

/**
 * @author Serhii Kutsyi
 */
public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {

    private List<Reservation> reservations;

    public ReservationAdapter(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Reservation reservation = reservations.get(position);
        holder.reservationDeails.setText(reservation.getFirstName() + " " + reservation.getLastName() + ", room " + reservation.getRoomNumber() + ", from " + reservation.getStartDate() + " to " + reservation.getEndDate());
    }

    @Override
    public int getItemCount() {
        if (reservations == null)
            return 0;
        return reservations.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView reservationDeails;

        public ViewHolder(View itemView) {
            super(itemView);
            reservationDeails = (TextView) itemView.findViewById(R.id.reservation_item_details);
        }
    }
}