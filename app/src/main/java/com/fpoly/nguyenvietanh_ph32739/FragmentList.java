package com.fpoly.nguyenvietanh_ph32739;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentList extends Fragment {
    View view;
    ArrayList<LapTop> list;
    LapTopDAO dao;

    LapTopAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dao = new LapTopDAO(new DBHelper(getActivity()), getActivity());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        LapTop lapTop = new LapTop("MLT01", "MacBook Pro", 300000, 0);
        LapTop lapTop1 = new LapTop("MLT02", "MAc Air", 900000, 10);
        LapTop lapTop2 = new LapTop("MLT03", "MAc", 500000, 20);
        dao.themLapTop(lapTop);
        dao.themLapTop(lapTop1);
        dao.themLapTop(lapTop2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = dao.getList();
        adapter = new LapTopAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);

        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String getMa = result.getString("ma");
                String getTen = result.getString("ten");
                int getGia = result.getInt("gia");
                int getKM = result.getInt("khuyenmai");
                LapTop lapTop = new LapTop(getMa, getTen, getGia, getKM);
                list.add(lapTop);
                adapter.notifyItemInserted(list.size());
            }
        });


    }


}