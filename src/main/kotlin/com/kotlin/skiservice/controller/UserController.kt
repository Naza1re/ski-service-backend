package com.kotlin.skiservice.controller

import com.kotlin.skiservice.dto.user.CreateUserRequest
import com.kotlin.skiservice.entities.user.User
import com.kotlin.skiservice.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v0.1/users")
class UserController(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @PostMapping
    fun createUser(@RequestBody req: CreateUserRequest): User {
        val user = User(
            username = req.username,
            password = passwordEncoder.encode(req.password),
            roles = req.roles
        )
        return userRepository.save(user)
    }

    @GetMapping
    fun getAllUsers(): List<User> = userRepository.findAll()
}
