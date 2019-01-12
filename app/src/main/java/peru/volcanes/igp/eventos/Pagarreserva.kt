package peru.volcanes.igp.eventos

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class Pagarreserva : AppCompatActivity() {
    var fecha: String = ""
    var hora: String = ""
    var departamento: String = ""
    var departamento_distrito: String = ""
    var distrito: String = ""
    var fecha_hora: String = ""
    var cancha_nombre: String = ""
    var local: String = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagarreserva)


        val i = this.intent
        fecha = i.extras!!.getString("FECHA")
        hora = i.extras!!.getString("HORA")
        departamento = i.extras!!.getString("DEPARTAMENTO")
        departamento_distrito = i.extras!!.getString("DEPARTAMENTO_DISTRITO")
        distrito = i.extras!!.getString("DISTRITO")
        fecha_hora = i.extras!!.getString("FECHA_HORA")
        cancha_nombre = i.extras!!.getString("CANCHA_NOMBRE")
        local = i.extras!!.getString("LOCAL")


        Toast.makeText(this, fecha  + " - " + hora + " - " + departamento + " - " + departamento_distrito + " - " + distrito + " - " + fecha_hora + " - " + cancha_nombre + " - "  + local, Toast.LENGTH_LONG).show()





    }

}
