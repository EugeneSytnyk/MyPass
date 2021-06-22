package com.eugene.domain.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

abstract class ObservableUseCase<T, Params>(
        private val executor: Scheduler,
        private val postExecution: Scheduler)
{
    private val disposables = CompositeDisposable()

    abstract fun buildUseCaseObservable(params: Params) : Observable<T>

    operator fun invoke(observer: DisposableObserver<T>, params: Params) {
        val observable = buildUseCaseObservable(params)
                .subscribeOn(executor)
                .observeOn(postExecution)
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose(){
        if(!disposables.isDisposed) disposables.dispose()
    }

    private fun addDisposable(disposable : Disposable){
        disposables.add(disposable)
    }
}