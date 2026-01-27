package com.kotlin.skiservice.service

import com.kotlin.skiservice.dto.skipass.SkiPassRequest
import com.kotlin.skiservice.dto.skipass.SkiPassResponse
import com.kotlin.skiservice.entities.SkiPass
import org.springframework.data.domain.Page

interface SkiPassService {
    fun getSkiPass(page: Int, size: Int): Page<SkiPass>
    fun createSkiPass(skiPassRequest: SkiPassRequest): SkiPassResponse
    fun getSkiPassByBarCode(barCode: String): SkiPassResponse
    fun deleteSkiPassByBarCode(barCode: String)
}