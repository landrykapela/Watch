package tz.co.neelansoft.watch;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by landre on 24/07/2018.
 */
@Entity(tableName = "task")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String description;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    private int duration; //duration in minutes

    @ColumnInfo (name = "started_at")
    private Date startedAt;

    @ColumnInfo (name = "ended_at")
    private Date endedAd;

    @Ignore
    public Task(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getEndedAd() {
        return endedAd;
    }

    public void setEndedAd(Date endedAd) {
        this.endedAd = endedAd;
    }

    @Ignore
    public Task(String title, String description,int duration){

        this.title = title;
        this.description = description;
        this.duration = duration;
        this.startedAt = new Date();
        this.endedAd = new Date();
    }

    public Task(int id, String title, String description,int duration) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.startedAt = new Date();
        this.endedAd = new Date();
    }
}
