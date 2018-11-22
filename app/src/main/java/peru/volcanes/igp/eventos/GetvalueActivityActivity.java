package peru.volcanes.igp.eventos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import peru.volcanes.igp.eventos.m_model.reservas;
import peru.volcanes.igp.eventos.m_ui.RecyclerAdapter;

/**
 * Created by usuario on 21/11/2018.
 */

public class GetvalueActivityActivity extends AppCompatActivity{

    ListView reservas;

    private final String TAG = "getvale";
    private CalendarView nCalendarView;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase database;
    ArrayList<reservas> objetoerupciones = new ArrayList<reservas>();

    FirebaseDatabase databaseerupciones;
    DatabaseReference myRef ;
    List<reservas> list;
    RecyclerView recycle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nCalendarView =   findViewById(R.id.calendario);

        nCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int i, int i1, int i2) {
                String date = i + "/" + i1 + "" + i2 + "";
                Log.d(TAG, "SADSAD" + date);
                consulta(date);
            }
        });

        recycle = (RecyclerView) findViewById(R.id.recycle);
        databaseerupciones = FirebaseDatabase.getInstance();


        recycle = (RecyclerView) findViewById(R.id.recycle);
        databaseerupciones = FirebaseDatabase.getInstance();

/*
        myRef = databaseerupciones.getReference("usuarios").child("reporteactividad");
       myRef.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
           @Override
           public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
               // This method is called once with the initial value and again
               // whenever data at this location is updated.
               list = new ArrayList<reservas>();
               for (com.google.firebase.database.DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                   reservas value = dataSnapshot1.getValue(reservas.class);
                   reservas fire = new reservas();

                   String name = value.getFecha();
                   String address = value.getHora();
                   String email = value.getHora();
                   fire.setFecha(name);
                   fire.setHora(email);
                   fire.setHora(address);
                   list.add(fire);


                   RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list, GetvalueActivityActivity.this);
                   RecyclerView.LayoutManager recyce = new GridLayoutManager(GetvalueActivityActivity.this, 1);
                   /// RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainActivity.this);
                   // recycle.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
                   recycle.setLayoutManager(recyce);
                   recycle.setItemAnimator(new DefaultItemAnimator());
                   recycle.setAdapter(recyclerAdapter);

               }
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
       */
    }

    public void consulta(String urlString) {

        myRef = databaseerupciones.getReference("usuarios").child("reporteactividad");
        myRef.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                list = new ArrayList<reservas>();
                for (com.google.firebase.database.DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    reservas value = dataSnapshot1.getValue(reservas.class);
                    reservas fire = new reservas();

                    String name = value.getFecha();
                    String address = value.getHora();
                    String email = value.getHora();
                    fire.setFecha(name);
                    fire.setHora(email);
                    fire.setHora(address);
                    list.add(fire);


                    RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list, GetvalueActivityActivity.this);
                    RecyclerView.LayoutManager recyce = new GridLayoutManager(GetvalueActivityActivity.this, 1);
                    /// RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainActivity.this);
                    // recycle.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
                    recycle.setLayoutManager(recyce);
                    recycle.setItemAnimator(new DefaultItemAnimator());
                    recycle.setAdapter(recyclerAdapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       Toast.makeText(getApplicationContext(), urlString, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(GetvalueActivityActivity.this, Elegirhoraa.class);
        startActivity(intent);

    }


}
