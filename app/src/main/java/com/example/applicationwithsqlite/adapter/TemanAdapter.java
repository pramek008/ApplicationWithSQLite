package com.example.applicationwithsqlite.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationwithsqlite.DetailTeman;
import com.example.applicationwithsqlite.EditTeman;
import com.example.applicationwithsqlite.MainActivity;
import com.example.applicationwithsqlite.R;
import com.example.applicationwithsqlite.database.DBController;
import com.example.applicationwithsqlite.database.Teman;

import java.util.ArrayList;
import java.util.HashMap;

import static android.graphics.Color.GREEN;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> {
    private ArrayList<Teman> listData;
    private Context c;
    public TemanAdapter(ArrayList<Teman> listData) {
        this.listData = listData;
    }

    @Override
    public TemanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        c = parent.getContext();
        LayoutInflater layoutInf = LayoutInflater.from(parent.getContext());
        View view = layoutInf.inflate(R.layout.row_data_teman,parent,false);
        return new TemanViewHolder(view);
    }

    public class TemanViewHolder extends RecyclerView.ViewHolder {
        private CardView cardKu;
        private TextView namaTxt,teleponTxt;
        public TemanViewHolder(@NonNull View view) {
            super(view);
            cardKu = view.findViewById(R.id.kartuKu);
            namaTxt = view.findViewById(R.id.textNama);
            teleponTxt = view.findViewById(R.id.textTelepon);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull TemanViewHolder holder, int position) {
        String nm, tlp, id;

        id = listData.get(position).getId();
        nm = listData.get(position).getNama();
        tlp = listData.get(position).getTelepon();
        DBController basisdata = new DBController(c);

        holder.namaTxt.setTextColor(Color.BLUE);
        holder.namaTxt.setTextSize(20);
        holder.namaTxt.setText(nm);

        holder.teleponTxt.setTextColor(GREEN);
        holder.teleponTxt.setText(tlp);

        holder.cardKu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c, DetailTeman.class);
                i.putExtra("id",id);
                i.putExtra("nama",nm);
                i.putExtra("telepon",tlp);
                c.startActivity(i);
            }
        });

        holder.cardKu.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(c,holder.cardKu);
                popupMenu.inflate(R.menu.pupup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.mnedit:
                                Intent i = new Intent(c, EditTeman.class);
                                i.putExtra("id",id);
                                i.putExtra("nama",nm);
                                i.putExtra("telepon",tlp);
                                c.startActivity(i);
                                break;
                            case R.id.mnhapus:
                                Intent o = new Intent(c, MainActivity.class);
                                HashMap<String,String> qValue = new HashMap<>();
                                qValue.put("nama",nm);
                                basisdata.deleteTeman(qValue);
                                c.startActivity(o);
                        }
                        return true;
                    }
                });
                popupMenu.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (listData != null)?listData.size() : 0;
    }


}
