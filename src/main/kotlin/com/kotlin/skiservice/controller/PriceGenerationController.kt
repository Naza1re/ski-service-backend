package com.kotlin.skiservice.controller

import com.kotlin.skiservice.dto.price.PriceResponse
import com.kotlin.skiservice.service.PriceGenerationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v0.1/price")
class PriceGenerationController(
    private val priceGenerationService: PriceGenerationService
) {

    @GetMapping("/generate")
    fun getPrice(@RequestParam("tariffCode") tariffCode: String,
                 @RequestParam("countHours") countHours: Int,
                 @RequestParam("ticketNumber") ticketNumber: Int) : ResponseEntity<PriceResponse> {
        return ResponseEntity.ok(priceGenerationService.generatePriceForTariff(ticketNumber,tariffCode, countHours))
    }
}