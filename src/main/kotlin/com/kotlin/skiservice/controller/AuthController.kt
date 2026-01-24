package com.kotlin.skiservice.controller

import com.kotlin.skiservice.dto.auth.AuthRequest
import com.kotlin.skiservice.service.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v0.1/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService,
    private val userDetailsService: UserDetailsService
) {

    @PostMapping("/login")
    fun login(@RequestBody req: AuthRequest): String {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(req.username, req.password)
        )
        val user = userDetailsService.loadUserByUsername(req.username)
        return jwtService.generateToken(user)
    }

    @PostMapping("/validate")
    fun validateToken(@RequestBody token: String): Boolean {
        return try {
            jwtService.extractUsername(token)
            true
        } catch (e: Exception) {
            false
        }
    }
}
