package peru.volcanes.igp.eventos
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.bumptech.glide.signature.ApplicationVersionSignature
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseError
import java.util.concurrent.atomic.AtomicInteger


/*
fun main(args: Array<String>){

}
*/


var databaseReference: DatabaseReference? = null
/*
fun Count() {
    databaseReference.child("a").child("b").addValueEventListener(new  ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                ct = dataSnapshot.getChildrenCount()
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });

}

*/

var df:String =""
var cal2:String=""

var sdf: String = ""

var valork: String = ""

var valor: String = ""
var valorx: String = ""

var msg: String = ""

fun Verificardisponibilidadx(valor1:String, valor2:String): String? {
    var valuo: String = medir(valor1, valor2).toString()


    return valuo
}

var ct: Int = 0

fun medir(valor1:String, valor2:String): String? {


    var mFirebaseDatabase2: DatabaseReference? = null
    FirebaseDatabase.getInstance()
    var database2 = FirebaseDatabase.getInstance()
    mFirebaseDatabase2 = database2.getReference("reservas").child("reportes")
//    mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1).addValueEventListener(object : ValueEventListener {


    mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1).addListenerForSingleValueEvent(object : ValueEventListener {



         override fun onDataChange(dataSnapshot: DataSnapshot) {
            println("In onDataChange")


             for (child in dataSnapshot.children) {
                 //child is each element in the finished list
                 val message = child.value as Map<String, Any>

                   msg = message.get("fecha") as String



                // fd(msg)

             }

 /*
               if (dataSnapshot.exists()){
                 valor = "A"

                 fd()
             }
             else{
                 valor = "B"
                 fd()

             }

             */



           /*
            for (dataSnapshot in 0..dataSnapshot.childrenCount){

                val map = HashMap<String, Object>()

                var hashMapd = HashMap<String, Object>()
            }*/



             ct = dataSnapshot.getChildrenCount().toInt()

            /*
            if (ct == 0) {

                valor = "A"
            }

            else{
                valor = "B"

            }
            */


        }



        override fun onCancelled(databaseError: DatabaseError) {

        }


    })
    println("After adding listener")


if (msg  != "") {
    valor = "A"
}
    else{
    valor = "B"
}



    return valor



}

fun fd(g:String): String {


    if(msg.length < 2){
        valor = "A"
        valork = valor


    }
    else{
        valor = "B"
        valork = valor


    }

    return valork


}

var contador: Int = 0
var contadorf: String = ""
var sreporte = null

var sreportex: String = ""

var llave: String = ""

var numChildren: Int = 0

var newCount:Int = 0
var de:String = ""



fun Verificardisponibilidad33(valor1:String, valor2:String): String? {
    Verificardisponibilidadss(valor1,valor2)
    //viewss()
    //var gf: String? = viewss()
    valorx = df()

    return  valorx
}

fun Verificardisponibilidadss(valor1:String, valor2:String): String? {

    var mFirebaseDatabase2: DatabaseReference
    FirebaseDatabase.getInstance()
    val database2 = FirebaseDatabase.getInstance()
    mFirebaseDatabase2 = database2.getReference("reservas").child("reportes")
    mFirebaseDatabase2.keepSynced(true)

/*
    mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1).addChildEventListener(object : ChildEventListener {

        override fun onChildAdded(dataSnapshot: DataSnapshot, s: String) {
/*
if(dataSnapshot.exists() != null){
    sreportex = "111"

}
            else{
    sreportex == "222"
}

*/


     if (dataSnapshot.exists()){
                        llave = "A"
                    }
                    else{
                        llave = "B"
                    }





        }

        override fun onChildChanged(dataSnapshot: DataSnapshot, s: String) {


        }



        override fun onChildRemoved(dataSnapshot: DataSnapshot) {


        }

        override fun onChildMoved(dataSnapshot: DataSnapshot, s: String) {


        }

        override fun onCancelled(databaseError: DatabaseError) {
        }

    })



    return llave
    */
   //  return  dataSnapshot.children.count()

    //
    //
    //



    /*

    mFirebaseDatabase2.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(stegoHeightSnapshot: DataSnapshot) {
             val query = mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1)
            query.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Data is ordered by increasing height, so we want the first entry
                    if (dataSnapshot.exists()){
                        llave = "A"
                    }
                    else{
                        llave = "B"
                    }


                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // ...
                }
            })
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // ...
        }
    })
*/


    ///

    var count = AtomicInteger()

    mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1).limitToFirst(1).addChildEventListener(object : ChildEventListener {
        override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
            // New child added, increment count
              newCount = count.incrementAndGet()
            val sreporte4 = dataSnapshot.getValue<Reservas>(Reservas::class.java)

            System.out.println("Added " + dataSnapshot.key + ", count is " + newCount)


            if (prevChildKey != "2"){
                llave = "A"
            }
            else{
                llave = "B"
            }




            guardar_pref2(sreporte4)

        }

        override fun onChildChanged(dataSnapshot: DataSnapshot, prevChildKey: String?) {


        }



        override fun onChildRemoved(dataSnapshot: DataSnapshot) {


        }

        override fun onChildMoved(dataSnapshot: DataSnapshot, prevChildKey: String?) {


        }

        override fun onCancelled(databaseError: DatabaseError) {
        }


        // ...
    })

// The number of children will always be equal to 'count' since the value of
// the dataSnapshot here will include every child_added event triggered before this point.
    mFirebaseDatabase2.orderByChild("fecha_hora").equalTo(valor2+"_"+valor1).limitToFirst(1).addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
              numChildren = dataSnapshot.childrenCount.toInt()
             de = count.get().toString()  + " == " + numChildren
        }

        override fun onCancelled(databaseError: DatabaseError) {

        }

    })




        return newCount.toString()

}


fun guardar_pref2(sreporte: Reservas?) {




      df = sreporte!!.getFecha()
      cal2 =  sreporte!!.getHora()

    mostrar_ult_sismo2(df, cal2)
}




fun mostrar_ult_sismo2(ds:String, vc:String): String? {

     sdf  = ds+vc

    return  sdf
}

fun df(): String {

    return  sdf
}
