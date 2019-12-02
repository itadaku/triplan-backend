package com.example.backend

import com.example.backend.util.GetPrefectureData
import com.example.backend.util.GetStation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Persistence

@SpringBootApplication
class TriplanBackendApplication{
	@Autowired
	lateinit var getStation : GetStation

	@Autowired
	lateinit var getPrefecture : GetPrefectureData

	fun execStartup(){
		getStation.getData()
		getPrefecture.getData()
	}
}
fun main(args: Array<String>) {
	val ctx = runApplication<TriplanBackendApplication>(*args)
	val app = ctx.getBean(TriplanBackendApplication::class.java)
	app.execStartup()
}

