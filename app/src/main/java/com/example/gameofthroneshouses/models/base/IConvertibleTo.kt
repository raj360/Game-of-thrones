package com.example.gameofthroneshouses.models.base

interface IConvertibleTo<T> {
    fun convertTo(): T?
}