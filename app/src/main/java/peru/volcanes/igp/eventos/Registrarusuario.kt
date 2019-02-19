package peru.volcanes.igp.eventos
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import okhttp3.*
import peru.volcanes.igp.eventos.m_model.usuario
import java.io.IOException

class Registrarusuario : AppCompatActivity() {
    private val BASE_URL = "http://arteypixel.com/appreserva/registrar_usuario_app.php"
    private val client = OkHttpClient()
    internal var TAG_REGISTER: String? = null
    lateinit var usuario: usuario
    lateinit var nombres_val:String
    lateinit var apellidos_val:String
    lateinit var email_val:String
    lateinit var telefono_val:String
    lateinit var dni_val:String
    lateinit var password_val:String
    lateinit var rgistrar_val:String
    lateinit var nombres: EditText
    lateinit var
            apellidos: EditText
    lateinit var email: EditText
    lateinit var telefono: EditText
    lateinit var dni: EditText
    lateinit var password: EditText
    lateinit var registrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarusuario)

        nombres = findViewById(R.id.nombre) as EditText
      //  apellidos  = findViewById(R.id.apellidos) as EditText
        email = findViewById(R.id.email) as EditText
        telefono  = findViewById(R.id.telefono) as EditText
        dni  = findViewById(R.id.dni) as EditText
        password  = findViewById(R.id.password) as EditText
        registrar = findViewById(R.id.registrar) as Button

        nombres.setHintTextColor(Color.WHITE)
       // apellidos.setHintTextColor(Color.WHITE)
        email.setHintTextColor(Color.WHITE)
        telefono.setHintTextColor(Color.WHITE)
        dni.setHintTextColor(Color.WHITE)
        password.setHintTextColor(Color.WHITE)

        registrar.setOnClickListener {

            var ref = FirebaseDatabase.getInstance().getReference("reservas").child("usuarios")
            var reporteid = ref.push().key

            nombres_val = nombres.text.toString()
          //  apellidos_val = apellidos.text.toString()
            email_val = email.text.toString()
            telefono_val = telefono.text.toString()
            dni_val =   dni.text.toString()
            password_val =  password.text.toString()
            rgistrar_val =  registrar.text.toString()
            usuario    =   usuario(nombres_val,email_val,telefono_val,dni_val,password_val)

            ref.child(reporteid).setValue(usuario).addOnCompleteListener {
                Toast.makeText(this, "Usuario registrado", Toast.LENGTH_LONG).show();

            }

            registerUser(nombres_val,email_val,telefono_val,dni_val,password_val)
        }

    }


    fun registerUser(nombreusuario: String, email:String, telefono:String, dni:String, password:String) {
        val body = FormBody.Builder()
                .add("Nuevousuario", "$nombreusuario&$email&$telefono&$dni&$password")
                .build()
        val request = Request.Builder().url(BASE_URL).post(body).build()
        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Registration Error" + e.message)
            }
            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                try {
                    val resp = response.body().string()
                    Log.v(TAG_REGISTER, resp)
                   /* if (resp.length > 5) {
                        val intent7 = Intent(Registrarusuario, Seleccionarcanchas::class.java)
                        startActivity(intent7)

                        val sharedrf = PreferenceManager.getDefaultSharedPreferences(this@Login)
                        val editor = sharedrf.edit()
                        editor.putBoolean("Registered", true)
                        editor.putString("Username", resp)
                        editor.putString("Password", resp)
                        editor.apply()
                    } else {
                        ver2(resp)
                    }*/





                    ver2(resp)

                    if (response.isSuccessful) {





                    } else {

                    }
                } catch (e: IOException) {
                    // Log.e(TAG_REGISTER, "Exception caught: ", e);
                    println("Exception caught" + e.message)
                }
            }
        })
    }


    private fun ver2(dato:String) {

        //   val intent = Intent(this,Elegirhoraa::class.java)

        val intent7: Intent = Intent(this, Login::class.java)

        startActivity(intent7)



        runOnUiThread { Toast.makeText(applicationContext, "Status = $dato", Toast.LENGTH_LONG).show() }
    }



}
