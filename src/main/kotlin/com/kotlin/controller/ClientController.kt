package com.kotlin.controller

import com.kotlin.entity.Client
import com.kotlin.service.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/clients")
class ClientController(
    @Autowired val clientService: ClientService
) {
    @GetMapping("/")
    fun getClients(@RequestParam name: String?): ResponseEntity<Any> {
        return clientService.findAll(name)
    }

    @GetMapping("/{id}/")
    fun getOneClient(@PathVariable(name = "id") id: Long): ResponseEntity<Any> {
        return clientService.getOne(id)
    }

    @PostMapping("/")
    fun postClients(@Valid @RequestBody client: Client, errors: Errors): ResponseEntity<Any> {
        return clientService.save(client, errors)
    }

    @PutMapping("/{id}/")
    fun putClient(@PathVariable id: Long, @RequestBody client: Client): ResponseEntity<Any> {
        return clientService.put(id, client)
    }

    @DeleteMapping("/{id}/")
    fun deleteClient(@PathVariable(name = "id") id: Long): ResponseEntity<String> {
        return clientService.delete(id)
    }
}