package com.kotlin.skiservice.service.impl

import com.kotlin.skiservice.dto.skipass.SkiPassRequest
import com.kotlin.skiservice.dto.skipass.SkiPassResponse
import com.kotlin.skiservice.entities.SkiPass
import com.kotlin.skiservice.exception.SkiPassAlreadyExistException
import com.kotlin.skiservice.exception.SkiPassNotFoundException
import com.kotlin.skiservice.mapper.SkiPassMapper
import com.kotlin.skiservice.repository.SkiPassRepository
import com.kotlin.skiservice.service.SkiPassService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class SkiPassServiceImpl(
    private val skiPassRepository: SkiPassRepository,
    private val skiPassMapper: SkiPassMapper,
) : SkiPassService {
    override fun getSkiPass(page: Int, size: Int): Page<SkiPass> {
        val pageRequest = PageRequest.of(page, size)
        return skiPassRepository.findAll(pageRequest)
    }

    override fun createSkiPass(skiPassRequest: SkiPassRequest): SkiPassResponse {
        validateCreateSkiPass(skiPassRequest)
        val skiPassToSave = SkiPass(
            barcode = skiPassRequest.barCode
        )
        val savedSkiPass = skiPassRepository.save(skiPassToSave)
        return skiPassMapper.toResponse(savedSkiPass)
    }

    override fun getSkiPassByBarCode(barCode: String): SkiPassResponse {
        return skiPassMapper.toResponse(getOrThrow(barCode))
    }

    override fun deleteSkiPassByBarCode(barCode: String) {
        val skiPass = getOrThrow(barCode)
        skiPassRepository.delete(skiPass)
    }

    private fun validateCreateSkiPass(skiPassRequest: SkiPassRequest) {
        val skiPass = skiPassRepository.findByBarcode(skiPassRequest.barCode)
        if (skiPass.isPresent) {
            throw SkiPassAlreadyExistException("Ski pass with bar code ${skiPassRequest.barCode} already exist ")
        }
    }

    private fun getOrThrow(skiPassBarCode: String): SkiPass {
        val skiPass = skiPassRepository.findByBarcode(skiPassBarCode)
        if (skiPass != null) {
            return skiPass.get()
        } else {
            throw SkiPassNotFoundException("Ski pass with bar code $skiPassBarCode not found")
        }
    }
}
