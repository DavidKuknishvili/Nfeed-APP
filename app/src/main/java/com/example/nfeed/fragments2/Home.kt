package com.example.nfeed.fragments2


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nfeed.News
import com.example.nfeed.R
import com.example.nfeed.adapter.NewsRecyclerViewAdapter
import com.example.nfeed.model.Model
import com.example.nfeed.network.NetworkClient
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home:Fragment(R.layout.home) {
    private lateinit var progressBar:ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsRecyclerViewAdapter: NewsRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progressBar)


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            retrofitService()

        }

    }
    private suspend fun retrofitService(){
        progressBar.isVisible = true

        val response = NetworkClient.api.getNews()
        val body = response.body()



        if (response.isSuccessful){
            setRecycler(body!!)
            progressBar.isVisible = false

        }
        else{
            val successSnakebar = Snackbar.make(requireView(),"ERROR", Snackbar.LENGTH_LONG)
            successSnakebar.setBackgroundTint(Color.RED).show()
        }
    }
    private fun setRecycler(data: Model){
        recyclerView = requireView().findViewById(R.id.recyclerView)
        newsRecyclerViewAdapter = NewsRecyclerViewAdapter(data)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = newsRecyclerViewAdapter
    }
}