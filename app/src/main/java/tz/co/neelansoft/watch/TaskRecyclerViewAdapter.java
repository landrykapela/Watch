package tz.co.neelansoft.watch;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by landre on 24/07/2018.
 */

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.ViewHolder> {
    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
    private Context mContext;
    private List<Task> mTaskList = new ArrayList<>();
    private final ItemClickListener mItemClickListener;

    public interface ItemClickListener{
        void onItemClickListener(int itemId);
    }
    public TaskRecyclerViewAdapter (Context ctx, ItemClickListener listener){
        this.mContext = ctx;
        this.mItemClickListener = listener;
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    @Override
    public int getItemCount(){
        if(mTaskList != null){
            return mTaskList.size();
        }
        else return 0;
    }
    public void setTasks(List<Task> tasks){
        this.mTaskList = tasks;
    }

    @Override
    public TaskRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.task_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position){
        vh.bind(position);

    }
    public Task getItem(int position){
        return mTaskList.get(position);
    }

    public List<Task> getAllTasks(){
        return mTaskList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvTitle;
        TextView tvStatus;
        TextView tvStartTime;

        public ViewHolder(View itemView){
            super(itemView);

            tvStartTime = itemView.findViewById(R.id.tvStartTime);
            tvStatus    = itemView.findViewById(R.id.tvStatus);
            tvTitle     = itemView.findViewById(R.id.tvTitle);
        }

        public void bind(int position){
            Task task = mTaskList.get(position);
            String status = "Unknown";
            if(task.getEndedAd().equals(task.getStartedAt())){
                status = "Incomplete";
            }
            else{
                status = "Complete";
            }

            tvStatus.setText(status);
            tvTitle.setText(task.getTitle());
            tvStartTime.setText(simpleDateFormat.format(task.getStartedAt()));
        }

        @Override
        public void onClick(View view){
            int itemId = mTaskList.get(getAdapterPosition()).getId();
            mItemClickListener.onItemClickListener(itemId);
        }
    }

}
