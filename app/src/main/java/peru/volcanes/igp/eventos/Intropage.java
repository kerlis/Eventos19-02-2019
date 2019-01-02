package peru.volcanes.igp.eventos;
import android.content.Intent;
import android.support.design.internal.BottomNavigationItemView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

import peru.volcanes.igp.eventos.m_model.reservas;
import peru.volcanes.igp.eventos.m_ui.RecyclerAdapter;

import static android.widget.Toast.LENGTH_LONG;

public class Intropage extends AppCompatActivity {

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

    Spinner spinner1;
    Spinner spinner2;


    String valo = "1";
    String valo2 = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intropage);

        recycle = (RecyclerView) findViewById(R.id.recycle);
        databaseerupciones = FirebaseDatabase.getInstance();

        spinner1 = (Spinner) findViewById(R.id.spinner);

        spinner2 = (Spinner) findViewById(R.id.spinner2);



        //  BottomNavigationItemView bottomNavigationItemView = (BottomNavigationItemView)findViewById(R.id.menuinferior);
        //  bottomNavigationItemView.setO


        combo1();

        spinner1.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                       valo = spinner1.getSelectedItem().toString();
                        datos_elegidos();
                        combo2(valo);

                        consulta(valo, valo2);
                        //Toast.makeText(this, valo, LENGTH_LONG).show();


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );



        spinner2.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        //combo2(valo);
                        valo2 = spinner2.getSelectedItem().toString();
                        datos_elegidos();
                        consulta(valo, valo2);
                        //Toast.makeText(this, valo, LENGTH_LONG).show();


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );






    }




    public void datos_elegidos(){
       // String valor1 = spinner1.getSelectedItem().toString();
        //String valor2 = spinner2.getSelectedItem().toString();
        Toast.makeText(this, "valor 1 : " + valo +  "valor 2: " + valo2, LENGTH_LONG).show();


     }

    public void combo1(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference fDatabaseRoot = database.getReference("reservas");

        fDatabaseRoot.child("departamento").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                final List<String> propertyAddressList = new ArrayList<>();

                for (DataSnapshot addressSnapshot: dataSnapshot.getChildren()) {
                    String propertyAddress = addressSnapshot.child("codigo").getValue(String.class);
                    if (propertyAddress!=null){
                        propertyAddressList.add(propertyAddress);
                    }
                }

                // Spinner spinnerProperty = (Spinner) findViewById(R.id.spinner);
                ArrayAdapter<String> addressAdapter = new ArrayAdapter<String>(Intropage.this, android.R.layout.simple_spinner_item, propertyAddressList);
                addressAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(addressAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public void combo2(String valor) {


        Toast.makeText(getApplicationContext(), valor, LENGTH_LONG).show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference fDatabaseRoot = database.getReference("reservas");

        fDatabaseRoot.child("distrito").orderByChild("codigodepartamento").equalTo(valor).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                final List<String> propertyAddressList = new ArrayList<>();

                for (DataSnapshot addressSnapshot: dataSnapshot.getChildren()) {
                    String propertyAddress = addressSnapshot.child("codigo").getValue(String.class);
                    if (propertyAddress!=null){
                        propertyAddressList.add(propertyAddress);
                    }
                }

                Spinner spinnerProperty = (Spinner) findViewById(R.id.spinner2);
                ArrayAdapter<String> addressAdapter = new ArrayAdapter<String>(Intropage.this, android.R.layout.simple_spinner_item, propertyAddressList);
                addressAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerProperty.setAdapter(addressAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }






    public void consulta(String valor, String valor2) {
        Toast.makeText(getApplicationContext(), valor+"_"+valor2, LENGTH_LONG).show();

        myRef = databaseerupciones.getReference("reservas").child("reportes");
        myRef.orderByChild("departamento_distrito").equalTo(valor+"_"+valor2).addValueEventListener(new com.google.firebase.database.ValueEventListener() {
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


                    RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list, Intropage.this);
                    RecyclerView.LayoutManager recyce = new GridLayoutManager(Intropage.this, 1);
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

        //  Intent intent = new Intent(Intropage.this, Intropage.class);
        // intent.putExtra("ACTIVIDAD_RECIENTE", urlString);
        //startActivity(intent);

    }



}



