package com.example.applicationwithsqlite.adapter;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationwithsqlite.R;
import com.example.applicationwithsqlite.database.Teman;

import java.util.ArrayList;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> {
    private ArrayList<Teman> listData;

    public TemanAdapter(ArrayList<Teman> listData) {
        this.listData = listData;
    }

    @Override
    public TemanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInf = LayoutInflater.from(parent.getContext());
        View view = layoutInf.inflate(R.layout.row_data_teman,parent,false);
        return new TemanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TemanViewHolder holder, int position) {
        String nm, tlp;

        nm = listData.get(position).getNama();
        tlp = listData.get(position).getTelepon();

        holder.namaTxt.setTextColor(Color.BLUE);
        holder.namaTxt.setTextSize(20);
        holder.namaTxt.setText(nm);

        holder.teleponTxt.setTextColor(Color.MAGENTA);
        holder.teleponTxt.setTextSize(15);
        holder.teleponTxt.setText(tlp);
    }

    @Override
    public int getItemCount() {
        return (listData != null)?listData.size() : 0;
    }

    public class TemanViewHolder extends RecyclerView.ViewHolder {
        private CardView cardKu;
        private TextView namaTxt,teleponTxt;
        public TemanViewHolder(@NonNull View view) {
            super(view);
            cardKu = (CardView) view.findViewById(R.id.kartuKu);
            namaTxt = (TextView) view.findViewById(R.id.textNama);
            teleponTxt = (TextView) view.findViewById(R.id.textTelepon);
        }
    }
}
