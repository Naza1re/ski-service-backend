package com.kotlin.skiservice.service.impl

import com.kotlin.skiservice.dto.ticket.TicketResponse
import com.kotlin.skiservice.entities.QueueTicket
import com.kotlin.skiservice.mapper.QueueTicketMapper
import com.kotlin.skiservice.repository.TicketRepository
import com.kotlin.skiservice.service.TicketService
import org.springframework.stereotype.Service

@Service
class TicketServiceImpl(
    private val ticketRepository: TicketRepository,
    private val ticketMapper: QueueTicketMapper
) : TicketService {

    override fun getNewTicket(): TicketResponse {
        val maxTicketNumber = ticketRepository.findMaxTicketNumber() ?: 0
        val ticket = QueueTicket(ticketNumber = maxTicketNumber + 1, status = "WAITING")
        val savedTicket = ticketRepository.save(ticket)
        return ticketMapper.toResponse(savedTicket)
    }

}