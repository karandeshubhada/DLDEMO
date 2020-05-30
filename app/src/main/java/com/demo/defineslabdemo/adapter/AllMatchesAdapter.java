package com.demo.defineslabdemo.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.defineslabdemo.Model.ResponseData;
import com.demo.defineslabdemo.R;
import com.demo.defineslabdemo.db.SqliteDatabase;
import com.demo.defineslabdemo.db.StoredList;

import java.util.ArrayList;

public class AllMatchesAdapter  extends RecyclerView.Adapter<AllMatchesAdapter.MyViewHolder> {

    private Context context;
    private SqliteDatabase mDatabase;

    ArrayList<ResponseData> responseData;
    ArrayList<String> idList=new ArrayList<>();
    ArrayList<StoredList> storedLists = new ArrayList<>();

    public AllMatchesAdapter(Context context, ArrayList<ResponseData> responseData) {
        this.context = context;
        this.responseData = responseData;
        mDatabase=new SqliteDatabase(context);
    }

    @NonNull
    @Override
    public AllMatchesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        AllMatchesAdapter.MyViewHolder viewHolder = new AllMatchesAdapter.MyViewHolder(view); // pass the view to View Holder

        mDatabase = new SqliteDatabase(context);
        Cursor cursor = mDatabase.fetch();
        StoredList list;

        if (cursor.moveToFirst()) {
            do {
                list = new StoredList();
                list.setSid(cursor.getString(2));
                storedLists.add(list);
            }while (cursor.moveToNext());
        }

        for(int i=0;i<storedLists.size();i++){
            idList.add(storedLists.get(i).getSid());
        }


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AllMatchesAdapter.MyViewHolder holder, int position) {

        final ResponseData data=responseData.get(position);

        holder.id.setText(data.getId());
        holder.name.setText(data.getName());
        holder.tv_phone.setText(data.getPhone());
        holder.tv_location.setText(data.getAddress());

            if(idList.contains(data.getId())){
                holder.status.setImageResource(R.drawable.ic_action_active);

            } else {

                holder.status.setImageResource(R.drawable.ic_action_inactive);
            }

        holder.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoredList storedList=new StoredList(data.getId(),data.getName());

               boolean status= mDatabase.isExist(data.getName());
               if(status){
                   mDatabase.delete(data.getId());
               //    holder.status.setText("status");
                   holder.status.setImageResource(R.drawable.ic_action_inactive);


               }else {
                   mDatabase.add(storedList);
                //   holder.status.setText("Add");
                   holder.status.setImageResource(R.drawable.ic_action_active);

               }


            }
        });


    }

    @Override
    public int getItemCount() {
        return responseData.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id,name,tv_phone,tv_location;
        ImageView status;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.tv_id);
            name=itemView.findViewById(R.id.tv_name);
            tv_phone=itemView.findViewById(R.id.tv_phone);
            tv_location=itemView.findViewById(R.id.tv_location);
            status=itemView.findViewById(R.id.status);
        }
    }
}
