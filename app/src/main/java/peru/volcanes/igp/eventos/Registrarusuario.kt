package peru.volcanes.igp.eventos

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import peru.volcanes.igp.eventos.m_model.usuario

class Registrarusuario : AppCompatActivity() {

    lateinit var usuario: usuario


    lateinit var nombres_val:String
    lateinit var apellidos_val:String
    lateinit var email_val:String
    lateinit var telefono_val:String
    lateinit var dni_val:String
    lateinit var password_val:String
    lateinit var rgistrar_val:String





    lateinit var nombres: EditText
    lateinit var apellidos: EditText
    lateinit var email: EditText
    lateinit var telefono: EditText
    lateinit var dni: EditText
    lateinit var password: EditText
    lateinit var registrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarusuario)

          nombres = findViewById(R.id.nombre) as EditText
          apellidos  = findViewById(R.id.apellidos) as EditText
          email = findViewById(R.id.email) as EditText
          telefono  = findViewById(R.id.telefono) as EditText
          dni  = findViewById(R.id.dni) as EditText
          password  = findViewById(R.id.password) as EditText
          registrar = findViewById(R.id.registrar) as Button





        nombres.setHintTextColor(Color.WHITE)
        apellidos.setHintTextColor(Color.WHITE)
        email.setHintTextColor(Color.WHITE)
        telefono.setHintTextColor(Color.WHITE)
        dni.setHintTextColor(Color.WHITE)
        password.setHintTextColor(Color.WHITE)





        registrar.setOnClickListener {

            var ref = FirebaseDatabase.getInstance().getReference("reservas").child("usuarios")

            var reporteid = ref.push().key

            nombres_val = nombres.text.toString()
            apellidos_val = apellidos.text.toString()
            email_val = email.text.toString()
            telefono_val = telefono.text.toString()
            dni_val =   dni.text.toString()
            password_val =  password.text.toString()
            rgistrar_val =  registrar.text.toString()

                 usuario    =   usuario(nombres_val,apellidos_val,email_val,telefono_val,dni_val,password_val)




            ref.child(reporteid).setValue(usuario).addOnCompleteListener {
                Toast.makeText(this, "Usuario registrado", Toast.LENGTH_LONG).show();

            }

        }





    }
}
