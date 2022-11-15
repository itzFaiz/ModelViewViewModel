package com.example.modelviewviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.modelviewviewmodel.adapter.UserAdapter
import com.example.modelviewviewmodel.api.UserRetrofitService
import com.example.modelviewviewmodel.databinding.ActivityUserBinding
import com.example.modelviewviewmodel.repository.UserRepository
import com.example.modelviewviewmodel.viewModels.UserViewModel
import com.example.modelviewviewmodel.viewModels.UserViewModelFactory
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserActivity : AppCompatActivity() {

    private val TAG = "UserActivity"
    private lateinit var binding: ActivityUserBinding
    lateinit var viewModel: UserViewModel
    private val adapter = UserAdapter()
    private val retrofitService = UserRetrofitService.getInstance()
    var page = 1
    var isScrolling = false
    var totalpage: Int? = null

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel =
            ViewModelProvider(this, UserViewModelFactory(UserRepository(retrofitService))).get(
                UserViewModel::class.java)
        binding.recyclerview.adapter = adapter
        viewModel.userList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setUserList(it)
        })

        viewModel.errorMessage.observe(this, Observer {
        })

            GlobalScope.launch {

                totalpage =   UserRepository(retrofitService).getAllUsers(page).execute().body()?.total_pages
            }

            viewModel.getAllUsers(page)

            binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                    val total = adapter.itemCount
                    val visibleItemCount = recyclerView.layoutManager?.itemCount

                    if (!isScrolling) {
                        if (page < totalpage!!) {
                            if (visibleItemCount!! <= total) {
                                page++
                                viewModel.getAllUsers(page)
                            }
                        }
                    }
                    super.onScrolled(recyclerView, dx, dy)
                }
            })


    }
}