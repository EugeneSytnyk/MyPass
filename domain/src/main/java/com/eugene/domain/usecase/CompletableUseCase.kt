package com.eugene.domain.usecase

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver

abstract class CompletableUseCase<Params> (
        private val executor: Scheduler,
        private val postExecution: Scheduler)
{
    private val disposables = CompositeDisposable()

    abstract fun buildUseCaseCompletable(params: Params) : Completable

    operator fun invoke(observer: DisposableCompletableObserver, params: Params) {
        val completable = buildUseCaseCompletable(params)
                .subscribeOn(executor)
                .observeOn(postExecution)
        addDisposable(completable.subscribeWith(observer))
    }

    fun dispose(){
        if(!disposables.isDisposed) disposables.dispose()
    }

    private fun addDisposable(disposable : Disposable){
        disposables.add(disposable)
    }
}