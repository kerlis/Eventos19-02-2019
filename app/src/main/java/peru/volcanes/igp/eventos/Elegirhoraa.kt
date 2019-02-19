package peru.volcanes.igp.eventos
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.nfc.Tag
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
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
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.truncate

import peru.volcanes.igp.eventos.m_model.reservas

class Elegirhoraa : AppCompatActivity()  {
    lateinit var valorhorario: String

    lateinit var calendar: Calendar
    lateinit var calendar2: Calendar

    lateinit var reservas: reservas


    var intensidad: String = ""
    var hora: String = ""


    internal lateinit var databaseerupciones: FirebaseDatabase
    internal lateinit var myRef: DatabaseReference
    var ct: Int = 0
    var dia: String =""
    var mes: String =""
    var valint: Int = 0
    lateinit var valtostring: String
    var anio: String = ""
    @SuppressLint("ResourceAsColor")




    lateinit var  dia2: String
    lateinit var mes2: String
    var valint2: Int = 0
    lateinit var valtostring2: String
    lateinit var anio2: String

      var mesarreglado: String = ""


    lateinit var local_val:String
    lateinit var distrito_val:String
    lateinit var nombre_val:String
    lateinit var canchaid:String
    lateinit var localid:String

    lateinit var datetime: String

    private var mDrawerBlock: RelativeLayout? = null

    internal var toolbar: Toolbar? = null
    private var mDrawerTitle: CharSequence? = null
    private var mTitle: CharSequence? = null
    var mDrawerToggle: android.support.v7.app.ActionBarDrawerToggle? = null
    internal lateinit var sliderz: ImageView
    internal    var mNavigationDrawerItemTitles: Array<String>? = null
    private  var mDrawerLayout: DrawerLayout? = null

     lateinit var usuario: TextView


    lateinit var calendario: MaterialCalendarView







    lateinit var texto_horario1: String

    lateinit var texto_horario2: String

    lateinit var texto_horario3: String


    lateinit var texto_horario4: String
    lateinit var texto_horario5: String
    lateinit var texto_horario6: String
    lateinit var texto_horario7: String
    lateinit var texto_horario8: String
    lateinit var texto_horario9: String
    lateinit var texto_horario10: String
    lateinit var texto_horario11: String
    lateinit var texto_horario12: String

    lateinit var texto_horario13: String
    lateinit var texto_horario14: String
    lateinit var texto_horario15: String
    lateinit var texto_horario16: String
    lateinit var texto_horario17: String
    lateinit var texto_horario18: String


    lateinit var datestringx:String
    lateinit var dateformatx:SimpleDateFormat
    lateinit var convertdate:Date




    lateinit var dia_tomado: String
    lateinit var mes_tomado: String
    lateinit var anio_tomado: String


    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_elegirhoraa)

        val Registered: Boolean?
        val valor1: String?
        val valor2: String?


        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        Registered = sharedPref.getBoolean("Registered", false)
        valor1 = sharedPref.getString("Username", "")
        valor2 = sharedPref.getString("Password", "")


        usuario = findViewById(R.id.usuario) as TextView
        usuario.text = valor1

        val i = this.intent
         local_val = i.extras!!.getString("LOCAL")
         distrito_val = i.extras!!.getString("DISTRITO")
         nombre_val = i.extras!!.getString("NOMBRE")


        canchaid = i.extras!!.getString("CANCHAID")
        localid = i.extras!!.getString("LOCALID")


        val local: TextView = findViewById(R.id.local) as TextView
        //val cancha: TextView = findViewById(R.id.cancha) as TextView
        val distrito: TextView = findViewById(R.id.distrito) as TextView


       // val colorvalue = ContextCompat.getColor(cont)

        local.text = "Local: " + local_val + " / " + nombre_val
        //cancha.text = "Nombre: " + nombre_val
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
        var horario13: Button = findViewById(R.id.horario13) as Button
        var horario14: Button = findViewById(R.id.horario14) as Button
        var horario15: Button = findViewById(R.id.horario15) as Button
        var horario16: Button = findViewById(R.id.horario16) as Button
        var horario17: Button = findViewById(R.id.horario17) as Button
        var horario18: Button = findViewById(R.id.horario18) as Button

        texto_horario1 = horario1.text.toString()
        texto_horario2 = horario2.text.toString()
        texto_horario3 = horario3.text.toString()
        texto_horario4 = horario4.text.toString()
        texto_horario5 = horario5.text.toString()
        texto_horario6 = horario6.text.toString()
        texto_horario7 = horario7.text.toString()
        texto_horario8 = horario8.text.toString()
        texto_horario9 = horario9.text.toString()
        texto_horario10 = horario10.text.toString()
        texto_horario11 = horario11.text.toString()
        texto_horario12 = horario12.text.toString()

        texto_horario13 = horario13.text.toString()
        texto_horario14 = horario14.text.toString()
        texto_horario15 = horario15.text.toString()
        texto_horario16 = horario16.text.toString()
        texto_horario17 = horario17.text.toString()
        texto_horario18 = horario18.text.toString()


        calendario      = findViewById(R.id.calendarView) as MaterialCalendarView

          calendar     = Calendar.getInstance()

          calendar2  = Calendar.getInstance()


       // Toast.makeText(this, calendar2.toString() , Toast.LENGTH_LONG).show()

        calendario.setDateSelected(calendar.time, true)


     //   calendario.setDateSelected(calendar.time, true)



       var simplef:SimpleDateFormat  = SimpleDateFormat("dd-MM-yyyy")
       datetime    = simplef.format(calendar2.time)
/*

        calendar.time.day

        dia = calendar.time.day.toString()
        mes = calendar.time.month.toString()
        valint = mes.toInt() + 1
        valtostring = valint.toString()
        anio = calendar.time.year.toString()

        */
        /*
        calendar2.time.day

        dia = calendar2.time.day.toString()
        mes = calendar2.time.month.toString()
        valint = mes.toInt() + 1
        valtostring = valint.toString()
        anio = calendar2.time.year.toString()
*/
        if(calendar == calendar2){

            dia2 = calendario.selectedDate.day.toString()
            mes2 = calendario.selectedDate.month.toString()
            valint2 = mes2.toInt() + 1
            valtostring2 = valint2.toString()
            anio2 = calendario.selectedDate.year.toString()

            /*
            calendar.time.day

            dia = calendar.time.day.toString()
            mes = calendar.time.month.toString()
            valint = mes.toInt() + 1
            valtostring = valint.toString()
            anio = calendar.time.year.toString()
*/

            Toast.makeText(this, dia2+mes2+anio2+calendar , Toast.LENGTH_LONG).show()




            horario1.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))

            horario2.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario3.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario4.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario5.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario6.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario7.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario8.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario9.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario10.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario11.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario12.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario13.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario14.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario15.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario16.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario17.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario18.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))

/*
            ///  horario1.backgroundTintMode(Color.parseColor("#00cf7c"))
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
*/
            Verificardisponibilidad(texto_horario1, dia2+valtostring2+anio2)

            Verificardisponibilidad2(texto_horario2, dia2+valtostring2+anio2)
            Verificardisponibilidad3(texto_horario3, dia2+valtostring2+anio2)

            Verificardisponibilidad4(texto_horario4, dia2+valtostring2+anio2)
            Verificardisponibilidad5(texto_horario5, dia2+valtostring2+anio2)
            Verificardisponibilidad6(texto_horario6, dia2+valtostring2+anio2)
            Verificardisponibilidad7(texto_horario7, dia2+valtostring2+anio2)
            Verificardisponibilidad8(texto_horario8, dia2+valtostring2+anio2)
            Verificardisponibilidad9(texto_horario9, dia2+valtostring2+anio2)
            Verificardisponibilidad10(texto_horario10, dia2+valtostring2+anio2)
            Verificardisponibilidad11(texto_horario11, dia2+valtostring2+anio2)
            Verificardisponibilidad12(texto_horario12, dia2+valtostring2+anio2)

            Verificardisponibilidad13(texto_horario13, dia2+valtostring2+anio2)
            Verificardisponibilidad14(texto_horario14, dia2+valtostring2+anio2)
            Verificardisponibilidad15(texto_horario15, dia2+valtostring2+anio2)
            Verificardisponibilidad16(texto_horario16, dia2+valtostring2+anio2)
            Verificardisponibilidad17(texto_horario17, dia2+valtostring2+anio2)
            Verificardisponibilidad18(texto_horario18, dia2+valtostring2+anio2)

        }


        calendario.setOnDateChangedListener { widget, date, selected ->
            Toast.makeText(this, date.day.toString() , Toast.LENGTH_LONG).show()

            dia = date.day.toString()
            mes = date.month.toString()
            valint = mes.toInt() + 1
            valtostring = valint.toString()
            anio = date.year.toString()


            horario1.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))

            horario2.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario3.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario4.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario5.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario6.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario7.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario8.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario9.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario10.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario11.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario12.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario13.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario14.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario15.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario16.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario17.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            horario18.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))

            // horario1.setBackgroundColor(Color.parseColor("#00cf7c"))

            /*
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
*/
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





            Verificardisponibilidad13(texto_horario13, dia2+valtostring2+anio2)
            Verificardisponibilidad14(texto_horario14, dia2+valtostring2+anio2)
            Verificardisponibilidad15(texto_horario15, dia2+valtostring2+anio2)
            Verificardisponibilidad16(texto_horario16, dia2+valtostring2+anio2)
            Verificardisponibilidad17(texto_horario17, dia2+valtostring2+anio2)
            Verificardisponibilidad18(texto_horario18, dia2+valtostring2+anio2)

        }


        horario1.setOnClickListener {
            valorhorario = horario1.getText() as String;
            var de:String =  horario1.backgroundTintList.defaultColor.toString()
            Toast.makeText(this, de, Toast.LENGTH_LONG).show()

            if(dia!="" && mes!="" &&  anio!=""){
                 dia_tomado = dia
                 mes_tomado = valtostring
                 anio_tomado = anio
            }
            else{
                dia_tomado = dia2
                mes_tomado = valtostring2
                anio_tomado = anio2
            }
            Toast.makeText(this, "fecha tomada" + dia_tomado+mes_tomado+anio_tomado, Toast.LENGTH_LONG).show()

            if(de == "-16720798"){
                showNewNameDialog(valorhorario, mes_tomado , dia_tomado, anio_tomado, "horario1")
            }
            else{
                Toast.makeText(this, "El horario ya esta reservado", Toast.LENGTH_LONG).show()
            }
        }

        horario2.setOnClickListener {
            valorhorario = horario2.getText() as String;
            var de:String =  horario2.backgroundTintList.defaultColor.toString()
            Toast.makeText(this, de, Toast.LENGTH_LONG).show()

            if(dia!="" && mes!="" &&  anio!=""){
                dia_tomado = dia
                mes_tomado = valtostring
                anio_tomado = anio
            }
            else{
                dia_tomado = dia2
                mes_tomado = valtostring2
                anio_tomado = anio2
            }

            Toast.makeText(this, "fecha tomada" + dia_tomado+mes_tomado+anio_tomado, Toast.LENGTH_LONG).show()

            if(de == "-16720798"){
                showNewNameDialog(valorhorario, mes_tomado , dia_tomado, anio_tomado, "horario2")
            }
            else{
                Toast.makeText(this, "El horario ya esta reservado", Toast.LENGTH_LONG).show()
            }
        }

        horario3.setOnClickListener {
            valorhorario = horario3.getText() as String;
            //Toast.makeText(this, valorhorario, Toast.LENGTH_LONG).show();
            var de:String =  horario3.backgroundTintList.defaultColor.toString()
            Toast.makeText(this, de, Toast.LENGTH_LONG).show()


            if(dia!="" && mes!="" &&  anio!=""){
                dia_tomado = dia
                mes_tomado = valtostring
                anio_tomado = anio
            }
            else{
                dia_tomado = dia2
                mes_tomado = valtostring2
                anio_tomado = anio2
            }
            Toast.makeText(this, "fecha tomada" + dia_tomado+mes_tomado+anio_tomado, Toast.LENGTH_LONG).show()

            if(de == "-16720798"){
                showNewNameDialog(valorhorario,mes_tomado , dia_tomado, anio_tomado, "horario3")
            }
            else{
                Toast.makeText(this, "El horario ya esta reservado", Toast.LENGTH_LONG).show()
            }
        }

        /*

        horario4.setOnClickListener {
            valorhorario = horario4.getText() as String;
            //Toast.makeText(this, valorhorario, Toast.LENGTH_LONG).show();
            var de:String =  horario4.backgroundTintList.defaultColor.toString()
            Toast.makeText(this, de, Toast.LENGTH_LONG).show()
            if(de == "-16720798"){
                showNewNameDialog(valorhorario, dia+"/"+mes+"/"+anio, "horario3")
            }
            else{
                Toast.makeText(this, "El horario ya esta reservado", Toast.LENGTH_LONG).show()
            }
        }


        horario5.setOnClickListener {
            valorhorario = horario5.getText() as String;
            //Toast.makeText(this, valorhorario, Toast.LENGTH_LONG).show();
            var de:String =  horario5.backgroundTintList.defaultColor.toString()
            Toast.makeText(this, de, Toast.LENGTH_LONG).show()
            if(de == "-16720798"){
                showNewNameDialog(valorhorario, dia+"/"+mes+"/"+anio, "horario3")
            }
            else{
                Toast.makeText(this, "El horario ya esta reservado", Toast.LENGTH_LONG).show()
            }
        }





        horario6.setOnClickListener {
            valorhorario = horario6.getText() as String;
            //Toast.makeText(this, valorhorario, Toast.LENGTH_LONG).show();
            var de:String =  horario6.backgroundTintList.defaultColor.toString()
            Toast.makeText(this, de, Toast.LENGTH_LONG).show()
            if(de == "-16720798"){
                showNewNameDialog(valorhorario, dia+"/"+mes+"/"+anio, "horario3")
            }
            else{
                Toast.makeText(this, "El horario ya esta reservado", Toast.LENGTH_LONG).show()
            }
        }


        horario7.setOnClickListener {
            valorhorario = horario7.getText() as String;
            //Toast.makeText(this, valorhorario, Toast.LENGTH_LONG).show();
            var de:String =  horario7.backgroundTintList.defaultColor.toString()
            Toast.makeText(this, de, Toast.LENGTH_LONG).show()
            if(de == "-16720798"){
                showNewNameDialog(valorhorario, dia+"/"+mes+"/"+anio, "horario3")
            }
            else{
                Toast.makeText(this, "El horario ya esta reservado", Toast.LENGTH_LONG).show()
            }
        }


        horario8.setOnClickListener {
            valorhorario = horario8.getText() as String;
            //Toast.makeText(this, valorhorario, Toast.LENGTH_LONG).show();
            var de:String =  horario8.backgroundTintList.defaultColor.toString()
            Toast.makeText(this, de, Toast.LENGTH_LONG).show()
            if(de == "-16720798"){
                showNewNameDialog(valorhorario, dia+"/"+mes+"/"+anio, "horario3")
            }
            else{
                Toast.makeText(this, "El horario ya esta reservado", Toast.LENGTH_LONG).show()
            }
        }


        horario9.setOnClickListener {
            valorhorario = horario9.getText() as String;
            //Toast.makeText(this, valorhorario, Toast.LENGTH_LONG).show();
            var de:String =  horario9.backgroundTintList.defaultColor.toString()
            Toast.makeText(this, de, Toast.LENGTH_LONG).show()
            if(de == "-16720798"){
                showNewNameDialog(valorhorario, dia+"/"+mes+"/"+anio, "horario3")
            }
            else{
                Toast.makeText(this, "El horario ya esta reservado", Toast.LENGTH_LONG).show()
            }
        }


        horario10.setOnClickListener {
            valorhorario = horario10.getText() as String;
            //Toast.makeText(this, valorhorario, Toast.LENGTH_LONG).show();
            var de:String =  horario10.backgroundTintList.defaultColor.toString()
            Toast.makeText(this, de, Toast.LENGTH_LONG).show()
            if(de == "-16720798"){
                showNewNameDialog(valorhorario, dia+"/"+mes+"/"+anio, "horario3")
            }
            else{
                Toast.makeText(this, "El horario ya esta reservado", Toast.LENGTH_LONG).show()
            }
        }

        horario11.setOnClickListener {
            valorhorario = horario11.getText() as String;
            //Toast.makeText(this, valorhorario, Toast.LENGTH_LONG).show();
            var de:String =  horario11.backgroundTintList.defaultColor.toString()
            Toast.makeText(this, de, Toast.LENGTH_LONG).show()
            if(de == "-16720798"){
                showNewNameDialog(valorhorario, dia+"/"+mes+"/"+anio, "horario3")
            }
            else{
                Toast.makeText(this, "El horario ya esta reservado", Toast.LENGTH_LONG).show()
            }
        }


        horario12.setOnClickListener {
            valorhorario = horario12.getText() as String;
            //Toast.makeText(this, valorhorario, Toast.LENGTH_LONG).show();
            var de:String =  horario12.backgroundTintList.defaultColor.toString()
            Toast.makeText(this, de, Toast.LENGTH_LONG).show()
            if(de == "-16720798"){
                showNewNameDialog(valorhorario, dia+"/"+mes+"/"+anio, "horario3")
            }
            else{
                Toast.makeText(this, "El horario ya esta reservado", Toast.LENGTH_LONG).show()
            }
        }



        horario13.setOnClickListener {
            valorhorario = horario13.getText() as String;
            //Toast.makeText(this, valorhorario, Toast.LENGTH_LONG).show();
            var de:String =  horario13.backgroundTintList.defaultColor.toString()
            Toast.makeText(this, de, Toast.LENGTH_LONG).show()
            if(de == "-16720798"){
                showNewNameDialog(valorhorario, dia+"/"+mes+"/"+anio, "horario3")
            }
            else{
                Toast.makeText(this, "El horario ya esta reservado", Toast.LENGTH_LONG).show()
            }
        }


        horario14.setOnClickListener {
            valorhorario = horario14.getText() as String;
            //Toast.makeText(this, valorhorario, Toast.LENGTH_LONG).show();
            var de:String =  horario14.backgroundTintList.defaultColor.toString()
            Toast.makeText(this, de, Toast.LENGTH_LONG).show()
            if(de == "-16720798"){
                showNewNameDialog(valorhorario, dia+"/"+mes+"/"+anio, "horario3")
            }
            else{
                Toast.makeText(this, "El horario ya esta reservado", Toast.LENGTH_LONG).show()
            }
        }



        horario15.setOnClickListener {
            valorhorario = horario15.getText() as String;
            //Toast.makeText(this, valorhorario, Toast.LENGTH_LONG).show();
            var de:String =  horario15.backgroundTintList.defaultColor.toString()
            Toast.makeText(this, de, Toast.LENGTH_LONG).show()
            if(de == "-16720798"){
                showNewNameDialog(valorhorario, dia+"/"+mes+"/"+anio, "horario3")
            }
            else{
                Toast.makeText(this, "El horario ya esta reservado", Toast.LENGTH_LONG).show()
            }
        }



        horario16.setOnClickListener {
            valorhorario = horario16.getText() as String;
            //Toast.makeText(this, valorhorario, Toast.LENGTH_LONG).show();
            var de:String =  horario16.backgroundTintList.defaultColor.toString()
            Toast.makeText(this, de, Toast.LENGTH_LONG).show()
            if(de == "-16720798"){
                showNewNameDialog(valorhorario, dia+"/"+mes+"/"+anio, "horario3")
            }
            else{
                Toast.makeText(this, "El horario ya esta reservado", Toast.LENGTH_LONG).show()
            }
        }




        horario17.setOnClickListener {
            valorhorario = horario17.getText() as String;
            //Toast.makeText(this, valorhorario, Toast.LENGTH_LONG).show();
            var de:String =  horario17.backgroundTintList.defaultColor.toString()
            Toast.makeText(this, de, Toast.LENGTH_LONG).show()
            if(de == "-16720798"){
                showNewNameDialog(valorhorario, dia+"/"+mes+"/"+anio, "horario3")
            }
            else{
                Toast.makeText(this, "El horario ya esta reservado", Toast.LENGTH_LONG).show()
            }
        }



        horario18.setOnClickListener {
            valorhorario = horario18.getText() as String;
            //Toast.makeText(this, valorhorario, Toast.LENGTH_LONG).show();
            var de:String =  horario18.backgroundTintList.defaultColor.toString()
            Toast.makeText(this, de, Toast.LENGTH_LONG).show()
            if(de == "-16720798"){
                showNewNameDialog(valorhorario, dia+"/"+mes+"/"+anio, "horario3")
            }
            else{
                Toast.makeText(this, "El horario ya esta reservado", Toast.LENGTH_LONG).show()
            }
        }
        */


        sliderz = findViewById<View>(R.id.sliderz) as ImageView
        mDrawerTitle = title
        mTitle = mDrawerTitle
        mNavigationDrawerItemTitles = resources.getStringArray(R.array.navigation_drawer_items_array)
        mDrawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        mDrawerBlock = findViewById<View>(R.id.mDrawerBlock) as RelativeLayout
        mDrawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        mDrawerLayout!!.setDrawerListener(mDrawerToggle)
        setupDrawerToggle()
        sliderz.setOnClickListener { mDrawerLayout!!.openDrawer(GravityCompat.START) }

    }







    @SuppressLint("SetTextI18n")
    fun showNewNameDialog(horarioval: String, mes_value:String,dia_value:String,anio_value:String, horariox: String) {

       // Toast.makeText(this, fechaval, Toast.LENGTH_LONG).show()

        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dislogo, null)
        dialogBuilder.setView(dialogView)

        val horario = dialogView.findViewById<View>(R.id.horario) as TextView
        val fecha = dialogView.findViewById<View>(R.id.fecha) as TextView
        val local = dialogView.findViewById<View>(R.id.local) as TextView
        val cancha = dialogView.findViewById<View>(R.id.cancha) as TextView
        val distrito = dialogView.findViewById<View>(R.id.distrito) as TextView


        val botonpagar = dialogView.findViewById<View>(R.id.pagarahora) as Button
        val botonsoloreservar = dialogView.findViewById<View>(R.id.soloreservar) as Button


        horario.text = horarioval
        local.text = local_val

        cancha.text = nombre_val
        distrito.text = distrito_val

/*
        dia2 = calendario.selectedDate.day.toString()
        mes2 = calendario.selectedDate.month.toString()
        valint2 = mes.toInt()
        valtostring2 = valint2.toString()
        anio2 = calendario.selectedDate.year.toString()
*/
        var valk:String =  dia+mes+anio
        if(valk.length < 4){
            fecha.text = dia2+mes2+anio2
        }
        else{
            fecha.text =  dia+mes+anio
        }


        botonpagar.setOnClickListener {

            if(horariox == "horario1"){
               horario1.setBackgroundColor(Color.parseColor("#d62e2e"))
            }
            else if (horariox == "horario2"){
                horario2.setBackgroundColor(Color.parseColor("#d62e2e"))
            }
            else if (horariox == "horario3"){
                horario3.setBackgroundColor(Color.parseColor("#d62e2e"))
            }


            //  horariox.setBackgroundColor(Color.parseColor("#d62e2e"))

            var ref = FirebaseDatabase.getInstance().getReference("reservas").child("reportes")
            var reporteid = ref.push().key


            fecha.text =  dia_value+mes_value+anio_value
            reservas    =   reservas(dia_value+mes_value+anio_value , horarioval,distrito_val,distrito_val+"_"+distrito_val,distrito_val,dia_value+mes_value+anio_value+"_"+horarioval,nombre_val,local_val)


                datestringx = mes_value+"/"+dia_value+"/"+anio_value
                dateformatx = SimpleDateFormat("mm/dd/yyyy")
                convertdate = Date()
                convertdate = dateformatx.parse(datestringx)


                val intent = Intent(this,Pagarreserva::class.java)
                intent.putExtra("FECHA", anio_value+"/"+mes_value+"/"+dia_value)
                intent.putExtra("FECHARESERVA", anio_value+"-"+mes_value+"-"+dia_value+" "+"00:00:00")


                intent.putExtra("HORA", horarioval.toString())
                intent.putExtra("DEPARTAMENTO", distrito_val)
                intent.putExtra("DEPARTAMENTO_DISTRITO",distrito_val+"_"+distrito_val)
                intent.putExtra("DISTRITO",distrito_val)
                intent.putExtra("FECHA_HORA",dia_value+mes_value+anio_value+"_"+horarioval)
                intent.putExtra("CANCHA_NOMBRE", nombre_val)
                intent.putExtra("LOCAL", local_val)

                intent.putExtra("LOCALID", localid)
                intent.putExtra("CANCHAID", canchaid)

                startActivity(intent)

            ref.child(reporteid).setValue(reservas).addOnCompleteListener {
            Toast.makeText(this, "xxx"+anio_value+"-"+mes_value+"-"+dia_value+" "+"00:00:00", Toast.LENGTH_LONG).show();
            }
        }


        botonsoloreservar.setOnClickListener {

            if(horariox == "horario1"){
                horario1.setBackgroundColor(Color.parseColor("#d62e2e"))
            }
            else if (horariox == "horario2"){
                horario2.setBackgroundColor(Color.parseColor("#d62e2e"))
            }
            else if (horariox == "horario3"){
                horario3.setBackgroundColor(Color.parseColor("#d62e2e"))
            }


            //  horariox.setBackgroundColor(Color.parseColor("#d62e2e"))

            var ref = FirebaseDatabase.getInstance().getReference("reservas").child("reportes")
            var reporteid = ref.push().key


            fecha.text =  dia_value+mes_value+anio_value
            reservas    =   reservas(dia_value+mes_value+anio_value , horarioval,distrito_val,distrito_val+"_"+distrito_val,distrito_val,dia_value+mes_value+anio_value+"_"+horarioval,nombre_val,local_val)


            datestringx = mes_value+"/"+dia_value+"/"+anio_value
            dateformatx = SimpleDateFormat("mm/dd/yyyy")
            convertdate = Date()
            convertdate = dateformatx.parse(datestringx)


            val intent = Intent(this,Soloreservar::class.java)
            intent.putExtra("FECHA", anio_value+"/"+mes_value+"/"+dia_value)
            intent.putExtra("FECHARESERVA", anio_value+"-"+mes_value+"-"+dia_value+" "+"00:00:00")


            intent.putExtra("HORA", horarioval.toString())
            intent.putExtra("DEPARTAMENTO", distrito_val)
            intent.putExtra("DEPARTAMENTO_DISTRITO",distrito_val+"_"+distrito_val)
            intent.putExtra("DISTRITO",distrito_val)
            intent.putExtra("FECHA_HORA",dia_value+mes_value+anio_value+"_"+horarioval)
            intent.putExtra("CANCHA_NOMBRE", nombre_val)
            intent.putExtra("LOCAL", local_val)

            intent.putExtra("LOCALID", localid)
            intent.putExtra("CANCHAID", canchaid)

            startActivity(intent)

            ref.child(reporteid).setValue(reservas).addOnCompleteListener {
                Toast.makeText(this, "xxx"+anio_value+"-"+mes_value+"-"+dia_value+" "+"00:00:00", Toast.LENGTH_LONG).show();
            }
        }



        val b = dialogBuilder.create()
        b.show()
    }







    fun Verificardisponibilidad(valor1:String, valor2:String): String? {

        Toast.makeText(this, "valor : " + valor2+"_"+valor1, Toast.LENGTH_LONG).show()


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
           // horario1.setBackgroundColor(Color.parseColor("#00cf7c"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario1.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.rojo)))
            }


        }
        else{
           // horario1.setBackgroundColor(Color.parseColor("#d62e2e"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario1.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.rojo)))
            }


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
           // horario2.setBackgroundColor(Color.parseColor("#00cf7c"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario2.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            }

        }
        else{
           // horario2.setBackgroundColor(Color.parseColor("#d62e2e"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario2.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.rojo)))
            }

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
           // horario3.setBackgroundColor(Color.parseColor("#00cf7c"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario3.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            }

        }
        else{
            //horario3.setBackgroundColor(Color.parseColor("#d62e2e"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario3.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.rojo)))
            }

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
           // horario4.setBackgroundColor(Color.parseColor("#00cf7c"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario4.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            }

        }
        else{
          //  horario4.setBackgroundColor(Color.parseColor("#d62e2e"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario4.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.rojo)))
            }

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
            //horario5.setBackgroundColor(Color.parseColor("#00cf7c"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario5.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            }

        }
        else{
            //horario5.setBackgroundColor(Color.parseColor("#d62e2e"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario5.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.rojo)))
            }

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
            //horario6.setBackgroundColor(Color.parseColor("#00cf7c"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario6.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            }

        }
        else{
            //horario6.setBackgroundColor(Color.parseColor("#d62e2e"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario6.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.rojo)))
            }

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
            //horario7.setBackgroundColor(Color.parseColor("#00cf7c"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario7.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            }

        }
        else{
           // horario7.setBackgroundColor(Color.parseColor("#d62e2e"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario7.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.rojo)))
            }

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
            //horario8.setBackgroundColor(Color.parseColor("#00cf7c"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario8.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            }

        }
        else{
           // horario8.setBackgroundColor(Color.parseColor("#d62e2e"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario8.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.rojo)))
            }

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
            //horario9.setBackgroundColor(Color.parseColor("#00cf7c"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario9.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            }

        }
        else{
            //horario9.setBackgroundColor(Color.parseColor("#d62e2e"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario9.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.rojo)))
            }

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
            //horario10.setBackgroundColor(Color.parseColor("#00cf7c"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario10.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            }

        }
        else{
           // horario10.setBackgroundColor(Color.parseColor("#d62e2e"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario10.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.rojo)))
            }

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
           // horario11.setBackgroundColor(Color.parseColor("#00cf7c"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario11.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            }

        }
        else{
            //horario11.setBackgroundColor(Color.parseColor("#d62e2e"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario11.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.rojo)))
            }

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
        intensidad = prefs.getString("incategh,jhoriak", "")
        hora = prefs.getString("inreferenciak", "")
        Toast.makeText(this, intensidad + hora, Toast.LENGTH_LONG).show()

        if (intensidad == null){
          //  horario12.setBackgroundColor(Color.parseColor("#00cf7c"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario12.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            }

        }
        else{
            //horario12.setBackgroundColor(Color.parseColor("#d62e2e"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario12.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.rojo)))
            }

        }
    }








    fun Verificardisponibilidad13(valor1:String, valor2:String): String? {

        var mFirebaseDatabase2: DatabaseReference? = null
        FirebaseDatabase.getInstance()
        val database2 = FirebaseDatabase.getInstance()
        mFirebaseDatabase2 = database2.getReference("reservas").child("reportes")
        mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1).limitToFirst(1).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                val sreporte4 = dataSnapshot.getValue<Reservas>(Reservas::class.java)
                guardar_prefkk13(sreporte4)
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

    fun guardar_prefkk13(sreporte: Reservas?) {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        var editor = prefs.edit()
        editor.putString("incategoriak", sreporte!!.getFecha())
        editor.putString("inreferenciak", sreporte!!.getHora())

        editor.apply()
        mostrar_ult_sismo13()
    }

    @SuppressLint("ResourceAsColor")
    fun mostrar_ult_sismo13() {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        intensidad = prefs.getString("incategh,jhoriak", "")
        hora = prefs.getString("inreferenciak", "")
        Toast.makeText(this, intensidad + hora, Toast.LENGTH_LONG).show()

        if (intensidad == null){
            //  horario12.setBackgroundColor(Color.parseColor("#00cf7c"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario13.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            }

        }
        else{
            //horario12.setBackgroundColor(Color.parseColor("#d62e2e"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario13.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.rojo)))
            }

        }
    }











    fun Verificardisponibilidad14(valor1:String, valor2:String): String? {

        var mFirebaseDatabase2: DatabaseReference? = null
        FirebaseDatabase.getInstance()
        val database2 = FirebaseDatabase.getInstance()
        mFirebaseDatabase2 = database2.getReference("reservas").child("reportes")
        mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1).limitToFirst(1).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                val sreporte4 = dataSnapshot.getValue<Reservas>(Reservas::class.java)
                guardar_prefkk14(sreporte4)
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

    fun guardar_prefkk14(sreporte: Reservas?) {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        var editor = prefs.edit()
        editor.putString("incategoriak", sreporte!!.getFecha())
        editor.putString("inreferenciak", sreporte!!.getHora())

        editor.apply()
        mostrar_ult_sismo14()
    }

    @SuppressLint("ResourceAsColor")
    fun mostrar_ult_sismo14() {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        intensidad = prefs.getString("incategh,jhoriak", "")
        hora = prefs.getString("inreferenciak", "")
        Toast.makeText(this, intensidad + hora, Toast.LENGTH_LONG).show()

        if (intensidad == null){
            //  horario12.setBackgroundColor(Color.parseColor("#00cf7c"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario14.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            }

        }
        else{
            //horario12.setBackgroundColor(Color.parseColor("#d62e2e"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario14.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.rojo)))
            }

        }
    }








    fun Verificardisponibilidad15(valor1:String, valor2:String): String? {

        var mFirebaseDatabase2: DatabaseReference? = null
        FirebaseDatabase.getInstance()
        val database2 = FirebaseDatabase.getInstance()
        mFirebaseDatabase2 = database2.getReference("reservas").child("reportes")
        mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1).limitToFirst(1).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                val sreporte4 = dataSnapshot.getValue<Reservas>(Reservas::class.java)
                guardar_prefkk15(sreporte4)
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

    fun guardar_prefkk15(sreporte: Reservas?) {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        var editor = prefs.edit()
        editor.putString("incategoriak", sreporte!!.getFecha())
        editor.putString("inreferenciak", sreporte!!.getHora())

        editor.apply()
        mostrar_ult_sismo15()
    }

    @SuppressLint("ResourceAsColor")
    fun mostrar_ult_sismo15() {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        intensidad = prefs.getString("incategh,jhoriak", "")
        hora = prefs.getString("inreferenciak", "")
        Toast.makeText(this, intensidad + hora, Toast.LENGTH_LONG).show()

        if (intensidad == null){
            //  horario12.setBackgroundColor(Color.parseColor("#00cf7c"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario15.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            }

        }
        else{
            //horario12.setBackgroundColor(Color.parseColor("#d62e2e"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario15.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.rojo)))
            }

        }
    }
















    fun Verificardisponibilidad16(valor1:String, valor2:String): String? {

        var mFirebaseDatabase2: DatabaseReference? = null
        FirebaseDatabase.getInstance()
        val database2 = FirebaseDatabase.getInstance()
        mFirebaseDatabase2 = database2.getReference("reservas").child("reportes")
        mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1).limitToFirst(1).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                val sreporte4 = dataSnapshot.getValue<Reservas>(Reservas::class.java)
                guardar_prefkk16(sreporte4)
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

    fun guardar_prefkk16(sreporte: Reservas?) {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        var editor = prefs.edit()
        editor.putString("incategoriak", sreporte!!.getFecha())
        editor.putString("inreferenciak", sreporte!!.getHora())

        editor.apply()
        mostrar_ult_sismo16()
    }

    @SuppressLint("ResourceAsColor")
    fun mostrar_ult_sismo16() {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        intensidad = prefs.getString("incategh,jhoriak", "")
        hora = prefs.getString("inreferenciak", "")
        Toast.makeText(this, intensidad + hora, Toast.LENGTH_LONG).show()

        if (intensidad == null){
            //  horario12.setBackgroundColor(Color.parseColor("#00cf7c"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario16.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            }

        }
        else{
            //horario12.setBackgroundColor(Color.parseColor("#d62e2e"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario16.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.rojo)))
            }

        }
    }






    fun Verificardisponibilidad17(valor1:String, valor2:String): String? {

        var mFirebaseDatabase2: DatabaseReference? = null
        FirebaseDatabase.getInstance()
        val database2 = FirebaseDatabase.getInstance()
        mFirebaseDatabase2 = database2.getReference("reservas").child("reportes")
        mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1).limitToFirst(1).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                val sreporte4 = dataSnapshot.getValue<Reservas>(Reservas::class.java)
                guardar_prefkk17(sreporte4)
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

    fun guardar_prefkk17(sreporte: Reservas?) {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        var editor = prefs.edit()
        editor.putString("incategoriak", sreporte!!.getFecha())
        editor.putString("inreferenciak", sreporte!!.getHora())

        editor.apply()
        mostrar_ult_sismo17()
    }

    @SuppressLint("ResourceAsColor")
    fun mostrar_ult_sismo17() {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        intensidad = prefs.getString("incategh,jhoriak", "")
        hora = prefs.getString("inreferenciak", "")
        Toast.makeText(this, intensidad + hora, Toast.LENGTH_LONG).show()

        if (intensidad == null){
            //  horario12.setBackgroundColor(Color.parseColor("#00cf7c"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario17.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            }

        }
        else{
            //horario12.setBackgroundColor(Color.parseColor("#d62e2e"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario17.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.rojo)))
            }

        }
    }











    fun Verificardisponibilidad18(valor1:String, valor2:String): String? {

        var mFirebaseDatabase2: DatabaseReference? = null
        FirebaseDatabase.getInstance()
        val database2 = FirebaseDatabase.getInstance()
        mFirebaseDatabase2 = database2.getReference("reservas").child("reportes")
        mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1).limitToFirst(1).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                val sreporte4 = dataSnapshot.getValue<Reservas>(Reservas::class.java)
                guardar_prefkk18(sreporte4)
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

    fun guardar_prefkk18(sreporte: Reservas?) {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        var editor = prefs.edit()
        editor.putString("incategoriak", sreporte!!.getFecha())
        editor.putString("inreferenciak", sreporte!!.getHora())

        editor.apply()
        mostrar_ult_sismo18()
    }

    @SuppressLint("ResourceAsColor")
    fun mostrar_ult_sismo18() {
        var prefs = getSharedPreferences("ultsismok", Context.MODE_PRIVATE)
        intensidad = prefs.getString("incategh,jhoriak", "")
        hora = prefs.getString("inreferenciak", "")
        Toast.makeText(this, intensidad + hora, Toast.LENGTH_LONG).show()

        if (intensidad == null){
            //  horario12.setBackgroundColor(Color.parseColor("#00cf7c"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario18.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.verde)))
            }

        }
        else{
            //horario12.setBackgroundColor(Color.parseColor("#d62e2e"))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horario18.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.rojo)))
            }

        }
    }




    internal fun setupDrawerToggle() {
        mDrawerToggle = android.support.v7.app.ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name)
        mDrawerToggle!!.syncState()
    }

    internal fun actualizar_estado(){


        if(calendar == calendar2){
            dia2 = calendario.selectedDate.day.toString()
            mes2 = calendario.selectedDate.month.toString()
            valint2 = mes2.toInt()
            valtostring2 = valint2.toString()
            anio2 = calendario.selectedDate.year.toString()



            Toast.makeText(this, dia2+mes2+anio2 , Toast.LENGTH_LONG).show()


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

            Verificardisponibilidad(texto_horario1, dia2+valtostring2+anio2)
            Verificardisponibilidad2(texto_horario2, dia2+valtostring2+anio2)
            Verificardisponibilidad3(texto_horario3, dia2+valtostring2+anio2)

            Verificardisponibilidad4(texto_horario4, dia2+valtostring2+anio2)
            Verificardisponibilidad5(texto_horario5, dia2+valtostring2+anio2)
            Verificardisponibilidad6(texto_horario6, dia2+valtostring2+anio2)
            Verificardisponibilidad7(texto_horario7, dia2+valtostring2+anio2)
            Verificardisponibilidad8(texto_horario8, dia2+valtostring2+anio2)
            Verificardisponibilidad9(texto_horario9, dia2+valtostring2+anio2)
            Verificardisponibilidad10(texto_horario10, dia2+valtostring2+anio2)
            Verificardisponibilidad11(texto_horario11, dia2+valtostring2+anio2)
            Verificardisponibilidad12(texto_horario12, dia2+valtostring2+anio2)

        }


        else{

         }




    }


}
