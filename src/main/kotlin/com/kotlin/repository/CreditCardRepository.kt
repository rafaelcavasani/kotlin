package com.kotlin.repository

import com.kotlin.entity.CreditCard
import org.springframework.data.jpa.repository.JpaRepository

interface CreditCardRepository: JpaRepository<CreditCard, Long> {}