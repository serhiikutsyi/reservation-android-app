package com.serhiikutsyi.reservation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.serhiikutsyi.reservation.model.Reservation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Serhii Kutsyi
 */
public class EditActivity extends AppCompatActivity {

    public static final String NEW_RESERVATION = "new_reservation";
    public static final String EDIT_RESERVATION = "edit_reservation";

    private String TAG = "EditActivity";
    private Reservation mReservation;

    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private EditText mRoomEditText;
    private EditText mStartDateEditText;
    private EditText mEndDateEditText;
    private ImageButton mSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final Intent intent = getIntent();
        mReservation = (Reservation) intent.getSerializableExtra("reservation");

        mFirstNameEditText = (EditText) findViewById(R.id.edittext_firstname);
        mLastNameEditText = (EditText) findViewById(R.id.edittext_lastname);
        mRoomEditText = (EditText) findViewById(R.id.edittext_room);
        mStartDateEditText = (EditText) findViewById(R.id.edittext_start);
        mEndDateEditText = (EditText) findViewById(R.id.edittext_end);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (intent.getAction().equals(NEW_RESERVATION)) {
            toolbar.setTitle("Create");
        }
        if (intent.getAction().equals(EDIT_RESERVATION)) {
            toolbar.setTitle("Edit");
            Log.v(TAG, "Edit Reservation with ID " + mReservation.getId());
            mFirstNameEditText.setText(mReservation.getFirstName());
            mLastNameEditText.setText(mReservation.getLastName());
            mRoomEditText.setText("" + mReservation.getRoomNumber());
            mStartDateEditText.setText(mReservation.getStartDate());
            mEndDateEditText.setText(mReservation.getEndDate());
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSaveButton = (ImageButton) findViewById(R.id.action_save);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReservation.setFirstName(mFirstNameEditText.getText().toString());
                mReservation.setLastName(mLastNameEditText.getText().toString());
                mReservation.setRoomNumber(Integer.valueOf(mRoomEditText.getText().toString()));
                mReservation.setStartDate(mStartDateEditText.getText().toString());
                mReservation.setEndDate(mEndDateEditText.getText().toString());
                saveReservation(mReservation);
            }
        });

    }

    public void saveReservation(Reservation reservation) {
        App.getApi().createReservation(reservation).enqueue(new Callback<Reservation>() {
            @Override
            public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                Log.v(TAG, "Reservation is saved!");
                onBackPressed();
            }

            @Override
            public void onFailure(Call<Reservation> call, Throwable t) {
                Toast.makeText(EditActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteReservation(Long id) {
        App.getApi().deleteReservation(id).enqueue(new Callback<Reservation>() {
            @Override
            public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                Log.v(TAG, "Reservation is deleted!");
                onBackPressed();
            }

            @Override
            public void onFailure(Call<Reservation> call, Throwable t) {
                Toast.makeText(EditActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.edit_menu, menu);
        Intent intent = getIntent();
        if (intent.getAction().equals(NEW_RESERVATION)) {
            MenuItem item = menu.findItem(R.id.action_delete);
            item.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            deleteReservation(mReservation.getId());
        }
        return super.onOptionsItemSelected(item);
    }

}
