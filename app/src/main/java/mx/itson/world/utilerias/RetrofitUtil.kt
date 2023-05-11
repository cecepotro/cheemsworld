package mx.itson.world.utilerias

import com.google.gson.GsonBuilder
import mx.itson.world.interfaces.CheemsAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil {

    fun getApi(): CheemsAPI? {
        val gson = GsonBuilder()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.232.2.67/cheems/public/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(CheemsAPI::class.java)
    }

}