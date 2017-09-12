package com.serhiikutsyi.reservation.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.serhiikutsyi.reservation.EditActivity;
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
        final Reservation reservation = reservations.get(position);
        holder.name.setText(reservation.getFirstName() + " " + reservation.getLastName());
        holder.room.setText("Room # " + reservation.getRoomNumber());
        holder.date.setText("from " + reservation.getStartDate() + " to " + reservation.getEndDate());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditActivity.class);
                intent.putExtra("reservation", reservation);
                intent.setAction(EditActivity.EDIT_RESERVATION);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (reservations == null)
            return 0;
        return reservations.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView room;
        private TextView date;
        private CardView cardView;
        private ImageButton editButton;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.reservation_item_name);
            room = itemView.findViewById(R.id.reservation_item_room);
            date = itemView.findViewById(R.id.reservation_item_date);
            cardView = itemView.findViewById(R.id.card_view);
            editButton = itemView.findViewById(R.id.action_edit);
        }
    }
}