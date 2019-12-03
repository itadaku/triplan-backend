package com.example.backend.domain.service.impl

import com.example.backend.domain.models.Element
import com.example.backend.domain.repository.ElementRepository
import com.example.backend.domain.service.ElementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ElementServiceImpl : ElementService{
    @Autowired
    lateinit var elementRepository : ElementRepository

    fun save(element : Element) : Element {
        elementRepository.save(element)
        return element
    }

    fun findByName(name: String) : List<Element> {
        return elementRepository.findByName(name)
    }
}