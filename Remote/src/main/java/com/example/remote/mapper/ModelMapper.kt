package com.example.remote.mapper

interface ModelMapper<in M, out E> {

    fun mapFromModel(projectModel: M): E
}