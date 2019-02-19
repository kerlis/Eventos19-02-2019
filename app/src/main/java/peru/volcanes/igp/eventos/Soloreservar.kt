package peru.volcanes.igp.eventos

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

class Soloreservar : AppCompatActivity() {

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
    lateinit var numeros: Spinner
    lateinit var valormonto: TextView

    private var mDrawerBlock: RelativeLayout? = null
    internal var toolbar: Toolbar? = null
    private var mDrawerTitle: CharSequence? = null
    private var mTitle: CharSequence? = null
    var mDrawerToggle: android.support.v7.app.ActionBarDrawerToggle? = null
    internal lateinit var sliderz: ImageView
    internal    var mNavigationDrawerItemTitles: Array<String>? = null
    private  var mDrawerLayout: DrawerLayout? = null

    lateinit var txt_nombretarjeta: TextView
    lateinit var txt_cardnumber: TextView
    lateinit var txt_month: TextView
    lateinit var txt_year: TextView
    lateinit var txt_cvv: TextView
    lateinit var txt_codigopostal: TextView
    lateinit var btn_pay: Button


    lateinit var txt_fecha: TextView
    lateinit var txt_hora: TextView
    lateinit var txt_distrito: TextView
    lateinit var txt_cancha: TextView
    lateinit var txt_local: TextView
    lateinit var txt_reservar: Button

    var val_nombretarjeta: String? = null
    var val_cardnumber:String? = null
    var val_month:String? = null
    var val_year:String? = null
    var valcvv:String? = null
    var val_codigopostal:String? = null

    private val client = OkHttpClient()
    private val BASE_URL = "http://arteypixel.com/appreserva/soloreservar.php"
    private val BASE_URL2 = "https://arteypixel.com/appreserva/culqi-php-master/examples/01-create-token.php"

    internal var TAG_REGISTER: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soloreservar)

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

        txt_fecha = findViewById(R.id.fecha)
        txt_hora = findViewById(R.id.hora)
        txt_distrito = findViewById(R.id.distrito)
        txt_cancha = findViewById(R.id.cancha)
        txt_local = findViewById(R.id.local)
        txt_reservar = findViewById(R.id.reservar)

        txt_fecha.text = "Fecha:" + fecha
        txt_hora.text = "Hora:" + hora
        txt_distrito.text = "Distrito: " + distrito
        txt_cancha.text = "Cancha:" + cancha_nombre
        txt_local.text = "Local: " + local



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


        txt_reservar.setOnClickListener {
            registrarreserva("", "", "", "", "", "",fecha!!,hora!!,departamento!!,departamento_distrito!!,distrito!!,fecha_hora!!,cancha_nombre!!,local!!,fechareserva!!,localid!!,canchaid!!)

        }


    }


    fun registrarreserva(nombre_tarjeta: String,
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
                .add("VALORESRESERVA", "$nombre_tarjeta&$numero_tarjeta&$mes_tarjeta&$anio_tarjeta&$ccv_tarjeta&$codigopostal_tarjeta&$fecha_reserva&$hora&$departamento&$departamento_distrito&$distrito&$fecha_hora&$cancha_nombre&$local&$usuario&$password&$password&$fechareserva&$localid&$canchaid")
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

                    ver2(resp)
 */

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

    internal fun setupDrawerToggle() {
        mDrawerToggle = android.support.v7.app.ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name)
        mDrawerToggle!!.syncState()
    }
}
