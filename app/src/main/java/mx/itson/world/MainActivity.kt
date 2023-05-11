package mx.itson.world

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import mx.itson.world.entidades.Visita
import mx.itson.world.utilerias.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    var mapa: GoogleMap? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapaFragment = supportFragmentManager.findFragmentById(R.id.mapa) as SupportMapFragment?
        mapaFragment!!.getMapAsync(this)

        obtenerVisitas()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        try{
            mapa = googleMap
            mapa!!.mapType = GoogleMap.MAP_TYPE_HYBRID
        } catch(ex: Exception){

        }
    }

    fun obtenerVisitas(){
        val call : Call<List<Visita>> = RetrofitUtil.getApi()!!.getVisitas()
        call.enqueue(object : Callback<List<Visita>> {
            override fun onResponse(call: Call<List<Visita>>, response: Response<List<Visita>>) {
               val visitas : List<Visita> = response.body()!!

                mapa?.clear()

                for(v in visitas){
                    val latLng = LatLng(v.latitud!!, v.longitud!!)
                    mapa!!.addMarker(MarkerOptions().position(latLng)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_cheems)))
                }
            }

            override fun onFailure(call: Call<List<Visita>>, t: Throwable) {

            }

        })

    }
}











