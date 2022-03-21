package com.android.app.sampleapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.app.sampleapplication.R
import com.android.app.sampleapplication.databinding.ActivityMainBinding
import com.android.app.sampleapplication.network.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter : WeatherListAdapter

    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObservers()
        setupAdapter()
    }

    private fun setupAdapter() {
        adapter = WeatherListAdapter()
        binding.rvItems.layoutManager = LinearLayoutManager(this)
        binding.rvItems.adapter = adapter
    }

    private fun initObservers() {
        mainViewModel.getScreenStateLiveData().observe(this, {
            when (it) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvItems.visibility = View.GONE
                    binding.txtError.visibility = View.GONE
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvItems.visibility = View.GONE
                    binding.txtError.visibility = View.VISIBLE
                    binding.txtError.text = it.message
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvItems.visibility = View.VISIBLE
                    binding.txtError.visibility = View.GONE
                    setupAdapterData()
                }
            }
        })
    }

    private fun setupAdapterData() {

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextSubmit(value: String?): Boolean {
        value?.let {
            mainViewModel.searchData(value)
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return false
    }
}