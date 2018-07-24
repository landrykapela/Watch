package tz.co.neelansoft.watch;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by landre on 24/07/2018.
 */

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timeInMills){
        if(timeInMills != null){
            return new Date(timeInMills);
        }
        else{
            return null;
        }
    }

    @TypeConverter
    public static Long toTimestamp(Date date){
        return date.getTime();
    }
}
