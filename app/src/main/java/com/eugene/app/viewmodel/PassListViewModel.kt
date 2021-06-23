package com.eugene.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eugene.app.MyApplication
import com.eugene.domain.model.PasswordModel
import com.eugene.domain.usecase.DeletePassInstance
import com.eugene.domain.usecase.GetPassList
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class PassListViewModel : ViewModel() {

    init{
        MyApplication.daggerApplicationComponent.inject(this)
        loadPasswordList()
    }

    @Inject protected lateinit var getPassList : GetPassList
    @Inject protected lateinit var deletePassInstance : DeletePassInstance

    private val _passwordList = MutableLiveData<List<PasswordModel>>()
    val passwordList : LiveData<List<PasswordModel>> = _passwordList

    private val _error = MutableLiveData<Throwable>()
    val error : LiveData<Throwable> = _error

    private val _deletedPassInstance = MutableLiveData<Boolean>()
    val deletedPassInstance : LiveData<Boolean> = _deletedPassInstance

    fun deletePasswordInstance(passwordModel: PasswordModel){
        deletePassInstance(object : DisposableCompletableObserver(){
            override fun onComplete() {
                _deletedPassInstance.value = true
            }

            override fun onError(e: Throwable) {
                _error.value = e
            }

        }, passwordModel)
    }

    private fun loadPasswordList(){
        getPassList(object : DisposableObserver<List<PasswordModel>>(){
            override fun onNext(t: List<PasswordModel>) {
                _passwordList.value = t
            }

            override fun onError(e: Throwable) {
                _error.value = e
            }

            override fun onComplete() {}

        }, Unit)
    }

    override fun onCleared() {
        super.onCleared()
        getPassList.dispose()
        deletePassInstance.dispose()
    }

}