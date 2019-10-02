package com.cory.dbtest;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

class GridHolder extends RecyclerView.ViewHolder{
    private TextView mTextView;

    public GridHolder(@NonNull View itemView) {
        super(itemView);
        mTextView=itemView.findViewById(R.id.text);
    }

    public void bind(String s){
        mTextView.setText(s);
    }

    public void bindWithColumnColor(String s,int color){
        bind(s);
        itemView.setBackgroundColor(color);
    }
}


public class GridAdapter extends RecyclerView.Adapter<GridHolder> {
    private Context mContext;
    public LinkedList<String> mList=new LinkedList<>();
    private int mColumnColorA;
    private int mColumnColorB;
    private int mColumnColorC;
    private int mColumnColorD;
    private int mColumnCount;

    public GridAdapter(Context context, int columnCount) {
        mContext = context;
        mColumnCount = columnCount;
        Resources res=mContext.getResources();
        mColumnColorA=res.getColor(R.color.shallowBlue);
        mColumnColorB=res.getColor(R.color.shallowGray);
        mColumnColorC=res.getColor(R.color.shallowBlueLight);
        mColumnColorD=Color.WHITE;
    }

    @NonNull
    @Override
    public GridHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        return new GridHolder(inflater.inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull GridHolder holder, int position) {
        int remainder=position%(mColumnCount*2);
        if(remainder<mColumnCount){
            if (remainder%2==0) {
                holder.bindWithColumnColor(mList.get(position), mColumnColorA);
            }else{
                holder.bindWithColumnColor(mList.get(position), mColumnColorB);
            }
        }else {
            if(remainder%2==0){
                holder.bindWithColumnColor(mList.get(position), mColumnColorC);
            }else{
                holder.bindWithColumnColor(mList.get(position), mColumnColorD);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
