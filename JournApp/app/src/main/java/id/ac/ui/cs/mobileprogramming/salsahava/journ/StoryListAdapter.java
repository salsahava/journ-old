package id.ac.ui.cs.mobileprogramming.salsahava.journ;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.data.Story;

public class StoryListAdapter extends ListAdapter<Story, StoryViewHolder> {
    public StoryListAdapter(@NonNull DiffUtil.ItemCallback<Story> diffCallback) {
        super(diffCallback);
    }

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return StoryViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(StoryViewHolder holder, int position) {
        Story current = getItem(position);
        holder.bind(current.getTitle());
    }

    static class StoryDiff extends DiffUtil.ItemCallback<Story> {
        @Override
        public boolean areItemsTheSame(@NonNull Story oldItem, @NonNull Story newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Story oldItem, @NonNull Story newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    }
}
