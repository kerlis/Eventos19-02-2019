package peru.volcanes.igp.eventos
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.io.File

class Pagoexitoso : AppCompatActivity() {
    lateinit var valor: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagoexitoso)

        val myImageView = findViewById(R.id.imgview) as ImageView
        val btn = findViewById(R.id.proceso) as Button

        val i = this.intent
        var fecha_val = i.extras!!.getString("FECHA")
        var hora_val = i.extras!!.getString("HORA")
        var departamento_val = i.extras!!.getString("DEPARTAMENTO")
        var departamento_distrito_val = i.extras!!.getString("DEPARTAMENTO_DISTRITO")
        var distrito_val = i.extras!!.getString("DISTRITO")
        var fecha_hora_val = i.extras!!.getString("FECHA_HORA")
        var cancha_nombre_val = i.extras!!.getString("CANCHA_NOMBRE")
        var local_val = i.extras!!.getString("LOCAL")

        val k:String = "Fecha: $fecha_val \n" +
                       "Hora: $hora_val \n" +
                        "Cancha: $cancha_nombre_val \n" +
                        "Local: $local_val"

        val SA: MultiFormatWriter = MultiFormatWriter()
        val sas: BitMatrix = SA.encode(k, BarcodeFormat.QR_CODE, 400,400)
        val barc: BarcodeEncoder = BarcodeEncoder()
        val bitmap: Bitmap = barc.createBitmap(sas)
        myImageView.setImageBitmap(bitmap)

        btn.setOnClickListener {
            val fileName = "image.png"
            try {
                val ostream = this.openFileOutput(fileName, Context.MODE_WORLD_READABLE)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream)
                ostream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            val share = Intent(Intent.ACTION_SEND)
            share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            share.putExtra(Intent.EXTRA_SUBJECT, "QR!")
            share.putExtra(Intent.EXTRA_TEXT, "Comparto mi comprobante!")
            share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(File(this.getFileStreamPath(fileName).getAbsolutePath())))
            share.type = "image/jpeg"
            share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(Intent.createChooser(share, "Share via"))
        }
    }
}
