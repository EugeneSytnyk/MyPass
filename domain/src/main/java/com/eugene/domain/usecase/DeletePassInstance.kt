package com.eugene.domain.usecase

import com.eugene.domain.model.PasswordModel
import com.eugene.domain.repository.PassRepository
import io.reactivex.Completable
import io.reactivex.Scheduler

class DeletePassInstance (
    private val passRepository: PassRepository,
    executor : Scheduler,
    postExecution : Scheduler
) : CompletableUseCase<PasswordModel>(executor, postExecution) {

    override fun buildUseCaseCompletable(params: PasswordModel): Completable {
        return passRepository.deletePassword(params)
    }

}