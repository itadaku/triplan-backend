package com.example.backend.domain.service.impl

import com.example.backend.domain.models.Line
import com.example.backend.domain.repository.LineRepository
import com.example.backend.domain.service.LineService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LineServiceImpl : LineService {
    @Autowired
    lateinit var lineRepository : LineRepository

    fun findByName(name: String?) : List<Line> {
        return lineRepository.findByName(name)
    }

    fun save(line: Line) : Line {
        lineRepository.save(line)
        return line
    }

    fun deleteUser(id: Int) {
        lineRepository.deleteById(id)
    }

    fun updateUser(line: Line){
        lineRepository.save(line)
    }
}