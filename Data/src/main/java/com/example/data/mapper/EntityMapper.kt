package com.example.data.mapper

interface EntityMapper<E, D> {

    // map from entity instance in the data layer -> from data to domain
    fun mapFromEntity(entity: E): D

    // from the the domain to the domain layer
    fun mapToEntity(domain: D): E
}