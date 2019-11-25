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

    fun findById(id: Integer) : Prefecture {
        return prefectureRepository.findById(id.toInt()).get()
    }
}