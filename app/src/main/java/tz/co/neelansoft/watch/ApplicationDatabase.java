package tz.co.neelansoft.watch;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

/**
 * Created by landre on 24/07/2018.
 */

@Database(entities = {Task.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)

public abstract class ApplicationDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "watch_task_db";
    private static final Object LOCK = new Object();
    private static ApplicationDatabase sInstance;

    public static ApplicationDatabase getDatabaseInstance(Context context) {
        if(sInstance == null){
            synchronized (LOCK){
                sInstance = Room.databaseBuilder(context.getApplicationContext(),ApplicationDatabase.class,ApplicationDatabase.DATABASE_NAME)
                        .build();
            }

        }
        return sInstance;
    }

    public abstract TaskDao getDao();
}
