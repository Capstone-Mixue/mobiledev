package com.ahmrh.patypet.domain.use_case.pet

import android.util.Log
import com.ahmrh.patypet.common.Resource
import com.ahmrh.patypet.data.remote.responses.PetByIdResponse
import com.ahmrh.patypet.data.repositories.PetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPetByIdUseCase @Inject constructor(
    private val repository: PetRepository
) {
    operator fun invoke(
        email: String,
        id: Int
    ): Flow<Resource<PetByIdResponse>> = flow {
        Log.d("PredictUseCase", "PredictInvoked")

        try {
            repository.getPetById(email, id)
                .catch {
                    throw (it)
                }
                .collect {
                    emit(Resource.Success(it))
                }


        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = e.localizedMessage
                        ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach server"))
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = e.message
                        ?: "An unexpected error occurred"
                )
            )
        }
    }
}