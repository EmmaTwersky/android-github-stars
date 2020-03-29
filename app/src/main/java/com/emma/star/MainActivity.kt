package com.emma.star

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emma.star.databinding.ActivityMainBinding
import com.emma.star.di.ViewModelFactory
import com.emma.star.view.MainViewModel
import com.emma.star.view.RepoAdapter
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var repoRecyclerView: RecyclerView
    private lateinit var repoViewAdapter: RecyclerView.Adapter<*>
    private lateinit var repoViewManager: RecyclerView.LayoutManager

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        component.inject(this)

        repoViewManager = LinearLayoutManager(this)
        repoRecyclerView = binding.reposRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = repoViewManager
        }

        viewModel = ViewModelProvider(this@MainActivity, factory)
            .get(MainViewModel::class.java)

        viewModel.repos.observe(this, Observer {
            repoViewAdapter = RepoAdapter(it)
            repoRecyclerView.adapter = repoViewAdapter
        })

        viewModel.searchStatus.observe(this, Observer {
            when (it) {
                true -> binding.progressBar.visibility = View.VISIBLE
                false -> binding.progressBar.visibility = View.GONE
            }
        })
    }

    override fun onStart() {
        super.onStart()
        binding.searchButton.setOnClickListener {
            viewModel.searchRepos(binding.searchText.text.toString())

            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.searchText.windowToken, 0)
        }
    }
}
