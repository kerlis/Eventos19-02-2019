package peru.volcanes.igp.eventos

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.nfc.Tag
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.database.*
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import io.fabric.sdk.android.services.common.CommonUtils
import kotlinx.android.synthetic.main.activity_elegirhoraa.*
import java.text.DecimalFormat
import com.prolificinteractive.materialcalendarview.CalendarDay
import android.widget.Toast
import android.support.annotation.NonNull
import android.support.v4.content.ContextCompat
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener
import java.util.*
import kotlin.math.truncate

class Elegirhoraa : AppCompatActivity()  {
    lateinit var valorhorario: String
    lateinit var texto_horario1: String


    var intensidad: String = ""
    var hora: String = ""

    lateinit var texto_horario3: String

    internal lateinit var databaseerupciones: FirebaseDatabase
    internal lateinit var myRef: DatabaseReference
    var ct: Int = 0
    lateinit var dia: String
    lateinit var mes: String
    var valint: Int = 0
    lateinit var valtostring: String
    lateinit var anio: String
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_elegirhoraa)

        val i = this.intent
        val local_val: String = i.extras!!.getString("LOCAL")
        val distrito_val: String = i.extras!!.getString("DISTRITO")
        val nombre_val: String = i.extras!!.getString("NOMBRE")

        val local: TextView = findViewById(R.id.local) as TextView
        val cancha: TextView = findViewById(R.id.cancha) as TextView
        val distrito: TextView = findViewById(R.id.distrito) as TextView


       // val colorvalue = ContextCompat.getColor(cont)

        local.text = "Local: " + local_val
        cancha.text = "Nombre: " + nombre_val
        distrito.text = "Distrito: " + distrito_val


        var horario1: Button = findViewById(R.id.horario1) as Button
        var horario2: Button = findViewById(R.id.horario2) as Button
        var horario3: Button = findViewById(R.id.horario3) as Button
        var horario4: Button = findViewById(R.id.horario4) as Button
        var horario5: Button = findViewById(R.id.horario5) as Button
        var horario6: Button = findViewById(R.id.horario6) as Button
        var horario7: Button = findViewById(R.id.horario7) as Button
        var horario8: Button = findViewById(R.id.horario8) as Button
        var horario9: Button = findViewById(R.id.horario9) as Button
        var horario10: Button = findViewById(R.id.horario10) as Button
        var horario11: Button = findViewById(R.id.horario11) as Button
        var horario12: Button = findViewById(R.id.horario12) as Button

        texto_horario1 = horario1.text.toString()
        var texto_horario2: String = horario2.text.toString()
        texto_horario3 = horario3.text.toString()
        var texto_horario4: String = horario4.text.toString()
        var texto_horario5: String = horario5.text.toString()
        var texto_horario6: String = horario6.text.toString()
        var texto_horario7: String = horario7.text.toString()
        var texto_horario8: String = horario8.text.toString()
        var texto_horario9: String = horario9.text.toString()
        var texto_horario10: String = horario10.text.toString()
        var texto_horario11: String = horario11.text.toString()
        var texto_horario12: String = horario12.text.toString()


        var calendario: MaterialCalendarView   = findViewById(R.id.calendarView) as MaterialCalendarView

        var calendar: Calendar  = Calendar.getInstance()

        var calendar2: Calendar  = Calendar.getInstance()
        calendario.setDateSelected(calendar.time, true)



        if(calendar == calendar2){

            calendar.time.day

            dia = calendar.time.day.toString()
            mes = calendar.time.month.toString()
            valint = mes.toInt() + 1
            valtostring = valint.toString()
            anio = calendar.time.year.toString()

            horario1.setBackgroundColor(Color.parseColor("#00cf7c"))
            horario2.setBackgroundColor(Color.parseColor("#00cf7c"))
            horario3.setBackgroundColor(Color.parseColor("#00cf7c"))

            horario4.setBackgroundColor(Color.parseColor("#00cf7c"))
            horario5.setBackgroundColor(Color.parseColor("#00cf7c"))
            horario6.setBackgroundColor(Color.parseColor("#00cf7c"))
            horario7.setBackgroundColor(Color.parseColor("#00cf7c"))
            horario8.setBackgroundColor(Color.parseColor("#00cf7c"))
            horario9.setBackgroundColor(Color.parseColor("#00cf7c"))
            horario10.setBackgroundColor(Color.parseColor("#00cf7c"))
            horario11.setBackgroundColor(Color.parseColor("#00cf7c"))
            horario12.setBackgroundColor(Color.parseColor("#00cf7c"))

            Verificardisponibilidad(texto_horario1, dia+valtostring+anio)
            Verificardisponibilidad2(texto_horario2, dia+valtostring+anio)
            Verificardisponibilidad3(texto_horario3, dia+valtostring+anio)

            Verificardisponibilidad4(texto_horario4, dia+valtostring+anio)
            Verificardisponibilidad5(texto_horario5, dia+valtostring+anio)
            Verificardisponibilidad6(texto_horario6, dia+valtostring+anio)
            Verificardisponibilidad7(texto_horario7, dia+valtostring+anio)
            Verificardisponibilidad8(texto_horario8, dia+valtostring+anio)
            Verificardisponibilidad9(texto_horario9, dia+valtostring+anio)
            Verificardisponibilidad10(texto_horario10, dia+valtostring+anio)
            Verificardisponibilidad11(texto_horario11, dia+valtostring+anio)
            Verificardisponibilidad12(texto_horario12, dia+valtostring+anio)

        }


        calendario.setOnDateChangedListener { widget, date, selected ->
            Toast.makeText(this, date.day.toString() , Toast.LENGTH_LONG).show()

            dia = date.day.toString()
            mes = date.month.toString()
            valint = mes.toInt() + 1
            valtostring = valint.toString()
            anio = date.year.toString()

            horario1.setBackgroundColor(Color.parseColor("#00cf7c"))
            horario2.setBackgroundColor(Color.parseColor("#00cf7c"))
            horario3.setBackgroundColor(Color.parseColor("#00cf7c"))

            horario4.setBackgroundColor(Color.parseColor("#00cf7c"))
            horario5.setBackgroundColor(Color.parseColor("#00cf7c"))
            horario6.setBackgroundColor(Color.parseColor("#00cf7c"))
            horario7.setBackgroundColor(Color.parseColor("#00cf7c"))
            horario8.setBackgroundColor(Color.parseColor("#00cf7c"))
            horario9.setBackgroundColor(Color.parseColor("#00cf7c"))
            horario10.setBackgroundColor(Color.parseColor("#00cf7c"))
            horario11.setBackgroundColor(Color.parseColor("#00cf7c"))
            horario12.setBackgroundColor(Color.parseColor("#00cf7c"))

            Verificardisponibilidad(texto_horario1, dia+valtostring+anio)
            Verificardisponibilidad2(texto_horario2, dia+valtostring+anio)
            Verificardisponibilidad3(texto_horario3, dia+valtostring+anio)


            Verificardisponibilidad4(texto_horario4, dia+valtostring+anio)
            Verificardisponibilidad5(texto_horario5, dia+valtostring+anio)
            Verificardisponibilidad6(texto_horario6, dia+valtostring+anio)
            Verificardisponibilidad7(texto_horario7, dia+valtostring+anio)
            Verificardisponibilidad8(texto_horario8, dia+valtostring+anio)
            Verificardisponibilidad9(texto_horario9, dia+valtostring+anio)
            Verificardisponibilidad10(texto_horario10, dia+valtostring+anio)
            Verificardisponibilidad11(texto_horario11, dia+valtostring+anio)
            Verificardisponibilidad12(texto_horario12, dia+valtostring+anio)

        }


        horario1.setOnClickListener {
            valorhorario = horario1.getText() as String;
            //  Toast.makeText(this, valorhorario, Toast.LENGTH_LONG).show();
            var ds  = horario1.background
            var d = (ds as ColorDrawable).color
            //   Toast.makeText(this, d.toString(), Toast.LENGTH_LONG).show()


            if(d.toString() == "-16724100"){
                showNewNameDialog()
            }
            else{
                Toast.makeText(this, "El horario ya esta reservado", Toast.LENGTH_LONG).show()
            }

            /// -2740690
        }


        horario2.setOnClickListener {
            valorhorario = horario2.getText() as String;
            //  Toast.makeText(this, valorhorario, Toast.LENGTH_LONG).show();
            var ds  = horario2.background
            var d = (ds as ColorDrawable).color
            //   Toast.makeText(this, d.toString(), Toast.LENGTH_LONG).show()


            if(d.toString() == "-16724100"){
                showNewNameDialog()
            }
            else{
                Toast.makeText(this, "El horario ya esta reservado", Toast.LENGTH_LONG).show()
            }

            /// -2740690
        }


        horario3.setOnClickListener {
            valorhorario = horario3.getText() as String;
          //  Toast.makeText(this, valorhorario, Toast.LENGTH_LONG).show();
            var ds  = horario3.background
            var d = (ds as ColorDrawable).color
         //   Toast.makeText(this, d.toString(), Toast.LENGTH_LONG).show()


            if(d.toString() == "-16724100"){
                showNewNameDialog()
            }
            else{
                Toast.makeText(this, "El horario ya esta reservado", Toast.LENGTH_LONG).show()
            }

           /// -2740690
        }



    }







    @SuppressLint("SetTextI18n")
    fun showNewNameDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dislogo, null)
        dialogBuilder.setView(dialogView)

        val horario = dialogView.findViewById<View>(R.id.horario) as TextView
        val fecha = dialogView.findViewById<View>(R.id.fecha) as TextView
        val local = dialogView.findViewById<View>(R.id.local) as TextView
        val cancha = dialogView.findViewById<View>(R.id.cancha) as TextView
        val distrito = dialogView.findViewById<View>(R.id.distrito) as TextView

        horario.text = "dfref"
        fecha.text = "dfref"
        local.text = "dfref"

        cancha.text = "dfref"
        distrito.text = "dfref"


        val b = dialogBuilder.create()
        b.show()
    }







    fun Verificardisponibilidad(valor1:String, valor2:String): String? {

        var mFirebaseDatabase2: DatabaseReference? = null
        FirebaseDatabase.getInstance()
        val database2 = FirebaseDatabase.getInstance()
        mFirebaseDatabase2 = database2.getReference("reservas").child("reportes")
        mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1).limitToFirst(1).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                val sreporte4 = dataSnapshot.getValue<Reservas>(Reservas::class.java)
                guardar_prefkk(sreporte4)
            }
            override fun onChildChanged(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            }
            override fun onChildMoved(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
        var ds: String= ""
        return   ds
    }

    fun guardar_prefkk(sreporte: Reservas?) {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        var editor = prefs.edit()
        editor.putString("incategoriak", sreporte!!.getFecha())
        editor.putString("inreferenciak", sreporte!!.getHora())

        editor.apply()
        mostrar_ult_sismo()
    }

    @SuppressLint("ResourceAsColor")
    fun mostrar_ult_sismo() {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
          intensidad = prefs.getString("incategoriak", "")
          hora = prefs.getString("inreferenciak", "")
        Toast.makeText(this, intensidad + hora, Toast.LENGTH_LONG).show()

        if (intensidad == null){
            horario1.setBackgroundColor(Color.parseColor("#00cf7c"))


        }
        else{
            horario1.setBackgroundColor(Color.parseColor("#d62e2e"))


        }
    }






    fun Verificardisponibilidad2(valor1:String, valor2:String): String? {

        var mFirebaseDatabase2: DatabaseReference? = null
        FirebaseDatabase.getInstance()
        val database2 = FirebaseDatabase.getInstance()
        mFirebaseDatabase2 = database2.getReference("reservas").child("reportes")
        mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1).limitToFirst(1).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                val sreporte4 = dataSnapshot.getValue<Reservas>(Reservas::class.java)
                guardar_prefkk2(sreporte4)
            }
            override fun onChildChanged(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            }
            override fun onChildMoved(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
        var ds: String= ""
        return   ds
    }

    fun guardar_prefkk2(sreporte: Reservas?) {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        var editor = prefs.edit()
        editor.putString("incategoriak", sreporte!!.getFecha())
        editor.putString("inreferenciak", sreporte!!.getHora())

        editor.apply()
        mostrar_ult_sismo2()
    }

    @SuppressLint("ResourceAsColor")
    fun mostrar_ult_sismo2() {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        intensidad = prefs.getString("incategoriak", "")
        hora = prefs.getString("inreferenciak", "")
        Toast.makeText(this, intensidad + hora, Toast.LENGTH_LONG).show()

        if (intensidad == null){
            horario2.setBackgroundColor(Color.parseColor("#00cf7c"))

        }
        else{
            horario2.setBackgroundColor(Color.parseColor("#d62e2e"))

        }
    }




    fun Verificardisponibilidad3(valor1:String, valor2:String): String? {

        var mFirebaseDatabase2: DatabaseReference? = null
        FirebaseDatabase.getInstance()
        val database2 = FirebaseDatabase.getInstance()
        mFirebaseDatabase2 = database2.getReference("reservas").child("reportes")
        mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1).limitToFirst(1).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                val sreporte4 = dataSnapshot.getValue<Reservas>(Reservas::class.java)
                guardar_prefkk3(sreporte4)
            }
            override fun onChildChanged(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            }
            override fun onChildMoved(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
        var ds: String= ""
        return   ds
    }

    fun guardar_prefkk3(sreporte: Reservas?) {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        var editor = prefs.edit()
        editor.putString("incategoriak", sreporte!!.getFecha())
        editor.putString("inreferenciak", sreporte!!.getHora())

        editor.apply()
        mostrar_ult_sismo3()
    }

    @SuppressLint("ResourceAsColor")
    fun mostrar_ult_sismo3() {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        intensidad = prefs.getString("incategoriak", "")
        hora = prefs.getString("inreferenciak", "")
        Toast.makeText(this, intensidad + hora, Toast.LENGTH_LONG).show()

        if (intensidad == null){
            horario3.setBackgroundColor(Color.parseColor("#00cf7c"))

        }
        else{
            horario3.setBackgroundColor(Color.parseColor("#d62e2e"))

        }
    }


    fun Verificardisponibilidad4(valor1:String, valor2:String): String? {

        var mFirebaseDatabase2: DatabaseReference? = null
        FirebaseDatabase.getInstance()
        val database2 = FirebaseDatabase.getInstance()
        mFirebaseDatabase2 = database2.getReference("reservas").child("reportes")
        mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1).limitToFirst(1).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                val sreporte4 = dataSnapshot.getValue<Reservas>(Reservas::class.java)
                guardar_prefkk4(sreporte4)
            }
            override fun onChildChanged(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            }
            override fun onChildMoved(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
        var ds: String= ""
        return   ds
    }

    fun guardar_prefkk4(sreporte: Reservas?) {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        var editor = prefs.edit()
        editor.putString("incategoriak", sreporte!!.getFecha())
        editor.putString("inreferenciak", sreporte!!.getHora())

        editor.apply()
        mostrar_ult_sismo4()
    }

    @SuppressLint("ResourceAsColor")
    fun mostrar_ult_sismo4() {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        intensidad = prefs.getString("incategoriak", "")
        hora = prefs.getString("inreferenciak", "")
        Toast.makeText(this, intensidad + hora, Toast.LENGTH_LONG).show()

        if (intensidad == null){
            horario4.setBackgroundColor(Color.parseColor("#00cf7c"))

        }
        else{
            horario4.setBackgroundColor(Color.parseColor("#d62e2e"))

        }
    }





    fun Verificardisponibilidad5(valor1:String, valor2:String): String? {

        var mFirebaseDatabase2: DatabaseReference? = null
        FirebaseDatabase.getInstance()
        val database2 = FirebaseDatabase.getInstance()
        mFirebaseDatabase2 = database2.getReference("reservas").child("reportes")
        mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1).limitToFirst(1).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                val sreporte4 = dataSnapshot.getValue<Reservas>(Reservas::class.java)
                guardar_prefkk5(sreporte4)
            }
            override fun onChildChanged(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            }
            override fun onChildMoved(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
        var ds: String= ""
        return   ds
    }

    fun guardar_prefkk5(sreporte: Reservas?) {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        var editor = prefs.edit()
        editor.putString("incategoriak", sreporte!!.getFecha())
        editor.putString("inreferenciak", sreporte!!.getHora())

        editor.apply()
        mostrar_ult_sismo5()
    }

    @SuppressLint("ResourceAsColor")
    fun mostrar_ult_sismo5() {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        intensidad = prefs.getString("incategoriak", "")
        hora = prefs.getString("inreferenciak", "")
        Toast.makeText(this, intensidad + hora, Toast.LENGTH_LONG).show()

        if (intensidad == null){
            horario5.setBackgroundColor(Color.parseColor("#00cf7c"))

        }
        else{
            horario5.setBackgroundColor(Color.parseColor("#d62e2e"))

        }
    }




    fun Verificardisponibilidad6(valor1:String, valor2:String): String? {

        var mFirebaseDatabase2: DatabaseReference? = null
        FirebaseDatabase.getInstance()
        val database2 = FirebaseDatabase.getInstance()
        mFirebaseDatabase2 = database2.getReference("reservas").child("reportes")
        mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1).limitToFirst(1).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                val sreporte4 = dataSnapshot.getValue<Reservas>(Reservas::class.java)
                guardar_prefkk6(sreporte4)
            }
            override fun onChildChanged(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            }
            override fun onChildMoved(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
        var ds: String= ""
        return   ds
    }

    fun guardar_prefkk6(sreporte: Reservas?) {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        var editor = prefs.edit()
        editor.putString("incategoriak", sreporte!!.getFecha())
        editor.putString("inreferenciak", sreporte!!.getHora())

        editor.apply()
        mostrar_ult_sismo6()
    }

    @SuppressLint("ResourceAsColor")
    fun mostrar_ult_sismo6() {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        intensidad = prefs.getString("incategoriak", "")
        hora = prefs.getString("inreferenciak", "")
        Toast.makeText(this, intensidad + hora, Toast.LENGTH_LONG).show()

        if (intensidad == null){
            horario6.setBackgroundColor(Color.parseColor("#00cf7c"))

        }
        else{
            horario6.setBackgroundColor(Color.parseColor("#d62e2e"))

        }
    }




    fun Verificardisponibilidad7(valor1:String, valor2:String): String? {

        var mFirebaseDatabase2: DatabaseReference? = null
        FirebaseDatabase.getInstance()
        val database2 = FirebaseDatabase.getInstance()
        mFirebaseDatabase2 = database2.getReference("reservas").child("reportes")
        mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1).limitToFirst(1).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                val sreporte4 = dataSnapshot.getValue<Reservas>(Reservas::class.java)
                guardar_prefkk7(sreporte4)
            }
            override fun onChildChanged(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            }
            override fun onChildMoved(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
        var ds: String= ""
        return   ds
    }

    fun guardar_prefkk7(sreporte: Reservas?) {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        var editor = prefs.edit()
        editor.putString("incategoriak", sreporte!!.getFecha())
        editor.putString("inreferenciak", sreporte!!.getHora())

        editor.apply()
        mostrar_ult_sismo7()
    }

    @SuppressLint("ResourceAsColor")
    fun mostrar_ult_sismo7() {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        intensidad = prefs.getString("incategoriak", "")
        hora = prefs.getString("inreferenciak", "")
        Toast.makeText(this, intensidad + hora, Toast.LENGTH_LONG).show()

        if (intensidad == null){
            horario7.setBackgroundColor(Color.parseColor("#00cf7c"))

        }
        else{
            horario7.setBackgroundColor(Color.parseColor("#d62e2e"))

        }
    }




    fun Verificardisponibilidad8(valor1:String, valor2:String): String? {

        var mFirebaseDatabase2: DatabaseReference? = null
        FirebaseDatabase.getInstance()
        val database2 = FirebaseDatabase.getInstance()
        mFirebaseDatabase2 = database2.getReference("reservas").child("reportes")
        mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1).limitToFirst(1).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                val sreporte4 = dataSnapshot.getValue<Reservas>(Reservas::class.java)
                guardar_prefkk8(sreporte4)
            }
            override fun onChildChanged(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            }
            override fun onChildMoved(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
        var ds: String= ""
        return   ds
    }

    fun guardar_prefkk8(sreporte: Reservas?) {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        var editor = prefs.edit()
        editor.putString("incategoriak", sreporte!!.getFecha())
        editor.putString("inreferenciak", sreporte!!.getHora())

        editor.apply()
        mostrar_ult_sismo8()
    }

    @SuppressLint("ResourceAsColor")
    fun mostrar_ult_sismo8() {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        intensidad = prefs.getString("incategoriak", "")
        hora = prefs.getString("inreferenciak", "")
        Toast.makeText(this, intensidad + hora, Toast.LENGTH_LONG).show()

        if (intensidad == null){
            horario8.setBackgroundColor(Color.parseColor("#00cf7c"))

        }
        else{
            horario8.setBackgroundColor(Color.parseColor("#d62e2e"))

        }
    }






    fun Verificardisponibilidad9(valor1:String, valor2:String): String? {

        var mFirebaseDatabase2: DatabaseReference? = null
        FirebaseDatabase.getInstance()
        val database2 = FirebaseDatabase.getInstance()
        mFirebaseDatabase2 = database2.getReference("reservas").child("reportes")
        mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1).limitToFirst(1).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                val sreporte4 = dataSnapshot.getValue<Reservas>(Reservas::class.java)
                guardar_prefkk9(sreporte4)
            }
            override fun onChildChanged(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            }
            override fun onChildMoved(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
        var ds: String= ""
        return   ds
    }

    fun guardar_prefkk9(sreporte: Reservas?) {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        var editor = prefs.edit()
        editor.putString("incategoriak", sreporte!!.getFecha())
        editor.putString("inreferenciak", sreporte!!.getHora())

        editor.apply()
        mostrar_ult_sismo9()
    }

    @SuppressLint("ResourceAsColor")
    fun mostrar_ult_sismo9() {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        intensidad = prefs.getString("incategoriak", "")
        hora = prefs.getString("inreferenciak", "")
        Toast.makeText(this, intensidad + hora, Toast.LENGTH_LONG).show()

        if (intensidad == null){
            horario9.setBackgroundColor(Color.parseColor("#00cf7c"))

        }
        else{
            horario9.setBackgroundColor(Color.parseColor("#d62e2e"))

        }
    }







    fun Verificardisponibilidad10(valor1:String, valor2:String): String? {

        var mFirebaseDatabase2: DatabaseReference? = null
        FirebaseDatabase.getInstance()
        val database2 = FirebaseDatabase.getInstance()
        mFirebaseDatabase2 = database2.getReference("reservas").child("reportes")
        mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1).limitToFirst(1).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                val sreporte4 = dataSnapshot.getValue<Reservas>(Reservas::class.java)
                guardar_prefkk10(sreporte4)
            }
            override fun onChildChanged(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            }
            override fun onChildMoved(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
        var ds: String= ""
        return   ds
    }

    fun guardar_prefkk10(sreporte: Reservas?) {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        var editor = prefs.edit()
        editor.putString("incategoriak", sreporte!!.getFecha())
        editor.putString("inreferenciak", sreporte!!.getHora())

        editor.apply()
        mostrar_ult_sismo10()
    }

    @SuppressLint("ResourceAsColor")
    fun mostrar_ult_sismo10() {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        intensidad = prefs.getString("incategoriak", "")
        hora = prefs.getString("inreferenciak", "")
        Toast.makeText(this, intensidad + hora, Toast.LENGTH_LONG).show()

        if (intensidad == null){
            horario10.setBackgroundColor(Color.parseColor("#00cf7c"))

        }
        else{
            horario10.setBackgroundColor(Color.parseColor("#d62e2e"))

        }
    }





    fun Verificardisponibilidad11(valor1:String, valor2:String): String? {

        var mFirebaseDatabase2: DatabaseReference? = null
        FirebaseDatabase.getInstance()
        val database2 = FirebaseDatabase.getInstance()
        mFirebaseDatabase2 = database2.getReference("reservas").child("reportes")
        mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1).limitToFirst(1).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                val sreporte4 = dataSnapshot.getValue<Reservas>(Reservas::class.java)
                guardar_prefkk11(sreporte4)
            }
            override fun onChildChanged(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            }
            override fun onChildMoved(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
        var ds: String= ""
        return   ds
    }

    fun guardar_prefkk11(sreporte: Reservas?) {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        var editor = prefs.edit()
        editor.putString("incategoriak", sreporte!!.getFecha())
        editor.putString("inreferenciak", sreporte!!.getHora())

        editor.apply()
        mostrar_ult_sismo11()
    }

    @SuppressLint("ResourceAsColor")
    fun mostrar_ult_sismo11() {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        intensidad = prefs.getString("incategoriak", "")
        hora = prefs.getString("inreferenciak", "")
        Toast.makeText(this, intensidad + hora, Toast.LENGTH_LONG).show()

        if (intensidad == null){
            horario11.setBackgroundColor(Color.parseColor("#00cf7c"))

        }
        else{
            horario11.setBackgroundColor(Color.parseColor("#d62e2e"))

        }
    }







    fun Verificardisponibilidad12(valor1:String, valor2:String): String? {

        var mFirebaseDatabase2: DatabaseReference? = null
        FirebaseDatabase.getInstance()
        val database2 = FirebaseDatabase.getInstance()
        mFirebaseDatabase2 = database2.getReference("reservas").child("reportes")
        mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1).limitToFirst(1).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                val sreporte4 = dataSnapshot.getValue<Reservas>(Reservas::class.java)
                guardar_prefkk12(sreporte4)
            }
            override fun onChildChanged(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            }
            override fun onChildMoved(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
        var ds: String= ""
        return   ds
    }

    fun guardar_prefkk12(sreporte: Reservas?) {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        var editor = prefs.edit()
        editor.putString("incategoriak", sreporte!!.getFecha())
        editor.putString("inreferenciak", sreporte!!.getHora())

        editor.apply()
        mostrar_ult_sismo12()
    }

    @SuppressLint("ResourceAsColor")
    fun mostrar_ult_sismo12() {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        intensidad = prefs.getString("incategoriak", "")
        hora = prefs.getString("inreferenciak", "")
        Toast.makeText(this, intensidad + hora, Toast.LENGTH_LONG).show()

        if (intensidad == null){
            horario12.setBackgroundColor(Color.parseColor("#00cf7c"))

        }
        else{
            horario12.setBackgroundColor(Color.parseColor("#d62e2e"))

        }
    }

}
