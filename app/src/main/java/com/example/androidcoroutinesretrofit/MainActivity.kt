package com.example.androidcoroutinesretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidcoroutinesretrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: UserViewModel by viewModels()
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val usersAdapter: UsersAdapter = UsersAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.refresh()
        binding.users.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = usersAdapter
        }

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.users.observe(this) {
            it?.let { users ->
                binding.users.visibility = View.VISIBLE
                usersAdapter.updateCountries(users)
            }
        }

        viewModel.loadError.observe(this){isError->
            binding.listError.visibility = if(isError.isNullOrEmpty()) View.GONE else View.VISIBLE
            isError?.let {message->
                if (message.isNotEmpty()){
                    binding.listError.text = message
                }
            }


        }

        viewModel.loading.observe(this){ isLoading ->
            isLoading?.let {
                binding.loadingView.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    binding.listError.visibility = View.GONE
                    binding.users.visibility = View.GONE
                }
            }
        }
    }
}