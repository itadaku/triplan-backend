package com.example.backend.domain.service.impl

import com.example.backend.domain.models.Station
import com.example.backend.domain.repository.StationRepository
import com.example.backend.domain.service.StationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Date

@Service
class StationServiceImpl : StationService {
    @Autowired
    lateinit var stationRepository : StationRepository

    fun save(station: Station) : Station {
        stationRepository.save(station)
        return station
    }

    fun findById(id: Int?) : List<Station> {
        return stationRepository.findById(id)
    }

    fun findByName(name: String?) : List<Station> {
        return stationRepository.findByName(name)
    }

    fun deleteUser(id: Int) {
        stationRepository.deleteById(id)
    }

    fun updateUser(station: Station){
        stationRepository.save(station)
    }
}