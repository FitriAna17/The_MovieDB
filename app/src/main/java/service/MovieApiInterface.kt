package service

import model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiInterface{
     @GET("/3/movie/popular?api_key=dd1abee0f456bfc6a2fd54b354cd1a46")
     fun getMovieList(): Call<MovieResponse>
}