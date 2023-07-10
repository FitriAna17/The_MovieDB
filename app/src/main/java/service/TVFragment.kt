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
import model.TV
import model.TVResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import service.MovieApiInterface
import service.MovieApiService
import service.TVApiInterface
import service.TVApiService

class TVFragment : Fragment() {
    private val tv = arrayListOf<TV>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_t_v, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_television.layoutManager = LinearLayoutManager(this.context)
        rv_television.setHasFixedSize(true)
        getTvData{  TV : List<TV> ->
            rv_television.adapter = TVAdapter(TV)
        }
        showRecyclerView()
    }

    private fun getTvData(callback: (List<TV>) -> Unit){
        val apiService = TVApiService.getInstance().create(TVApiInterface::class.java)
        apiService.getTVList().enqueue(object : Callback<TVResponse> {
            override fun onFailure(call: Call<TVResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<TVResponse>, response: Response<TVResponse>) {
                return callback(response.body()!!.tv)
            }

        })
    }

    private fun showRecyclerView() {
        rv_television.layoutManager = LinearLayoutManager(this.context)
        rv_television.adapter = TVAdapter(tv)
    }
}