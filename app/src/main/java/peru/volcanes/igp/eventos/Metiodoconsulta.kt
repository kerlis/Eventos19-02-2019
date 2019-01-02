package peru.volcanes.igp.eventos

import android.content.Context
import com.google.firebase.database.*
import io.fabric.sdk.android.services.common.CommonUtils


fun ver(valor: String): String {
    val result = "ssfsf"
    return result
}


/*

fun ver(valor1:String) {

    val mDatabase: DatabaseReference
    mDatabase = FirebaseDatabase.getInstance("https://igpsismos2.firebaseio.com/").getReference("messages")

    var sreporte: DataSnapshot

    mDatabase.keepSynced(true)
    mDatabase.orderByKey().limitToLast(1).addChildEventListener(object : ChildEventListener {
        override fun onChildAdded(dataSnapshot: DataSnapshot, s: String) {
              sreporte = dataSnapshot.getValue<Reservas>(Reservas::class.java)
          //  guardar_pref(sreporte)
        }

        override fun onChildChanged(dataSnapshot: DataSnapshot, s: String) {
            val sreporte = dataSnapshot.getValue<Reservas>(Reservas::class.java)
           // guardar_pref(sreporte)
        }

        override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            val sreporte = dataSnapshot.getValue<Reservas>(Reservas::class.java)
          //  guardar_pref(sreporte)
        }

        override fun onChildMoved(dataSnapshot: DataSnapshot, s: String) {
            val sreporte = dataSnapshot.getValue<Reservas>(Reservas::class.java)
          //  guardar_pref(sreporte)
        }

        override fun onCancelled(databaseError: DatabaseError) {}
    })


    return sreporte
}

*/

