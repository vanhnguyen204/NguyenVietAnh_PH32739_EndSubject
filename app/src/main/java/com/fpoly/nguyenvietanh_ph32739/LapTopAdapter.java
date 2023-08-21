package com.fpoly.nguyenvietanh_ph32739;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

public class LapTopAdapter extends RecyclerView.Adapter<LapTopAdapter.viewHolder> {
    Activity atv;
    ArrayList<LapTop> list;

    public LapTopAdapter(Activity atv, ArrayList list) {
        this.atv = atv;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_recyclerview, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        LapTop lapTop = list.get(position);
        holder.tvMa.setText("Mã: "+lapTop.getMaLapTop());
        LapTopDAO dao = new LapTopDAO(new DBHelper(atv), atv);
        holder.tvTen.setText("Tên: "+lapTop.getTenLapTop());
        holder.tvGia.setText("Giá: "+String.valueOf(lapTop.getGiaLapTop()));
        holder.tvKM.setText("Khuyến mãi: "+String.valueOf(lapTop.getKhuyenMai()) +" %");
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(atv);
                builder.setTitle("Xoá laptop");
                builder.setIcon(R.mipmap.warning);
                builder.setMessage("Bạn có muốn xoá laptop này không ?");

                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(position);
                        dao.xoaLapTop(lapTop);
                        notifyItemRemoved(position);
                        notifyItemRangeRemoved(position, list.size());
                        MediaPlayer mediaPlayer = MediaPlayer.create(atv, R.raw.chaching);
                        mediaPlayer.start();
                        setNotification();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView tvMa, tvTen, tvGia, tvKM;
        ImageView btnDelete;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvMa = itemView.findViewById(R.id.tv_MaLT);
            tvTen = itemView.findViewById(R.id.tv_tenLT);
            tvGia = itemView.findViewById(R.id.tv_gia);
            tvKM = itemView.findViewById(R.id.tv_khuyenmai);
            btnDelete = itemView.findViewById(R.id.img_bin);

        }
    }
    void setNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(atv, ConfigNotification.CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setContentTitle("Chào mừng đến với FFPT Polytechnic");
        builder.setContentText("Xoá thành công ");

        NotificationManagerCompat com = NotificationManagerCompat.from(atv);

        if (ActivityCompat.checkSelfPermission(atv, android.Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED
        ) {
            com.notify((int) new Date().getTime(), builder.build());
        } else {
            // xin quyen
            ActivityCompat.requestPermissions(atv,
                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
                    999); // 999  là mã tuỳ ý để nhận biết sự kiện người dùng đồng ý hay từ chối
        }

    }
}
