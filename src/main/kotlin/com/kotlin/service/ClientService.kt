package com.kotlin.service

import com.kotlin.entity.Client
import com.kotlin.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.stereotype.Service
import org.springframework.validation.Errors
import org.springframework.validation.FieldError
import java.lang.Exception

@Service
class ClientService(
    @Autowired var clientRepository: ClientRepository
) {

    fun save(client: Client, errors: Errors): ResponseEntity<Any> {
        return try {
            if (errors.hasErrors()) {
                val map = mutableMapOf<String, String>()
                for (error in errors.allErrors) {
                    map.put((error as FieldError).getField(), error.defaultMessage.toString())
                }
                ResponseEntity(map, HttpStatus.BAD_REQUEST)
            } else {
                val persistedClient = clientRepository.save(client)
                ResponseEntity(persistedClient, HttpStatus.OK)
            }
        } catch (e: Exception) {
            ResponseEntity("Ocorreu um erro ao salvar o cliente.", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    fun getOne(id: Long): ResponseEntity<Any> {
        return try {
            val client: Client = clientRepository.getOne(id)
            ResponseEntity(client, HttpStatus.OK)
        } catch (e: JpaObjectRetrievalFailureException) {
            ResponseEntity("Cliente informado não encontrado", HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            println(e)
            ResponseEntity("Ocorreu um erro ao buscar pelo cliente.", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    fun findAll(name: String?): ResponseEntity<Any> {
        return try {
//            val nameParam = name?: ""
            if (name != null && name != "")
                ResponseEntity(clientRepository.findAllByNameContaining(name), HttpStatus.OK)
            else
                ResponseEntity(clientRepository.findAll(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity("Ocorreu um erro ao buscar pelos clientes.", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    fun delete(id: Long): ResponseEntity<String> {
        return try {
            clientRepository.deleteById(id)
            ResponseEntity("Cliente deletado com sucesso.", HttpStatus.OK)
        } catch (e: EmptyResultDataAccessException) {
            ResponseEntity("Cliente informado não encontrado.", HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ResponseEntity("Ocorreu um erro ao deletar cliente.", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    fun put(id: Long, client: Client): ResponseEntity<Any> {
        return try {
            val persistedClient: Client = clientRepository.getOne(id)
            persistedClient.name = client.name
            clientRepository.saveAndFlush(persistedClient)
            ResponseEntity(persistedClient, HttpStatus.OK)
        } catch (e: JpaObjectRetrievalFailureException) {
            ResponseEntity("Cliente informado não encontrado", HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            println(e)
            ResponseEntity("Ocorreu um erro ao atualizar o cliente.", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}