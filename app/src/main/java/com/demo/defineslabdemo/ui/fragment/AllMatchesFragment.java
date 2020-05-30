package com.demo.defineslabdemo.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.defineslabdemo.InternetConnection.Alerts;
import com.demo.defineslabdemo.InternetConnection.InternetConnection;
import com.demo.defineslabdemo.Model.ResponseData;
import com.demo.defineslabdemo.R;
import com.demo.defineslabdemo.adapter.AllMatchesAdapter;
import com.demo.defineslabdemo.api.ApiClient;
import com.demo.defineslabdemo.api.ApiInterface;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllMatchesFragment extends Fragment {

   private ApiInterface apiInterface;
   private ArrayList<ResponseData> responseData = new ArrayList<>();
   private AllMatchesAdapter allMatchesAdapter;
   private RecyclerView recycler_View;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_all_matches, container, false);

        recycler_View=root.findViewById(R.id.recycler_View);
        recycler_View.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler_View.setHasFixedSize(true);

        getAllMatchesList();

        return root;
    }

    private void getAllMatchesList() {
        if (InternetConnection.checkConnection(getContext())) {
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setCancelable(false); // set cancelable to false
            progressDialog.setMessage("Please Wait..."); // set message
            progressDialog.show();

            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<JsonElement> call = apiInterface.search();

            call.enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    progressDialog.dismiss();
                    String jsonResponse = String.valueOf(response.body());
                    try {
                        JSONObject object = new JSONObject(jsonResponse);
                        JSONObject object1 = object.getJSONObject("response");

                        JSONArray venues = object1.getJSONArray("venues");

                        for (int i = 0; i < venues.length(); i++){
                            ResponseData data=new ResponseData();
                            JSONObject jsonObjectVenues = venues.getJSONObject(i);
                            data.setId(jsonObjectVenues.getString("id"));
                            data.setName(jsonObjectVenues.getString("name"));


                            JSONObject contact = jsonObjectVenues.getJSONObject("contact");
                            if(contact.has("phone")) {
                                data.setPhone(contact.getString("phone"));

                            } JSONObject location = jsonObjectVenues.getJSONObject("location");
                            if(location.has("address")) {
                                data.setAddress(location.getString("address"));

                            }
                            responseData.add(data);
                        }
                        allMatchesAdapter=new AllMatchesAdapter(getContext(),responseData);
                        recycler_View.setAdapter(allMatchesAdapter);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        } else{
                Alerts.showAlert(getContext());
            }
        }
    }
