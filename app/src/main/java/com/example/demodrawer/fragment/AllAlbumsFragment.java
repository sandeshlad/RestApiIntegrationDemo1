package com.example.demodrawer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demodrawer.R;
import com.example.demodrawer.adapter.AlbumsAdapter;
import com.example.demodrawer.model.Albums;
import com.example.demodrawer.rest.ApiClient;
import com.example.demodrawer.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllAlbumsFragment extends Fragment {

    RecyclerView myRecyclerView;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_albums_fragment,container,false);
        myRecyclerView = view.findViewById(R.id.albums_recycler_view);
        progressBar = view.findViewById(R.id.progressbar);

        progressBar.setVisibility(View.VISIBLE);

        showAllAlbums();
        return view;
    }



    private void showAllAlbums() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Albums>> call = apiService.getAllAlbums();
        call.enqueue(new Callback<List<Albums>>() {
            @Override
            public void onResponse(Call<List<Albums>> call, Response<List<Albums>> response) {
                    if (response.code() == 200) {

                        if (response.body() != null) {

                            loadDataList(response.body());

                            if (progressBar.getVisibility() == View.VISIBLE) {
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    }

            }

            @Override
            public void onFailure(Call<List<Albums>> call, Throwable t) {
                if (progressBar.getVisibility() == View.VISIBLE){
                    progressBar.setVisibility(View.INVISIBLE);
                }
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void loadDataList(List<Albums> albums) {

        AlbumsAdapter myAdapter = new AlbumsAdapter(getActivity(), albums);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        myRecyclerView.setLayoutManager(mLayoutManager);
        myRecyclerView.setAdapter(myAdapter);

    }

}
