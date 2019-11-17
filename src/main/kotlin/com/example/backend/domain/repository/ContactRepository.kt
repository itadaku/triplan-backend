package com.example.backend.domain.repository

import com.example.backend.domain.models.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactRepository : JpaRepository<Contact, Int> {

}