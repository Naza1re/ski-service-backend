package com.kotlin.skiservice.service

import com.kotlin.skiservice.entities.Tariff

interface TariffService {

    fun getTariffByCode(code: String): Tariff
}