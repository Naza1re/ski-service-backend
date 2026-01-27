package com.kotlin.skiservice.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "skipass")
class SkiPass(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val barcode: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    var client: Client? = null,

    @Column(name = "valid_from")
    val validFrom: LocalDateTime? = null,

    @Column(name = "valid_to")
    val validTo: LocalDateTime? = null,
)
