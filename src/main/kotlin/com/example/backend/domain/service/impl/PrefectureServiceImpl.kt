package com.example.backend.domain.service.impl

import com.example.backend.domain.models.Prefecture
import com.example.backend.domain.repository.PrefectureRepository
import com.example.backend.domain.service.PrefectureService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PrefectureServiceImpl : PrefectureService{
    @Autowired
    lateinit var prefectureRepository: PrefectureRepository

    fun save(prefecture: Prefecture) : Prefecture {
        prefectureRepository.save(prefecture)
        return prefecture
    }

    fun deleteAll() {
        prefectureRepository.deleteAll()
    }

    fun findById(id: Int) : Prefecture {
        return prefectureRepository.findById(id).get()
    }

    fun findAll() : List<Prefecture> {
        return prefectureRepository.findAll()
    }
}