package com.example.backend.domain.service.impl

import com.example.backend.domain.models.LineStation
import com.example.backend.domain.repository.LineStationRepository
import com.example.backend.domain.service.LineStationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LineStationServiceImpl : LineStationService {
    @Autowired
    lateinit var lineStationRepository : LineStationRepository

    fun findByLineId(lineId: Int?) : List<LineStation> {
        return lineStationRepository.findByLineId(lineId)
    }

    fun save(lineStation: LineStation) : LineStation {
        lineStationRepository.save(lineStation)
        return lineStation
    }

    fun deleteUser(id: Int) {
        lineStationRepository.deleteById(id)
    }

    fun updateUser(lineStation: LineStation){
        lineStationRepository.save(lineStation)
    }
}