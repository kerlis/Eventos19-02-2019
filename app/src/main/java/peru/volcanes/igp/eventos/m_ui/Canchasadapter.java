//package com.mytrendin.firebaserecyclerviewcardview;
package peru.volcanes.igp.eventos.m_ui;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;
import peru.volcanes.igp.eventos.Elegirhoraa;
import peru.volcanes.igp.eventos.GetvalueActivityActivity;
import peru.volcanes.igp.eventos.Intropage;
import peru.volcanes.igp.eventos.R;
import peru.volcanes.igp.eventos.m_model.canchas;

import static android.widget.Toast.LENGTH_LONG;

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
        holder.nombredecancha.setText("   " + mylist.getServicioshigienicos());
        holder.canchadistrito.setText("   " + mylist.getDistrito());



      //  if(mylist.getFoto() != null && !mylist.getFoto().isEmpty()) {

            Picasso.with(context).load(mylist.getFoto())
                    .error(R.mipmap.ic_launcher)
                    .into(holder.foto);

        //   .into(holder.foto, new com.squareup.picasso.Callback(){

                   //     @Override
                     //   public void onSuccess() {

                     //   }

                 //       @Override
                //        public void onError() {

             //           }
           //         });
       // }



        holder.detalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                verpopup(mylist.getLocalid(),mylist.getDistrito(),mylist.getCanchaid());
            }
        });



        holder.ir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Elegirhoraa.class);
                intent.putExtra("LOCAL", mylist.getLocal());
                intent.putExtra("DISTRITO", mylist.getDistrito());
                intent.putExtra("NOMBRE", mylist.getNombre());
                intent.putExtra("CANCHAID", mylist.getCanchaid());
                intent.putExtra("LOCALID", mylist.getLocalid());

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
        TextView nombredecancha,canchadistrito,ir,detalles;

        ImageView foto;

        public MyHoder(View itemView) {
            super(itemView);
            nombredecancha= (TextView) itemView.findViewById(R.id.nombredecancha);
            canchadistrito= (TextView) itemView.findViewById(R.id.canchadistrito);
             ir = (Button) itemView.findViewById(R.id.ir);
            detalles = (Button) itemView.findViewById(R.id.detalles);

            foto = (ImageView) itemView.findViewById(R.id.imagen);

        }
    }



    public void loadimage(String url) {
       /* Picasso.with(context).load("https://static01.diariodenavarra.es/uploads/imagenes/8col/2016/12/18/_amaya_3176df96.jpg?8ea6fc0f2a298b1ae9b4835f01e49143")
                .resize(120, 100)
                .onlyScaleDown()
                .into(foto);*/
    }


    public void verpopup(String local, String distrito, String nombre) {
        View view;
        LayoutInflater inflater = (LayoutInflater)   context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.datoscancha, null);
        AlertDialog.Builder dialog  = new AlertDialog.Builder(context);
        dialog.setView(view);
        dialog.show();

        TextView  distrito_textview = view.findViewById(R.id.distrito);
        TextView  local_textview = view.findViewById(R.id.local);
        TextView  nombre_textview = view.findViewById(R.id.nombre);

        distrito_textview.setText(distrito);
        local_textview.setText(local);
        nombre_textview.setText(nombre);
    }

}