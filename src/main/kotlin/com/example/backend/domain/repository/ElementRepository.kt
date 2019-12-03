package com.example.backend.domain.repository

import com.example.backend.domain.models.Element
import org.springframework.data.jpa.repository.JpaRepository

interface ElementRepository : JpaRepository<Element, Int> {
    fun findByName(name: String) : List<Element>
}