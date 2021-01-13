package id.ac.ui.cs.mobileprogramming.salsahava.journ.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.dao.StoryDao;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.dao.ToDoDao;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.dao.TripDao;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Story;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.ToDo;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Trip;

@Database(entities = {Trip.class, Story.class, ToDo.class}, version = 1, exportSchema = false)
public abstract class JournDatabase extends RoomDatabase {
    private static JournDatabase instance;
    private static String DATABASE_NAME = "journ";

    public abstract TripDao getTripDao();
    public abstract StoryDao getStoryDao();
    public abstract ToDoDao getToDoDao();

    public static synchronized JournDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    JournDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private TripDao tripDao;
        private StoryDao storyDao;
        private ToDoDao toDoDao;

        private PopulateDbAsyncTask(JournDatabase journDatabase) {
            tripDao = journDatabase.getTripDao();
            storyDao = journDatabase.getStoryDao();
            toDoDao = journDatabase.getToDoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date date1 = new Date();
            Date date2 = new Date();
            Date date3 = new Date();
            Date date4 = new Date();

            try {
                date1 = sdf.parse("08-01-2021");
                date2 = sdf.parse("10-01-2021");
                date3 = sdf.parse("15-01-2021");
                date4 = sdf.parse("23-02-2021");
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Trip trip1 = new Trip("Paris 2021", date1, date2);
            Trip trip2 = new Trip("Madrid 2021", date2, date4);

            tripDao.insert(trip1);
            tripDao.insert(trip2);

            Story story1 = new Story("Day 1 - Louvre Museum", date1, "Nice nice");
            Story story2 = new Story("Visiting Gran Via", date3, "Cool cool");

            storyDao.insert(story1);
            storyDao.insert(story2);

            ToDo toDo1 = new ToDo("Buy a coat for winter", date3);
            ToDo toDo2 = new ToDo("Book a hotel", date4);

            toDoDao.insert(toDo1);
            toDoDao.insert(toDo2);

            return null;
        }
    }
}
