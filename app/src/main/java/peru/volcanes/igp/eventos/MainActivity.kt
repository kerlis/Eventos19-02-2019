package peru.volcanes.igp.eventos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.getvalue_activity)


         val textView: TextView = findViewById(R.id.texto) as TextView
        val boton: TextView = findViewById(R.id.botonenviar) as Button
        textView.text = "dasdsad"

boton.setOnClickListener {
    println(Toast.makeText(this, "asdsad", Toast.LENGTH_LONG).show())
}


    }
}
