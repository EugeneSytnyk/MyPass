package com.eugene.domain.usecase

import com.eugene.domain.model.PasswordModel
import com.eugene.domain.repository.PassRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetPassById (
        private val passRepository: PassRepository,
        executor : Scheduler,
        postExecution : Scheduler
) : ObservableUseCase<PasswordModel, Int>(executor, postExecution) {

    override fun buildUseCaseObservable(params: Int): Observable<PasswordModel> {
        return passRepository.loadPassword(params)
    }

}