package com.example.itunesk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itunesk.databinding.ActivityMainBinding
import com.example.itunesk.model.ItunesResponse
import com.example.itunesk.model.Repository
import com.example.itunesk.model.RepositoryImpl
import com.example.itunesk.view.ItunesAdapter
import com.example.itunesk.viewmodel.ItunesViewModel

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private val viewModel : ItunesViewModel by lazy {
        ViewModelProvider(this,
            ItunesViewModel.ItunesViewModelFactory(repository))
            .get(ItunesViewModel::class.java)
    }

    private val repository: Repository by lazy {
        RepositoryImpl()
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ItunesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = ItunesAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.searchView.btnSearchArtist.setOnClickListener {
            isDisplayedData()
            binding.searchView.tilArtistInput.editText?.text?.let {
                viewModel.getArtistByName(it.toString())
            }
        }

        viewModel.getLiveDataResponse().observe(this,
        Observer {
            isDisplayedData()
            displayData(it)
        })
    }

    private fun displayData(dataSet: ItunesResponse) {
        adapter.setDataSet(dataSet)
    }

    private fun isDisplayedData(){
        if(binding.pgLoading.visibility == View.GONE) {
            binding.pgLoading.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        }
        else {
            binding.pgLoading.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
    }
}