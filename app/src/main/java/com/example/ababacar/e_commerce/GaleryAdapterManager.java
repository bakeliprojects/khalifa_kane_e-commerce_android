package com.example.ababacar.e_commerce;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ababacar.e_commerce.Model.Produit;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Ababacar on 18/01/2017.
 */

public class GaleryAdapterManager extends RecyclerView.Adapter<GaleryAdapterManager.ViewHolder>  {

    private static final String TAG = "GaleryAdapterManager";
    //SwipeRefreshLayout  swipeRefreshLayout;
    private Realm realm;
    private ArrayList<Produit> Lproduit;
    private Context context;

    public GaleryAdapterManager(ArrayList<Produit> lproduit, Context context) {
        Lproduit = lproduit;
        this.context = context;
        realm = Realm.getDefaultInstance();
    }

    @Override
    public GaleryAdapterManager.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.galery_row_layout_manager, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GaleryAdapterManager.ViewHolder holder, int i) {
        holder.tvmarque.setText(Lproduit.get(i).getMarque());
        holder.tvcategorie.setText(Lproduit.get(i).getCategorie());
        holder.tvannee.setText(Lproduit.get(i).getAnnee());
        holder.tvprix.setText(String.valueOf(Lproduit.get(i).getPrix()));
        holder.tvdescription.setText(Lproduit.get(i).getDescription());
        holder.imgproduit.setImageBitmap(BitmapFactory.decodeFile(Lproduit.get(i).getPhoto()));
    }

    @Override
    public int getItemCount() {
        return Lproduit.size();
    }
        {

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvmarque;
        private TextView tvcategorie;
        private TextView tvannee;
        private TextView tvprix;
        private TextView tvdescription;
        private ImageView imgproduit;
        private Button bte;
        private  Button btd;
        public ViewHolder(View itemView) {
            super(itemView);
            tvmarque = (TextView)itemView.findViewById(R.id.marque);
            tvcategorie =(TextView)itemView.findViewById(R.id.categorie);
            tvannee = (TextView)itemView.findViewById(R.id.annee);
            tvprix = (TextView)itemView.findViewById(R.id.prix);
            tvdescription = (TextView)itemView.findViewById(R.id.description);
            imgproduit = (ImageView)itemView.findViewById(R.id.img);
            bte = (Button) itemView.findViewById(R.id.btneditproduit);
            btd = (Button) itemView.findViewById(R.id.btndelproduit);

            bte.setOnClickListener(this);
            btd.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Produit p = Lproduit.get(getAdapterPosition());

           if(bte.isPressed())
           {

                Intent intent = new Intent(context.getApplicationContext(),UpdateProduct.class);
                intent.putExtra("extra",p.getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
           }
            else if (btd.isPressed())
           {

               final RealmResults<Produit> produit = realm.where(Produit.class).equalTo("id",p.getId()).findAll();
               realm.executeTransaction(new Realm.Transaction() {
                   @Override
                   public void execute(Realm realm) {
                       produit.deleteFromRealm(0);
                   }
               });

           }
        }

    }


}
