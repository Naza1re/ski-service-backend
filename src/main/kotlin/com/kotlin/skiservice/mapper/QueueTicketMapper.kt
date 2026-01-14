package com.kotlin.skiservice.mapper

import com.kotlin.skiservice.dto.ticket.TicketResponse
import com.kotlin.skiservice.entities.QueueTicket
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface QueueTicketMapper {
    fun toResponse(ticket: QueueTicket) : TicketResponse
}