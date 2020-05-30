package com.demo.defineslabdemo.ui.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.defineslabdemo.R;
import com.demo.defineslabdemo.adapter.SavedMatchesAdapter;
import com.demo.defineslabdemo.db.SqliteDatabase;
import com.demo.defineslabdemo.db.StoredList;

import java.util.ArrayList;

public class SavedMatchesFragment extends Fragment {

    RecyclerView recycler_View;
    SqliteDatabase sqliteDatabase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_saved_matches, container, false);

        recycler_View=root.findViewById(R.id.recycler_View);
        recycler_View.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler_View.setHasFixedSize(true);

        loadData();
        return root;
    }

    void loadData(){
        sqliteDatabase = new SqliteDatabase(getContext());

        ArrayList<StoredList> storedLists = new ArrayList<>();

        Cursor cursor = sqliteDatabase.fetch();
        StoredList list;

        if (cursor.moveToFirst()) {
            do {

                list = new StoredList();

                list.setId(Integer.parseInt(cursor.getString(0)));
                list.setSid(cursor.getString(2));
                list.setName(cursor.getString(1));

                storedLists.add(list);
            }while (cursor.moveToNext());
        }

        SavedMatchesAdapter savedMatchesAdapter=new SavedMatchesAdapter(getContext(),storedLists);
        recycler_View.setAdapter(savedMatchesAdapter);
    }

}
