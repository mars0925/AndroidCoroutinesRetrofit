package com.example.androidcoroutinesretrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidcoroutinesretrofit.model.User
import kotlinx.coroutines.*

class UserViewModel: ViewModel()  {
    val users = MutableLiveData<List<User>?>()
    val loadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()
    private val apiService = UserApiService.create()
    private var job:Job? = null
    /*coroutines exception 處理*/
    private val exceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        viewModelScope.launch {
            withContext(Dispatchers.Main){
                onError("Exception ${throwable.localizedMessage}")
            }
        }


    }

    fun refresh() {
        fetchUser()
    }

    private fun fetchUser() {
        loading.value = true
        /*在背景執行緒拉資料*/
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = apiService.getUsers(30)
            /*在Main thread更新 ui*/
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    users.value = response.body()?.users
                    loadError.value = null
                    loading.value = false
                }else{
                    onError("Error ${response.message()}")
                }
            }
        }
    }

    private fun onError(message: String) {
        loadError.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}