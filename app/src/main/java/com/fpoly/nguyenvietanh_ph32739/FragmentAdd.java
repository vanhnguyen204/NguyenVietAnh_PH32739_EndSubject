package com.fpoly.nguyenvietanh_ph32739;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentAdd extends Fragment {
    View view;
    EditText edtMa, edtTen, edtGia, edtKhuyenMai;
    Button btnAddNew;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_me, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LapTopDAO dao1 = new LapTopDAO(new DBHelper(getActivity()), getActivity());
        edtMa = view.findViewById(R.id.edt_malt);
        edtTen = view.findViewById(R.id.edt_tenlt);
        edtGia = view.findViewById(R.id.edt_gia);
        edtKhuyenMai = view.findViewById(R.id.ed_khuyenMai);
        btnAddNew = view.findViewById(R.id.btnAddNew);
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getMa = edtMa.getText().toString().trim();
                String getTen = edtTen.getText().toString().trim();
                if (getMa.length() == 0) {
                    Toast.makeText(getActivity(), "Không được để trống mã", Toast.LENGTH_SHORT).show();
                } else if (getTen.length() == 0) {
                    Toast.makeText(getActivity(), "Không được để trống tên", Toast.LENGTH_SHORT).show();
                } else if (edtGia.getText().toString().trim().length() == 0) {
                    Toast.makeText(getActivity(), "Không được để trống giá", Toast.LENGTH_SHORT).show();
                } else if (edtKhuyenMai.getText().toString().trim().length() == 0) {
                    Toast.makeText(getActivity(), "Không được để trống khuyến mãi", Toast.LENGTH_SHORT).show();
                } else if (checkValue()) {

                }else {
                    int getGia = Integer.parseInt(edtGia.getText().toString().trim());
                    int getKhuyenMai = Integer.parseInt(edtKhuyenMai.getText().toString().trim());
                    LapTop lapTop = new LapTop(getMa, getTen, getGia, getKhuyenMai);
                    if (dao1.themLapTop(lapTop)){
                        Bundle bundle = new Bundle();
                        bundle.putString("ma", getMa);
                        bundle.putString("ten", getTen);
                        bundle.putInt("gia", getGia);
                        bundle.putInt("khuyenmai", getKhuyenMai);
                        getParentFragmentManager().setFragmentResult("key", bundle);

                    }
                }


            }
        });

        Button btnCancel = view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtKhuyenMai.setText("");
                edtGia.setText("");
                edtMa.setText("");
                edtTen.setText("");
            }
        });

    }


    boolean checkValue() {
        try {
            int checkGia = Integer.parseInt(edtGia.getText().toString().trim());
            if (checkGia <= 0) {
                Toast.makeText(getActivity(), "Giá phải lớn hơn 0 !", Toast.LENGTH_SHORT).show();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Giá phải là số !", Toast.LENGTH_SHORT).show();
            return true;
        }

        try {
            int checkKhuyenMai = Integer.parseInt(edtKhuyenMai.getText().toString());
            if (checkKhuyenMai < 1 || checkKhuyenMai > 100) {
                Toast.makeText(getActivity(), "Khuyến mại từ 1-100", Toast.LENGTH_SHORT).show();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Khuyến mại phải là số ", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}