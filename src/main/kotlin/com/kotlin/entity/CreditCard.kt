package com.kotlin.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.sun.istack.NotNull
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.Null

@Entity
@Table(name = "credit_card")
data class CreditCard(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Int,
    @NotNull
    val name: String,
    @NotNull
    val number: Long,
    @NotNull
    val secretNumber: Int,
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    val validation: LocalDate,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    val client: Client
) {
}