package com.example.backend.domain.service.impl

import com.example.backend.domain.models.Contact
import com.example.backend.domain.repository.ContactRepository
import com.example.backend.domain.service.ContactService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class ContactServiceImpl : ContactService{
    @Autowired
    lateinit var contactRepository : ContactRepository

    fun save(contact: Contact) : Contact {
        contactRepository.save(contact)
        return contact
    }

}