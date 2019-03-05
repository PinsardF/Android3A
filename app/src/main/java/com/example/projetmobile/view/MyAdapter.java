//Utiliser Picasso pour gérer les images (qu'il faudra héberger, apr ex sur imgur)

package com.example.projetmobile.view;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetmobile.model.Meuble;
import com.example.projetmobile.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Meuble> meubleList;
    private final OnItemClickListener listener;
    private Context context;


    public interface OnItemClickListener {
        void onItemClick(Meuble item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView txtImage;

        public ViewHolder(View v) {
            super(v);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            txtImage = (ImageView) v.findViewById(R.id.icon);
        }
    }

    public void add(int position, Meuble newMeuble) {
        meubleList.add(position, newMeuble);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        meubleList.remove(position);
        notifyItemRemoved(position);
    }

    public MyAdapter(List<Meuble> myDataset, OnItemClickListener listener, Context context) {
        meubleList = myDataset;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Meuble meuble = meubleList.get(position);
        final String name = meubleList.get(position).getNom();
        final String desc = meubleList.get(position).getNature();
        final String url = meubleList.get(position).getImageurl();
        holder.txtHeader.setText(name);
        holder.txtFooter.setText("Nature : "+desc);
        //Picasso.with(context).load(url).into(holder.txtImage);//Icones à créer
        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(meuble);
            }
        });
    }

    @Override
    public int getItemCount() {
        return meubleList.size();
    }

}