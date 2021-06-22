package com.eugene.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eugene.app.MyApplication
import com.eugene.domain.hasher.Validator
import com.eugene.domain.model.PasswordModel
import com.eugene.domain.usecase.CreatePassInstance
import com.eugene.domain.usecase.GetPassById
import com.eugene.domain.usecase.UpdatePassInstance
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class PasswordViewModel : ViewModel() {

    init{
        MyApplication.Dagger.daggerApplicationComponent.inject(this)
    }

    @Inject protected lateinit var createPassInstance : CreatePassInstance
    @Inject protected lateinit var getPassById: GetPassById
    @Inject protected lateinit var updatePassInstance: UpdatePassInstance
    @Inject protected lateinit var validator: Validator

    private val _passwordInstance = MutableLiveData<PasswordModel>()
    val passwordInstance : LiveData<PasswordModel> = _passwordInstance

    private val _error = MutableLiveData<Throwable>()
    val error : LiveData<Throwable> = _error

    private val _createdPassInstance = MutableLiveData<Boolean>()
    val createdPassInstance : LiveData<Boolean> = _createdPassInstance

    private val _updatedPassInstance = MutableLiveData<Boolean>()
    val updatedPassInstance : LiveData<Boolean> = _updatedPassInstance

    fun validatePass(password : String) =
        if(_passwordInstance.value != null)
            validator.validate(_passwordInstance.value!!.hash, password)
        else false

    fun createNewPass(password: String, name : String, description : String){
        createPassInstance(object : DisposableCompletableObserver(){
            override fun onComplete() {
                _createdPassInstance.value = true
            }

            override fun onError(e: Throwable) {
                _error.value = e
            }

        }, CreatePassInstance.Params(password, name, description))
    }

    fun updatePassInstance(password : String?, name : String?, description: String?, passwordModel: PasswordModel){
        updatePassInstance(object : DisposableCompletableObserver(){
            override fun onComplete() {
                _updatedPassInstance.value = true
            }

            override fun onError(e: Throwable) {
                _error.value = e
            }

        }, UpdatePassInstance.Params(password, name, description, passwordModel))
    }

    fun loadPassWithId(id : Int){
        getPassById(object : DisposableObserver<PasswordModel>(){
            override fun onNext(t: PasswordModel) {
                _passwordInstance.value = t
            }

            override fun onError(e: Throwable) {
                _error.value = e
            }

            override fun onComplete() {}

        }, id)
    }

    override fun onCleared() {
        super.onCleared()
        createPassInstance.dispose()
        getPassById.dispose()
        updatePassInstance.dispose()
    }
}