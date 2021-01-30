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
import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Story;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.util.Communicator;

public class RecyclerViewStoryAdapter extends RecyclerView.Adapter<RecyclerViewStoryAdapter.ViewHolder> {
    private static final String TAG = RecyclerViewStoryAdapter.class.getSimpleName();
    private Context context;
    private List<Story> stories = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
        context = parent.getContext();
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String datePattern = "EEEE, dd MMMM yyyy";
        SimpleDateFormat sdfDate = new SimpleDateFormat(datePattern);

        holder.storyTitle.setText(stories.get(position).getTitle());
        holder.storyDate.setText(sdfDate.format(stories.get(position).getDate()));
        holder.parentLayout.setOnClickListener(v -> {
            Communicator communicator = (Communicator) context;
            communicator.displayDetails(stories.get(position));
        });
    }

    public void setStories(List<Story> storyList) {
        this.stories = storyList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView storyTitle;
        TextView storyDate;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            storyTitle = itemView.findViewById(R.id.storyTitleTextView);
            storyDate = itemView.findViewById(R.id.storyDateTextView);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
}
