package peru.volcanes.igp.eventos;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import peru.volcanes.igp.eventos.m_model.canchas;
import peru.volcanes.igp.eventos.m_ui.Canchasadapter;

import static android.widget.Toast.LENGTH_LONG;

public class Seleccionarcanchas extends AppCompatActivity {

    private RelativeLayout mDrawerBlock;

    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    ImageView sliderz;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;


    ListView reservas;
    private final String TAG = "getvale";
    private CalendarView nCalendarView;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase database;
    ArrayList<peru.volcanes.igp.eventos.m_model.canchas> objetoerupciones = new ArrayList<canchas>();

    FirebaseDatabase databaseerupciones;
    DatabaseReference myRef ;
    List<canchas> list;
    RecyclerView recycle;

    Spinner spinner1;
    Spinner spinner2;


    String valo = "1";
    String valo2 = "1";


    String valorconvertido = "1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionarcanchas);







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
                         if(valo.equals("ate")){
                            valorconvertido = "1";
                        }
                        else if(valo.equals("barranco")){
                            valorconvertido = "2";
                        }
                        else if(valo.equals("san fernando")){
                            valorconvertido = "3";
                        }
                        else if(valo.equals("trujillo")){
                            valorconvertido = "4";
                        }
                        else{
                            valorconvertido = "0";
                        }

                       //  Toast.makeText(this, valo, Toast.LENGTH_LONG).show();

                         datos_elegidos();
                         combo2(valorconvertido);

                         consulta(valorconvertido, valo2);


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
                       // datos_elegidos();

                        consulta(valorconvertido, valo2);
                        //Toast.makeText(this, valo, LENGTH_LONG).show();


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );





        sliderz = (ImageView) findViewById(R.id.sliderz);
        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerBlock = (RelativeLayout) findViewById(R.id.mDrawerBlock);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();
        sliderz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });





    }

    public void datos_elegidos(){
        // String valor1 = spinner1.getSelectedItem().toString();
        //String valor2 = spinner2.getSelectedItem().toString();
       // Toast.makeText(this, "valor 1 : " + valo +  "valor 2: " + valorconvertido, LENGTH_LONG).show();


    }


    public void combo1(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference fDatabaseRoot = database.getReference("reservas");
        fDatabaseRoot.child("distrito").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> propertyAddressList = new ArrayList<>();
                for (DataSnapshot addressSnapshot: dataSnapshot.getChildren()) {
                    String propertyAddress = addressSnapshot.child("nombre").getValue(String.class);
                    if (propertyAddress!=null){
                        propertyAddressList.add(propertyAddress);
                    }
                }
                ArrayAdapter<String> addressAdapter = new ArrayAdapter<String>(Seleccionarcanchas.this, android.R.layout.simple_spinner_item, propertyAddressList);
                addressAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(addressAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }





    public void combo2(String valor) {
       // Toast.makeText(getApplicationContext(), valor, LENGTH_LONG).show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference fDatabaseRoot = database.getReference("reservas");

        fDatabaseRoot.child("local").orderByChild("distrito").equalTo(valor).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                final List<String> propertyAddressList = new ArrayList<>();

                for (DataSnapshot addressSnapshot: dataSnapshot.getChildren()) {

                    String propertyAddress = addressSnapshot.child("nombre").getValue(String.class);

                    if (propertyAddress!=null){
                        propertyAddressList.add(propertyAddress);
                    }
                }

                Spinner spinnerProperty = (Spinner) findViewById(R.id.spinner2);
                ArrayAdapter<String> addressAdapter = new ArrayAdapter<String>(Seleccionarcanchas.this, android.R.layout.simple_spinner_item, propertyAddressList);
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

        myRef = databaseerupciones.getReference("reservas").child("canchas");
        myRef.orderByChild("local_distrito").equalTo(valor2+"_"+valor).addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                list = new ArrayList<canchas>();
                for (com.google.firebase.database.DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    canchas value = dataSnapshot1.getValue(canchas.class);
                    canchas fire = new canchas();

                    String distrito = value.getDistrito();
                    String local = value.getLocal();
                    String nombre = value.getNombre();
                    fire.setDistrito(distrito);
                    fire.setLocal(local);
                    fire.setNombre(nombre);
                    list.add(fire);


                    Canchasadapter recyclerAdapter = new Canchasadapter(list, Seleccionarcanchas.this);
                    RecyclerView.LayoutManager recyce = new GridLayoutManager(Seleccionarcanchas.this, 1);
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

    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }


}
