package peru.volcanes.igp.eventos
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.net.Uri
import android.widget.EditText
import android.widget.TextView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.io.File


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
