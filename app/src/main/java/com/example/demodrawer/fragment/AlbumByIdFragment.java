package com.example.demodrawer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumByIdFragment extends Fragment {

    TextInputEditText edtUid;
    MaterialButton btnGetInfo;
    private RecyclerView myRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.album_by_id_fragment, container, false);
        edtUid = view.findViewById(R.id.txtuid);
        btnGetInfo = view.findViewById(R.id.btninfo);
        myRecyclerView = view.findViewById(R.id.albums_by_id_recycler_view);

        btnGetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtUid.getText() != null){

                    if (!edtUid.getText().toString().isEmpty()){
                        int id = Integer.parseInt(String.valueOf(edtUid.getText()));
                        showUserById(id);
                    }else {
                        edtUid.setError("Please enter id");
                        edtUid.requestFocus();
                    }
                }

            }
        });
        return view;
    }

    private void showUserById(int id) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Albums> call = apiService.getAlbumById(id);
        call.enqueue(new Callback<Albums>() {
            @Override
            public void onResponse(Call<Albums> call, Response<Albums> response) {
                if (response.code() == 200){
                    if (response.body() != null){
                        Albums albums = response.body();
                        List<Albums> albumsList = new ArrayList<>();
                        albumsList.add(albums);

                        loadDataList(albumsList);
                    }
                }
            }

            @Override
            public void onFailure(Call<Albums> call, Throwable t) {
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
