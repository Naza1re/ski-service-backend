package com.kotlin.skiservice.service.impl

import com.kotlin.skiservice.entities.Tariff
import com.kotlin.skiservice.exception.TariffNotFoundException
import com.kotlin.skiservice.repository.TariffRepository
import com.kotlin.skiservice.service.TariffService
import org.springframework.stereotype.Service

@Service
class TariffService(
    private val tariffRepository: TariffRepository
) : TariffService {

    private fun getOrThrow(code: String) : Tariff {
        val tariff = tariffRepository.findByCode(code)
        if (tariff != null) {
            return tariff.get()
        } else {
            throw TariffNotFoundException("Tariff with code $code not found")
        }
    }

    override fun getTariffByCode(code: String): Tariff {
        return getOrThrow(code)
    }
}