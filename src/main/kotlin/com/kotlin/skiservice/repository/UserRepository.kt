package com.kotlin.skiservice.repository

import com.kotlin.skiservice.entities.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByUsername(username: String): User?

}