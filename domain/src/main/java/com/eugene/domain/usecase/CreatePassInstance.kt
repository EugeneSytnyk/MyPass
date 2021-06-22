package com.eugene.domain.usecase

import com.eugene.domain.model.factory.PasswordFactory
import com.eugene.domain.repository.PassRepository
import io.reactivex.Completable
import io.reactivex.Scheduler

class CreatePassInstance constructor(
    private val passRepository: PassRepository,
    private val passwordFactory: PasswordFactory,
    executor : Scheduler,
    postExecution : Scheduler,
) : CompletableUseCase<CreatePassInstance.Params>(executor, postExecution) {

    override fun buildUseCaseCompletable(params: Params): Completable {
        val passwordModel = passwordFactory.create(params.password, params.name, params.description)
        return passRepository.addPassword(passwordModel)
    }

    data class Params(val password : String, val name : String, val description : String)

}