//package com.mytrendin.firebaserecyclerviewcardview;
package peru.volcanes.igp.eventos.m_ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import peru.volcanes.igp.eventos.R;
import peru.volcanes.igp.eventos.m_model.reservas;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHoder>{

    List<reservas> list;
    Context context;

    public RecyclerAdapter(List<reservas> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.carderupcion,parent,false);

       // View view = LayoutInflater.from(context).inflate(R.,parent,false);
        MyHoder myHoder = new MyHoder(view);


        return myHoder;
    }

    @Override
    public void onBindViewHolder(MyHoder holder, int position) {
        reservas mylist = list.get(position);
        holder.name.setText(mylist.getFecha());
        holder.email.setText(mylist.getFecha());
        holder.address.setText("Fuente: " + mylist.getHora());
    }

    @Override
    public int getItemCount() {

        int arr = 0;

        try{
            if(list.size()==0){

                arr = 0;

            }
            else{

                arr=list.size();
            }



        }catch (Exception e){



        }

        return arr;

    }

    class MyHoder extends RecyclerView.ViewHolder{
        TextView name,email,address;


        public MyHoder(View itemView) {
            super(itemView);
            email= (TextView) itemView.findViewById(R.id.vemail);
            address= (TextView) itemView.findViewById(R.id.vaddress);
            name = (TextView) itemView.findViewById(R.id.vname);

        }
    }

}

/*
package peru.volcanes.volcanesper.m_ui;
public class RecyclerAdapter {
}
*/