package com.example.mvvm.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.R
import com.example.mvvm.data.models.User
import com.example.mvvm.ui.adapter.UserAdapter
import com.example.mvvm.ui.viewModel.GithubViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val vm by lazy {
        ViewModelProvider(this).get(GithubViewModel::class.java)
    }

    val list = arrayListOf<User>()
    val originalList = arrayListOf<User>()
    val adapter =UserAdapter(list)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userRV.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        vm.fetchUsers()

        searchView.isSubmitButtonEnabled =true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { findUser(it) }
                return true
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { findUser(it) }
                return true
            }

        })

        searchView.setOnCloseListener {
            list.clear()
            list.addAll(originalList)
            return@setOnCloseListener true
        }



        vm.users.observe(this, Observer {
            if(!it.isNullOrEmpty()){
                list.addAll(it)
                originalList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })



    }

    private fun findUser(it: String) {
        vm.searchUsers(it).observe(this, Observer {
            if(!it.isNullOrEmpty()){
                list.clear()
                list.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }
}