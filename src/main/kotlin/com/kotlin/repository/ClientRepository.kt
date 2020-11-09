package com.kotlin.repository

import com.kotlin.entity.Client
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository: JpaRepository<Client, Long> {
    fun findAllByNameContaining(name: String): List<Client>
}