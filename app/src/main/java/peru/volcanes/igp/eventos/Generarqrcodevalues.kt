package peru.volcanes.igp.eventos
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.widget.EditText
import android.widget.TextView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder


class Generarqrcodevalues : AppCompatActivity() {



    lateinit var valor: TextView

    lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generarqrcodevalues)


        valor = findViewById(R.id.txtcontenido) as EditText

        btn = findViewById(R.id.proceso) as Button



        val myImageView = findViewById(R.id.imgview) as ImageView

        /*
        val myBitmap = BitmapFactory.decodeResource(
                applicationContext.resources,
                R.drawable.puppy)
        myImageView.setImageBitmap(myBitmap)
*/


        val btn = findViewById(R.id.proceso) as Button

        btn.setOnClickListener{

            val k:String = valor.text.toString()

            val SA:MultiFormatWriter = MultiFormatWriter()
            val sas: BitMatrix = SA.encode(k, BarcodeFormat.QR_CODE, 400,400)
            val barc: BarcodeEncoder = BarcodeEncoder()
            val bitmap:Bitmap = barc.createBitmap(sas)
            myImageView.setImageBitmap(bitmap)


        }



    }
}
