package com.example.cache.factory

import com.example.cache.factory.TestDataFactory.randomInt
import com.example.cache.factory.TestDataFactory.randomLong
import com.example.cache.model.Config

object TestConfigDataFactory {

    fun makeCachedConfig(): Config {
        return Config(randomInt(), randomLong())
    }
}