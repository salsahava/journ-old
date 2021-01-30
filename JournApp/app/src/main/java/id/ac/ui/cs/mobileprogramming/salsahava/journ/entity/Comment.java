package id.ac.ui.cs.mobileprogramming.salsahava.journ.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.util.Converters;

@Entity(tableName = "comment_table")
@TypeConverters(Converters.class)
public class Comment {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String username;

    private String imageUrl;

    public Comment(String username, String imageUrl) {
        this.username = username;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
