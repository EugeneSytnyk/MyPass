package com.eugene.domain.usecase

import com.eugene.domain.model.PasswordModel
import com.eugene.domain.repository.PassRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetPassList (
        private val passRepository: PassRepository,
        executor : Scheduler,
        postExecution : Scheduler
) : ObservableUseCase<List<PasswordModel>, Unit>(executor, postExecution) {

    override fun buildUseCaseObservable(params: Unit): Observable<List<PasswordModel>> {
        return passRepository.loadPasswords()
    }

}