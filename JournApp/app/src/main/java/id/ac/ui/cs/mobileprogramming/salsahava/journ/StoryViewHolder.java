package id.ac.ui.cs.mobileprogramming.salsahava.journ;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class StoryViewHolder extends RecyclerView.ViewHolder {
    private final TextView storyItemView;

    private StoryViewHolder(@NonNull View itemView) {
        super(itemView);
        storyItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        storyItemView.setText(text);
    }

    static StoryViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.story_list_item, parent, false);
        return new StoryViewHolder(view);
    }
}
