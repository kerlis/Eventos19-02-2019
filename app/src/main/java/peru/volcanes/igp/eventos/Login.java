package peru.volcanes.igp.eventos;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.http.FormUrlEncoded;
import static android.content.ContentValues.TAG;

public class Login extends AppCompatActivity {
    private OkHttpClient client = new OkHttpClient();
    String TAG_REGISTER;
    String json;
    private static final String BASE_URL = "http://arteypixel.com/appreserva/consultarusuario.php";
    EditText usuario;
    EditText password;
    Button   logbutton;
    TextView notienecuenta;
    String valor1,valor2;

    String PREF_Name = "myprefs";
    SharedPreferences mypref = null;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);





        usuario = findViewById(R.id.usuario);
        password = findViewById(R.id.password);
        logbutton = findViewById(R.id.login);
        notienecuenta = findViewById(R.id.notienecuenta);

        logbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                valor1 =  usuario.getText().toString();
                valor2 =  password.getText().toString();
             //   detecta_sismo(valor1, valor2);
                registerUser(valor1, valor2);
            }
        });



         notienecuenta.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View arg0) {
                 Intent intent7 = new Intent(Login.this,Registrarusuario.class);
                 startActivity(intent7);
             }
         });

     }





    public void detecta_sismo(String val1, String val2) {

        Toast.makeText(Login.this,"Dato:  " + val1 + val2, Toast.LENGTH_LONG).show();



        OkHttpClient client = new OkHttpClient();
       RequestBody body = new FormBody.Builder()
                .add("Token", "uno,uno")
               .build();
        Request request = new Request.Builder()
                .url("http://arteypixel.com/appreserva/consultarusuario.php?Token=uno,uno")
                .post(body)
                .build();


        //  vale .url("http://intranet.igp.gob.pe/eLdZpqDtLN/index.php?Token="+token)
        //  .url("http://arteypixel.com/envio_notificaciones/register.php?Token="+token)

        consulta("http://arteypixel.com/appreserva/consultarusuario.php?Token=uno,uno");

        //   vale  consulta("http://arteypixel.com/envio_notificaciones/register.php?Token="+token);


        // FirebaseMessaging.getInstance().subscribeToTopic("VOLCANESD");
        //http://intranet.igp.gob.pe/eLdZpqDtLN



        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }






    public void registerUser(String username, String password) {
        RequestBody body = new FormBody.Builder()
                .add("Token", username+","+password)
                 .build();
        Request request = new Request.Builder().url(BASE_URL).post(body).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Registration Error" + e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    final String resp = response.body().string();


                    Integer vd = resp.length();
                    Log.v(TAG_REGISTER,"respueta: "+resp + vd);

/*
                    new Handler(Looper.getMainLooper().getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Login.this, "Dato:  " + resp, Toast.LENGTH_SHORT);
                        }
                    });
*/

                    if(resp.length() > 8){
                        Intent intent7 = new Intent(Login.this,Seleccionarcanchas.class);
                        startActivity(intent7);

                        final SharedPreferences sharedrf = PreferenceManager.getDefaultSharedPreferences(Login.this);
                        SharedPreferences.Editor editor = sharedrf.edit();
                        editor.putBoolean("Registered", true);
                        editor.putString("Username",resp);
                        editor.putString("Password", resp);
                        editor.apply();
                        ver2(resp);



                    }
                    else{
                        Intent intent7 = new Intent(Login.this,Login.class);
                        startActivity(intent7);
                        ver2(resp);
                    }



                    if (response.isSuccessful()) {
                    } else {

                    }
                } catch (IOException e) {
                    // Log.e(TAG_REGISTER, "Exception caught: ", e);
                    System.out.println("Exception caught" + e.getMessage());
                }
            }
/*
            @Override
            public void onFailure(Request request, IOException e) {
                //  Log.e(TAG_REGISTER, "Registration error: " + e.getMessage());
                System.out.println("Registration Error" + e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    String resp = response.body().string();
//                    Log.v(TAG_REGISTER, resp);
                    System.out.println(resp);
                    if (response.isSuccessful()) {
                    } else {

                    }
                } catch (IOException e) {
                    // Log.e(TAG_REGISTER, "Exception caught: ", e);
                    System.out.println("Exception caught" + e.getMessage());
                }
            }
            */
        });
    }



    public void consulta(String urlString)  {




        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = null;
            BufferedReader bufferedReader = null;
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            json = bufferedReader.readLine();
            //  Toast.makeText(getApplicationContext(), json, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "fdfdfd json: " + json);
            ver2(json);
            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ver2(final String dato) {
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), "Status = " + dato , Toast.LENGTH_LONG).show();
            }
        });

              //  Toast.makeText(Login.this, "Dato:  " + dato, Toast.LENGTH_SHORT);


      // Toast.makeText(Login.this,"Dato:  " + dato, Toast.LENGTH_LONG).show();

      //  Login.runOnUiThread(new Runnable() {
         //   public void run() {
              ///  Toast.makeText(Login, "Hello", Toast.LENGTH_SHORT).show();
          //  }
      //  });
        /*
        String Message5 = dato;
        String file_namex = "datos_ordences";
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_namex, MODE_PRIVATE);
            fileOutputStream.write(Message5.getBytes());
            // FirebaseMessaging.getInstance().subscribeToTopic(Message5);

            FirebaseMessaging.getInstance().subscribeToTopic("VOLCANESPERU30017");


            //  fileOutputStream.write(Message7.getBytes());
            fileOutputStream.close();
            //  Toast.makeText(getApplicationContext(), "Configurado", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }






}
