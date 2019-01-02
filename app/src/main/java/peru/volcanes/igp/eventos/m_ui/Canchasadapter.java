//package com.mytrendin.firebaserecyclerviewcardview;
package peru.volcanes.igp.eventos.m_ui;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import peru.volcanes.igp.eventos.Elegirhoraa;
import peru.volcanes.igp.eventos.GetvalueActivityActivity;
import peru.volcanes.igp.eventos.Intropage;
import peru.volcanes.igp.eventos.R;
import peru.volcanes.igp.eventos.m_model.canchas;

public class Canchasadapter extends RecyclerView.Adapter<Canchasadapter.MyHoder>{

    List<canchas> list;
    Context context;

    public Canchasadapter(List<canchas> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_canchas,parent,false);
        // View view = LayoutInflater.from(context).inflate(R.,parent,false);
        MyHoder myHoder = new MyHoder(view);
        return myHoder;
    }

    @Override
    public void onBindViewHolder(MyHoder holder, int position) {
        final canchas mylist = list.get(position);
        holder.nombredecancha.setText("   " + mylist.getNombre());
        holder.canchadistrito.setText("   " + mylist.getDistrito());
        holder.ir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Elegirhoraa.class);
                intent.putExtra("LOCAL", mylist.getLocal());
                intent.putExtra("DISTRITO", mylist.getDistrito());
                intent.putExtra("NOMBRE", mylist.getNombre());
                context.startActivity(intent);
            }
        });
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
       // TextView name,email,address,ir;
        TextView nombredecancha,canchadistrito,ir;


        public MyHoder(View itemView) {
            super(itemView);
            nombredecancha= (TextView) itemView.findViewById(R.id.nombredecancha);
            canchadistrito= (TextView) itemView.findViewById(R.id.canchadistrito);
             ir = (Button) itemView.findViewById(R.id.ir);

        }
    }

}

/*
package peru.volcanes.volcanesper.m_ui;
public class RecyclerAdapter {
}
*/
