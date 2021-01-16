package id.ac.ui.cs.mobileprogramming.salsahava.journ.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.util.Converters;

@Entity(tableName = "todo_table")
@TypeConverters(Converters.class)
public class ToDo {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String task;

    @ColumnInfo(name = "trip_date")
    private Date tripDate;

    public ToDo(String task, Date tripDate) {
        this.task = task;
        this.tripDate = tripDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public Date getTripDate() {
        return tripDate;
    }
}
