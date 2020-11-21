package id.ac.ui.cs.mobileprogramming.salsahava.journ.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity
public class Story {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String title;

    private Date date;

    private String narration;

    public Story(@NonNull Long id, String title, Date date, String narration) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.narration = narration;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public String getNarration() {
        return narration;
    }
}
