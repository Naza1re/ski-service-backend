package com.kotlin.skiservice.controller

import com.kotlin.skiservice.dto.ticket.TicketResponse
import com.kotlin.skiservice.service.TicketService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v0.1/ticket")
class TicketController(
    private val ticketService: TicketService
) {

    @PostMapping
    fun create() : ResponseEntity<TicketResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.getNewTicket())
    }

}