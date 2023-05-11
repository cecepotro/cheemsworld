package mx.itson.world.interfaces

import mx.itson.world.entidades.Visita
import retrofit2.Call
import retrofit2.http.GET

interface CheemsAPI {

    @GET("visitas")
    fun getVisitas(): Call<List<Visita>>
}