package tz.co.neelansoft.watch;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by landre on 24/07/2018.
 */

public class TaskExecutors{
    private static final Object EXECUTOR_LOCK = new Object();
    private static TaskExecutors sExecutorInstance;
    private final Executor diskIO;
    private final Executor networkIO;
    private final Executor mainThread;

    //private constructor
    private TaskExecutors(Executor diskIO, Executor networkIO, Executor mainThread){
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }

    //get executor instance
    public static TaskExecutors getInstance(){
        if(sExecutorInstance == null){
            synchronized (EXECUTOR_LOCK){
                sExecutorInstance = new TaskExecutors(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3), new MainThreadExecutor());

            }
        }
        return sExecutorInstance;
    }

    public Executor diskIO(){
        return diskIO;
    }
    public Executor networkIO(){
        return networkIO;
    }
    public Executor mainThread(){
        return mainThread;
    }

    private static class MainThreadExecutor implements Executor{

        private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
