package com.kotlin.skiservice.service

import com.kotlin.skiservice.dto.ticket.TicketResponse

interface TicketService {
    fun getNewTicket(): TicketResponse
}