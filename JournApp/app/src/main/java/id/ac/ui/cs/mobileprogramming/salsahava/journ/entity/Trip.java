package id.ac.ui.cs.mobileprogramming.salsahava.journ.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.util.Converters;

@Entity(tableName = "trip_table")
@TypeConverters(Converters.class)
public class Trip {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    @ColumnInfo(name = "start_date")
    private Date startDate;

    @ColumnInfo(name = "end_date")
    private Date endDate;

    @ColumnInfo(name = "story_ids")
    private List<Integer> storyIds;

    public Trip(String name, Date startDate, Date endDate, List<Integer> storyIds) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.storyIds = storyIds;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStoryIds(List<Integer> storyIds) {
        this.storyIds = storyIds;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public List<Integer> getStoryIds() {
        return storyIds;
    }
}
