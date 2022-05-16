package com.soethan.citysearch.data


import androidx.paging.*
import com.google.gson.Gson
import com.soethan.citysearch.data.local.CityPagingSource
import com.soethan.citysearch.data.local.LocalDataSource
import com.soethan.citysearch.data.mapper.EntityMapper
import com.soethan.citysearch.data.network.ApiDataSource
import com.soethan.citysearch.domain.CitySearchRepo
import com.soethan.citysearch.domain.model.City
import com.soethan.citysearch.mapper.DomainMapper
import com.soethan.citysearch.utils.ConnectivityChecker
import com.soethan.citysearch.utils.ErrorBody
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketException
import javax.inject.Inject

class CitySearchRepoImpl @Inject constructor(
    private val apiDataSource: ApiDataSource,
    private val localDataSource: LocalDataSource,
    private val entityMapper: EntityMapper,
    private val domainMapper: DomainMapper,
    private val connectivityChecker: ConnectivityChecker
) : CitySearchRepo {

    override suspend fun loadAllCities(fetch: Boolean): Flow<PagingData<City>> {

        return withContext(Dispatchers.IO) {
            if (fetch && connectivityChecker.isNetworkAvailable()) {
                val result = safeApiCall(Dispatchers.IO) {
                    return@safeApiCall apiDataSource.loadAllCities()
                }

                localDataSource.saveAllCities(entityMapper.transform(result))
            }
            Pager(
                PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false,
                    initialLoadSize = 20
                ),

                ) {
                CityPagingSource(
                    localDataSource = localDataSource,
                    domainModel = domainMapper
                )
            }.flow


        }
    }


    override suspend fun searchCities(keyword: String): Flow<PagingData<City>> {
        return withContext(Dispatchers.IO) {
            Pager(
                PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false,
                    initialLoadSize = 20
                ),

                ) {
                CityPagingSource(
                    localDataSource = localDataSource,
                    domainModel = domainMapper,
                    query = keyword
                )
            }.flow
        }
    }

    private suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): T {
        return withContext(dispatcher) {
            try {
                return@withContext apiCall.invoke()
            } catch (t: Throwable) {
                throw when (t) {
                    is SocketException -> ErrorBody(errMsg = "No internet connection")
                    is ConnectException -> ErrorBody(errMsg = t.localizedMessage)
                    is HttpException -> {
                        val code = t.code()
                        val errorBody = t.response()?.errorBody()?.string()

                        val gsonErrorBody = Gson().fromJson(
                            errorBody,
                            ErrorBody::class.java
                        )
                        val message = gsonErrorBody.message
                        ErrorBody(errMsg = message ?: "", status = code)
                    }
                    else -> ErrorBody(errMsg = "Something went wrong")
                }
            }


        }
    }

//    suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher,
//                                io: suspend () -> T):T{
//        return withContext(dispatcher){
//            try {
//                ResultWrapper.Success(io.invoke())
//            }catch (throwable:Throwable){
//                when(throwable){
//                    is IOException -> ResultWrapper.NetworkError
//                    is HttpException -> {
//                        val code = throwable.code()
//                        val errorBody = throwable.response()?.errorBody()?.string()
//
//                        val gsonErrorBody = Gson().fromJson(
//                            errorBody,
//                            ErrorBody::class.java
//                        )
//                        val message = gsonErrorBody.message
//                        ResultWrapper.GenericError(code, message)
//                    }
//                    else -> ResultWrapper.GenericError(null, null)
//                }
//
//            }
//        }
//    }
}