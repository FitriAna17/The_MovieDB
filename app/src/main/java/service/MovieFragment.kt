package com.example.themoviedb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_t_v.*
import model.Movie
import model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import service.MovieApiInterface
import service.MovieApiService

class MovieFragment : Fragment() {
    private val movies = arrayListOf<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_television.layoutManager = LinearLayoutManager(this.context)
        rv_television.setHasFixedSize(true)
        getMovieData { movies : List<Movie> ->
            rv_television.adapter = MovieAdapter(movies)
        }
        showRecyclerView()
    }

    private fun getMovieData(callback: (List<Movie>) -> Unit){
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

        })
    }

    private fun showRecyclerView() {
        rv_television.layoutManager = LinearLayoutManager(this.context)
        rv_television.adapter = MovieAdapter(movies)
    }
}