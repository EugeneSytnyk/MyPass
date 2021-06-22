package com.eugene.domain.usecase

import com.eugene.domain.model.PasswordModel
import com.eugene.domain.model.factory.PasswordFactory
import com.eugene.domain.repository.PassRepository
import io.reactivex.Completable
import io.reactivex.Scheduler

class UpdatePassInstance (
        private val passRepository: PassRepository,
        private val passwordFactory: PasswordFactory,
        executor : Scheduler,
        postExecution : Scheduler
) : CompletableUseCase<UpdatePassInstance.Params>(executor, postExecution) {

    override fun buildUseCaseCompletable(params: Params): Completable {
        val passModel = passwordFactory.update(params.password, params.name, params.description, params.passwordModel)
        return passRepository.updatePassword(passModel)
    }

    class Params(val password : String? = null, val name : String? = null, val description : String? = null, val passwordModel: PasswordModel)

}