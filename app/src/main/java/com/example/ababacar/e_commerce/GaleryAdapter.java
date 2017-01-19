package com.example.ababacar.e_commerce;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ababacar.e_commerce.Model.Produit;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



/**
 * Created by Ababacar on 18/01/2017.
 */

public class GaleryAdapter extends RecyclerView.Adapter<GaleryAdapter.ViewHolder> {

    private ArrayList<Produit> Lproduit;
    private Context context;

    public GaleryAdapter(Context context, ArrayList<Produit> lproduit) {
        this.context = context;
        Lproduit = lproduit;
    }

    @Override
    public GaleryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.galery_row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GaleryAdapter.ViewHolder holder, int i) {
        holder.tvmarque.setText(Lproduit.get(i).getMarque());
        holder.tvcategorie.setText(Lproduit.get(i).getCategorie());
        holder.tvannee.setText(Lproduit.get(i).getAnnee());
        holder.tvprix.setText(String.valueOf(Lproduit.get(i).getPrix()));
        holder.tvdescription.setText(Lproduit.get(i).getDescription());
        //Picasso.with(context).load(Lproduit.get(i).getPhoto()).resize(120, 60).into(holder.imgproduit);
        holder.imgproduit.setImageBitmap(BitmapFactory.decodeFile(Lproduit.get(i).getPhoto()));
    }

    @Override
    public int getItemCount() {
        return Lproduit.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvmarque;
        private TextView tvcategorie;
        private TextView tvannee;
        private TextView tvprix;
        private TextView tvdescription;

        private ImageView imgproduit;
        public ViewHolder(View itemView) {
            super(itemView);
            tvmarque = (TextView)itemView.findViewById(R.id.marque);
            tvcategorie =(TextView)itemView.findViewById(R.id.categorie);
            tvannee = (TextView)itemView.findViewById(R.id.annee);
            tvprix = (TextView)itemView.findViewById(R.id.prix);
            tvdescription = (TextView)itemView.findViewById(R.id.description);
            imgproduit = (ImageView)itemView.findViewById(R.id.img);
        }
    }
}
