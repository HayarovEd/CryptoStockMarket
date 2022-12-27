package com.edurda77.cryptostockmarket.utils

import retrofit2.HttpException
import java.io.IOException

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)

    companion object {
        suspend fun <T> handleResponse (data: suspend () -> T): Resource<T> {

            return try {
                Success(data())
            } catch (e: IOException) {
                e.printStackTrace()
                Error(e.message?: "Неизвестная ошибка")
            } catch (e: HttpException) {
                when (e.code()) {
                    400 -> {
                        e.printStackTrace()
                        Error("Неверный запрос")
                    }
                    401 -> {
                        e.printStackTrace()
                        Error("Неправильный логин или пароль")
                    }
                    402 -> {
                        e.printStackTrace()
                        Error("Необходима оплата")
                    }
                    403 -> {
                        e.printStackTrace()
                        Error("Недостаточно прав")
                    }
                    404 -> {
                        e.printStackTrace()
                        Error("Не найдена страница")
                    }
                    408 -> {
                        e.printStackTrace()
                        Error("Превышено время ожидания")
                    }
                    503 -> {
                        e.printStackTrace()
                        Error("Сервер не доступен")
                    }
                    else -> {
                        e.printStackTrace()
                        Error("Неизвестная ошибка")
                    }
                }
            }
        }
    }
}