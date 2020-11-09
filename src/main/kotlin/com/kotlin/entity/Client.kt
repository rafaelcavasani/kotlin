package com.kotlin.entity

import com.sun.istack.NotNull
import com.sun.istack.Nullable
import org.hibernate.validator.constraints.br.CPF
import javax.persistence.*
import javax.validation.constraints.Null
import javax.validation.constraints.Size
import kotlin.jvm.Transient

@Entity
@Table(name = "client")
data class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long,
    @NotNull
    @field:Column(length = 100)
    @field:Size(min = 5, max = 100)
    var name: String,
    @NotNull
    @field:Column(unique = true, length = 11)
    @field:CPF()
    val cpf: String
) {
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    private lateinit var _creditCards: List<CreditCard>

    val creditCards get() = _creditCards
}