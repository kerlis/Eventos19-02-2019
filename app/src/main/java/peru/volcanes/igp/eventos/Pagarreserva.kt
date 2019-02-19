package peru.volcanes.igp.eventos
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.*
import okhttp3.*
import java.io.IOException
class Pagarreserva : AppCompatActivity() {
    var fecha: String = ""
    var fechareserva: String = ""

    var hora: String = ""
    var departamento: String = ""
    var departamento_distrito: String = ""
    var distrito: String = ""
    var fecha_hora: String = ""
    var cancha_nombre: String = ""
    var local: String = ""

    var localid: String = ""
    var canchaid: String = ""
    var montox: String = ""
    lateinit var numeros:Spinner
    lateinit var valormonto:TextView

    private var mDrawerBlock: RelativeLayout? = null
    internal var toolbar: Toolbar? = null
    private var mDrawerTitle: CharSequence? = null
    private var mTitle: CharSequence? = null
    var mDrawerToggle: android.support.v7.app.ActionBarDrawerToggle? = null
    internal lateinit var sliderz: ImageView
    internal    var mNavigationDrawerItemTitles: Array<String>? = null
    private  var mDrawerLayout: DrawerLayout? = null

    lateinit var txt_nombretarjeta:TextView
    lateinit var txt_cardnumber:TextView
    lateinit var txt_month:TextView
    lateinit var txt_year:TextView
    lateinit var txt_cvv:TextView
    lateinit var txt_codigopostal:TextView
    lateinit var btn_pay:Button



    var val_nombretarjeta: String? = null
    var val_cardnumber:String? = null
    var val_month:String? = null
    var val_year:String? = null
    var valcvv:String? = null
    var val_codigopostal:String? = null

    private val client = OkHttpClient()
    private val BASE_URL = "http://arteypixel.com/appreserva/pagarreserva.php"
    private val BASE_URL2 = "https://arteypixel.com/appreserva/culqi-php-master/examples/01-create-token.php"

    internal var TAG_REGISTER: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagarreserva)

        txt_nombretarjeta = findViewById(R.id.txt_nombretarjeta)
        txt_cardnumber = findViewById(R.id.txt_cardnumber)
        txt_month = findViewById(R.id.txt_month)
        txt_year = findViewById(R.id.txt_year)
        txt_cvv = findViewById(R.id.txt_cvv)
        txt_codigopostal = findViewById(R.id.txt_codigopostal)
        btn_pay = findViewById(R.id.btn_pay)

        numeros = findViewById(R.id.horas)
        valormonto = findViewById(R.id.valormonto)






        //val spinner = findViewById(R.id.horas) as Spinner
// Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter.createFromResource(this,
                R.array.numeros, android.R.layout.simple_spinner_item)
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
// Apply the adapter to the spinner
        numeros.adapter = adapter



        val i = this.intent
        fecha = i.extras!!.getString("FECHA")
        fechareserva = i.extras!!.getString("FECHARESERVA")

        hora = i.extras!!.getString("HORA")
        departamento = i.extras!!.getString("DEPARTAMENTO")
        departamento_distrito = i.extras!!.getString("DEPARTAMENTO_DISTRITO")
        distrito = i.extras!!.getString("DISTRITO")
        fecha_hora = i.extras!!.getString("FECHA_HORA")
        cancha_nombre = i.extras!!.getString("CANCHA_NOMBRE")
        local = i.extras!!.getString("LOCAL")

        localid = i.extras!!.getString("LOCALID")
        canchaid = i.extras!!.getString("CANCHAID")




        //Toast.makeText(this, fecha  + " - " + hora + " - " + departamento + " - " + departamento_distrito + " - " + distrito + " - " + fecha_hora + " - " + cancha_nombre + " - "  + local, Toast.LENGTH_LONG).show()



    when(hora){

        "06:00-07:00" -> {
            valormonto.text = "80"
            montox = "80"

        }

        "07:00-08:00" -> {
            valormonto.text = "80"
            montox = "80"


        }

        "08:00-09:00" -> {
            valormonto.text = "80"
            montox = "80"

        }

        "09:00-10:00" -> {
            valormonto.text = "80"
            montox = "80"

        }

        "10:00-11:00" -> {
            valormonto.text = "80"
            montox = "80"

        }

        "11:00-12:00" -> {
            valormonto.text = "80"
            montox = "80"

        }

        "12:00-13:00" -> {
            valormonto.text = "80"
            montox = "80"

        }
        "13:00-14:00" -> {
            valormonto.text = "80"
            montox = "80"

        }
        "14:00-15:00" -> {
            valormonto.text = "80"
            montox = "80"

        }
        "15:00-16:00" -> {
            valormonto.text = "80"
            montox = "80"

        }
        "16:00-17:00" -> {
            valormonto.text = "80"
            montox = "80"

        }
        "17:00-18:00" -> {
            valormonto.text = "80"
            montox = "80"

        }
        "18:00-19:00" -> {
            valormonto.text = "100"
            montox = "100"

        }
        "19:00-20:00" -> {
            valormonto.text = "100"
            montox = "100"

        }
        "20:00-21:00" -> {
            valormonto.text = "100"
            montox = "100"

        }
        "21:00-22:00" -> {
            valormonto.text = "100"
            montox = "100"

        }
        "22:00-23:00" -> {
            valormonto.text = "100"
            montox = "100"

        }
        "23:00-24:00" -> {
            valormonto.text = "100"
            montox = "100"

        }

    }

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




        btn_pay.setOnClickListener {

            val_nombretarjeta = txt_nombretarjeta.text.toString()
            val_cardnumber = txt_cardnumber.text.toString()
            val_month = txt_month.text.toString()
            val_year = txt_year.text.toString()
            valcvv = txt_cvv.text.toString()
            val_codigopostal = txt_codigopostal.text.toString()

        //    Toast.makeText(this, val_nombretarjeta+"-"+val_cardnumber+"-"+val_month+"-"+val_year+"-"+valcvv+"-"+val_codigopostal, Toast.LENGTH_LONG).show()


            fecha = i.extras!!.getString("FECHA")
            fechareserva = i.extras!!.getString("FECHARESERVA")

            hora = i.extras!!.getString("HORA")
            departamento = i.extras!!.getString("DEPARTAMENTO")
            departamento_distrito = i.extras!!.getString("DEPARTAMENTO_DISTRITO")
            distrito = i.extras!!.getString("DISTRITO")
            fecha_hora = i.extras!!.getString("FECHA_HORA")
            cancha_nombre = i.extras!!.getString("CANCHA_NOMBRE")
            local = i.extras!!.getString("LOCAL")

            localid = i.extras!!.getString("LOCALID")
            canchaid = i.extras!!.getString("CANCHAID")

            if (val_nombretarjeta != "" && val_cardnumber != "" && val_month != "" && val_year != "" && valcvv != "" && val_codigopostal != ""){
                Toast.makeText(this, fecha , Toast.LENGTH_LONG).show()

                registrarpago(val_nombretarjeta!!, val_cardnumber!!, val_month!!, val_year!!, valcvv!!, val_codigopostal!!,fecha!!,hora!!,departamento!!,departamento_distrito!!,distrito!!,fecha_hora!!,cancha_nombre!!,local!!,fechareserva!!,localid!!,canchaid!!)

            }
            else{
                Toast.makeText(this, " no se puede pagar" , Toast.LENGTH_LONG).show()
            }
        }




    }


    internal fun setupDrawerToggle() {
        mDrawerToggle = android.support.v7.app.ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name)
        mDrawerToggle!!.syncState()
    }


    fun registrarpago(nombre_tarjeta: String,
                      numero_tarjeta: String,
                      mes_tarjeta:String,
                      anio_tarjeta:String,
                      ccv_tarjeta: String,
                      codigopostal_tarjeta: String,

                      fecha_reserva:String,
                      hora: String,
                      departamento: String,
                      departamento_distrito: String,
                      distrito: String,
                      fecha_hora: String,
                      cancha_nombre: String,
                      local: String,
                      fechareserva:String,

                      localid:String,
                      canchaid:String) {

        val Registered: Boolean?
        val usuario: String?
        val password: String?

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        Registered = sharedPref.getBoolean("Registered", false)
        usuario = sharedPref.getString("Username", "")
        password = sharedPref.getString("Password", "")



        val body = FormBody.Builder()
                .add("VALORESRESERVA", "$nombre_tarjeta&$numero_tarjeta&$mes_tarjeta&$anio_tarjeta&$ccv_tarjeta&$codigopostal_tarjeta&$fecha_reserva&$hora&$departamento&$departamento_distrito&$distrito&$fecha_hora&$cancha_nombre&$local&$usuario&$password&${valormonto.text}&$fechareserva&$localid&$canchaid")
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



                    } else {
                        ver2(resp)
                    }
*/
                    ver2(resp)
                    subirpago(val_nombretarjeta!!, val_cardnumber!!, val_month!!, val_year!!, valcvv!!)


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


    fun subirpago(nombre_tarjeta: String,
                      numero_tarjeta: String,
                      mes_tarjeta:String,
                      anio_tarjeta:String,
                      ccv_tarjeta: String) {

        val Registered: Boolean?
        val usuario: String?
        val password: String?

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        Registered = sharedPref.getBoolean("Registered", false)
        usuario = sharedPref.getString("Username", "")
        password = sharedPref.getString("Password", "")

        val body = FormBody.Builder()
                .add("VALORESTARJETA", "$nombre_tarjeta&$numero_tarjeta&$mes_tarjeta&$anio_tarjeta&$ccv_tarjeta")
                .build()
        val request = Request.Builder().url(BASE_URL2).post(body).build()
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
                    //ver2(resp)
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



    private fun ver2(dato: String) {

     //   val intent = Intent(this,Elegirhoraa::class.java)

        val intent7: Intent = Intent(this, Pagoexitoso::class.java)
       //
        // startActivity(intent7)
/*
        val sharedrf = PreferenceManager.getDefaultSharedPreferences(Pagarreserva.this)
        val editor = sharedrf.edit()
        editor.putBoolean("Registered", true)
        editor.putString("Username", resp)
        editor.putString("Password", resp)
        editor.apply()
*/

       // val intent = Intent(this, Pagoexitoso::class.java)
        intent7.putExtra("FECHA", fecha)
        intent7.putExtra("HORA", hora)
        intent7.putExtra("DEPARTAMENTO", departamento)
        intent7.putExtra("DEPARTAMENTO_DISTRITO", departamento_distrito)
        intent7.putExtra("DISTRITO", distrito)
        intent7.putExtra("FECHA_HORA",fecha_hora)
        intent7.putExtra("CANCHA_NOMBRE", cancha_nombre)
        intent7.putExtra("LOCAL", local)
        startActivity(intent7)



        runOnUiThread { Toast.makeText(applicationContext, "Status = $dato", Toast.LENGTH_LONG).show() }
    }


}
