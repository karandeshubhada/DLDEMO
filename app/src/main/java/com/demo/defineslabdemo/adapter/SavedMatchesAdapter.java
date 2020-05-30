package com.demo.defineslabdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.defineslabdemo.Model.ResponseData;
import com.demo.defineslabdemo.R;
import com.demo.defineslabdemo.db.SqliteDatabase;
import com.demo.defineslabdemo.db.StoredList;

import java.util.ArrayList;
public class SavedMatchesAdapter  extends RecyclerView.Adapter<SavedMatchesAdapter.MyViewHolder> {

    private Context context;
    private SqliteDatabase mDatabase;

    ArrayList<StoredList> storedLists;

    public SavedMatchesAdapter(Context context, ArrayList<StoredList> storedLists) {
        this.context = context;
        this.storedLists = storedLists;
        mDatabase=new SqliteDatabase(context);
    }

    @NonNull
    @Override
    public SavedMatchesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.saved_matches_layout, parent, false);
        SavedMatchesAdapter.MyViewHolder viewHolder = new SavedMatchesAdapter.MyViewHolder(view); // pass the view to View Holder
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SavedMatchesAdapter.MyViewHolder holder, final int position) {

        final StoredList data=storedLists.get(position);
        holder.id.setText(data.getSid());
        holder.name.setText(data.getName());
    }

    @Override
    public int getItemCount() {
        return storedLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id,name,delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.tv_id);
            name=itemView.findViewById(R.id.tv_name);
        }
    }
}
