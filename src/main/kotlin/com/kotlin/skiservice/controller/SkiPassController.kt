package com.kotlin.skiservice.controller

import com.kotlin.skiservice.dto.skipass.SkiPassRequest
import com.kotlin.skiservice.dto.skipass.SkiPassResponse
import com.kotlin.skiservice.entities.SkiPass
import com.kotlin.skiservice.service.SkiPassService
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v0.1/ski-pass")
class SkiPassController(
    private val skiPassService: SkiPassService
) {

    @GetMapping
    fun get(@RequestParam("page") page: Int, @RequestParam("size") size: Int) : Page<SkiPass> {
        return skiPassService.getSkiPass(page, size)
    }

    @GetMapping("/{barCode}")
    fun get(@PathVariable("barCode") barCode: String) : ResponseEntity<SkiPassResponse> {
        return ResponseEntity.ok(skiPassService.getSkiPassByBarCode(barCode))
    }

    @PostMapping
    fun create(@RequestBody skiPassRequest: SkiPassRequest) : ResponseEntity<SkiPassResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(skiPassService.createSkiPass(skiPassRequest))
    }

    @DeleteMapping("/{barCode}")
    fun delete(@PathVariable("barCode") barCode: String) : ResponseEntity<Void> {
        skiPassService.deleteSkiPassByBarCode(barCode)
        return ResponseEntity.noContent().build()
    }

}