package id.ac.ui.cs.mobileprogramming.salsahava.journ.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.R;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.adapter.RecyclerViewTripAdapter;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Trip;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.util.AlertReceiver;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.viewmodel.TripListViewModel;

public class TripListFragment extends Fragment {
    private static final String TAG = TripListFragment.class.getSimpleName();
    private static final String NAME_KEY = "name";
    private static final String DESC_KEY = "desc";
    private TripListViewModel tripListViewModel;
    private RecyclerView recyclerView;
    private RecyclerViewTripAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trip_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView();

        tripListViewModel = new ViewModelProvider(this).get(TripListViewModel.class);
        tripListViewModel.getAllTrips().observe(getViewLifecycleOwner(), new Observer<List<Trip>>() {
            @Override
            public void onChanged(List<Trip> trips) {
                if (trips != null && !trips.isEmpty()) {
                    adapter.setTrips(trips);
                    Calendar calendar = Calendar.getInstance();
                    Trip trip = trips.get(0);
                    calendar.setTime(trip.getStartDate());
                    startAlarm(trip.getName(), "Make sure you have everything you need for an amazing trip! :)", calendar);
                }
            }
        });
    }

    private void initRecyclerView() {
        adapter = new RecyclerViewTripAdapter();
        recyclerView = getView().findViewById(R.id.recyclerTrip);
        RecyclerView.LayoutManager linearLayout = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(adapter);
    }

    private void startAlarm(String name, String desc, Calendar calendar) {
        AlarmManager alarmManager = (AlarmManager) getActivity()
                .getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), AlertReceiver.class);
        intent.putExtra(NAME_KEY, name);
        intent.putExtra(DESC_KEY, desc);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 1, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

    }

}