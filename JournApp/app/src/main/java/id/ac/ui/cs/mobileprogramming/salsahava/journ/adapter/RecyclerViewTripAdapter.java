package id.ac.ui.cs.mobileprogramming.salsahava.journ.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.R;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Trip;

public class RecyclerViewTripAdapter extends RecyclerView.Adapter<RecyclerViewTripAdapter.ViewHolder> {
    private static final String TAG = RecyclerViewTripAdapter.class.getSimpleName();
    private Context context;
    private List<Trip> trips = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_trip_item, parent, false);
        context = parent.getContext();
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String datePattern = "EEEE, dd MMMM yyyy";
        SimpleDateFormat sdfDate = new SimpleDateFormat(datePattern);

        holder.tripName.setText(trips.get(position).getName());
        holder.tripStartDate.setText(sdfDate.format(trips.get(position).getStartDate()));
        holder.tripEndDate.setText(sdfDate.format(trips.get(position).getEndDate()));
    }

    public void setTrips(List<Trip> tripList) {
        this.trips = tripList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tripName;
        TextView tripStartDate;
        TextView tripEndDate;
        ConstraintLayout tripListLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tripName = itemView.findViewById(R.id.tripName);
            tripStartDate = itemView.findViewById(R.id.tripStartDate);
            tripEndDate = itemView.findViewById(R.id.tripEndDate);
            tripListLayout = itemView.findViewById(R.id.tripListLayout);
        }
    }
}
