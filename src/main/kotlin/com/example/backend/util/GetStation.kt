package com.example.backend.util

import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.example.backend.domain.models.Line
import com.example.backend.domain.models.LineStation
import com.example.backend.domain.models.Station
import com.example.backend.domain.service.impl.StationServiceImpl
import com.example.backend.domain.service.impl.LineServiceImpl
import com.example.backend.domain.service.impl.LineStationServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import java.util.Date


data class StationLineModel(var line_cd:Int, var line_name: String, var line_lon:Float, var line_lat:Float, var line_zoom:Int, var station_l:Array<StationModel>)
data class StationModel(var station_cd:Int, var station_g_cd:Int, var station_name:String, var lon:Float, var lat:Float)
@Component
class GetStation {
    @Autowired
    lateinit var stationServiceImpl: StationServiceImpl
    @Autowired
    lateinit var lineServiceImpl: LineServiceImpl
    @Autowired
    lateinit var lineStationServiceImpl: LineStationServiceImpl

    fun saveLines(stationName: String) {
        var line = Line()
        line.name = stationName
        line.updatedAt = Date()
        line.createdAt = Date()
        lineServiceImpl.save(line)
    }

    fun saveStations(stations: StationLineModel){
        for (stationA in stations.station_l) {
            if (stationServiceImpl.findByName(stationA.station_name).isEmpty()) {
                var station = Station()
                station.name = stationA.station_name
                station.updatedAt = Date()
                station.createdAt = Date()
                stationServiceImpl.save(station)
            }

            var lineId = lineServiceImpl.findByName(stations.line_name)[0].id
            var stationId = stationServiceImpl.findByName(stationA.station_name)[0].id
            saveLineStation(lineId,stationId)
        }
    }

    fun saveLineStation(lineId: Int?, stationId: Int?){
        var lineStation = LineStation()
        lineStation.lineId = lineId
        lineStation.stationId = stationId
        lineStation.createdAt = Date()
        lineStation.updatedAt = Date()
        lineStationServiceImpl.save(lineStation)
    }

    @PostConstruct
    fun getData() {
        var reqBaseUrl = "http://www.ekidata.jp/api/l/"
        val reqJson: List<String> = listOf(
                "11101.json","11102.json","11103.json","11104.json","11105.json",
                "11106.json","11107.json","11108.json","11109.json","11110.json",
                "11111.json","11112.json","11113.json","11114.json","11115.json",
                "11116.json","11117.json","11118.json","11119.json","11202.json",
                "11203.json","11204.json","11205.json","11206.json","11207.json",
                "11208.json","11209.json","11210.json","11211.json","11212.json",
                "11213.json","11214.json","11215.json","11216.json","11217.json",
                "11218.json","11219.json","11220.json","11221.json","11222.json",
                "11223.json","11224.json","11225.json","11226.json","11227.json",
                "11228.json","11229.json","11230.json","11231.json","11301.json",
                "11302.json","11303.json","11304.json","11305.json","11306.json",
                "11307.json","11308.json","11309.json","11311.json","11312.json",
                "11313.json","11314.json","11315.json","11316.json","11317.json",
                "11318.json","11319.json","11320.json","11321.json","11322.json",
                "11323.json","11324.json","11325.json","11326.json","11327.json",
                "11328.json","11329.json","11330.json","11331.json","11332.json",
                "11333.json","11334.json","11335.json","11337.json","11338.json",
                "11339.json","11340.json","11341.json","11342.json","11343.json",
                "11401.json","11402.json","11403.json","11404.json","11405.json",
                "11406.json","11407.json","11408.json","11409.json","11410.json",
                "11411.json","11412.json","11413.json","11414.json","11415.json",
                "11416.json","11417.json","11418.json","11419.json","11421.json",
                "11422.json","11501.json","11502.json","11503.json","11504.json",
                "11505.json","11506.json","11507.json","11508.json","11509.json",
                "11510.json","11511.json","11512.json","11513.json","11601.json",
                "11602.json","11603.json","11605.json","11607.json","11608.json",
                "11609.json","11610.json","11611.json","11612.json","11613.json",
                "11614.json","11615.json","11616.json","11617.json","11618.json",
                "11622.json","11623.json","11624.json","11625.json","11626.json",
                "11627.json","11628.json","11629.json","11630.json","11631.json",
                "11632.json","11633.json","11634.json","11635.json","11636.json",
                "11637.json","11639.json","11640.json","11641.json","11701.json",
                "11702.json","11703.json","11704.json","11705.json","11706.json",
                "11707.json","11708.json","11709.json","11710.json","11713.json",
                "11714.json","11715.json","11716.json","11717.json","11720.json",
                "11721.json","11722.json","11723.json","11724.json","11801.json",
                "11802.json","11803.json","11804.json","11805.json","11806.json",
                "11807.json","11808.json","11901.json","11902.json","11903.json",
                "11904.json","11905.json","11906.json","11907.json","11908.json",
                "11909.json","11910.json","11911.json","11912.json","11913.json",
                "11914.json","11915.json","11916.json","11917.json","11918.json",
                "11920.json","11921.json","11922.json","11923.json","11924.json",
                "11925.json","11926.json","11927.json","11928.json","11929.json",
                "11930.json","21001.json","21002.json","21003.json","21004.json",
                "21005.json","21006.json","21007.json","21008.json","21009.json",
                "21010.json","21011.json","21012.json","22001.json","22002.json",
                "22003.json","22004.json","22005.json","22006.json","22007.json",
                "22008.json","22009.json","22010.json","22011.json","22012.json",
                "23001.json","23002.json","23003.json","23004.json","23005.json",
                "23006.json","24001.json","24002.json","24003.json","24004.json",
                "24005.json","24006.json","24007.json","25001.json","25002.json",
                "25003.json","26001.json","26002.json","26003.json","26004.json",
                "26005.json","26006.json","26007.json","26008.json","27001.json",
                "27002.json","27003.json","27004.json","27005.json","28001.json",
                "28002.json","28003.json","28004.json","28005.json","28006.json",
                "28008.json","28009.json","28010.json","29001.json","29002.json",
                "30001.json","30002.json","30003.json","30004.json","30005.json",
                "30006.json","30007.json","30008.json","30009.json","30010.json",
                "30011.json","30012.json","30013.json","30014.json","30015.json",
                "30016.json","30017.json","30018.json","30020.json","30021.json",
                "31001.json","31002.json","31003.json","31005.json","31007.json",
                "31008.json","31009.json","31010.json","31011.json","31012.json",
                "31013.json","31014.json","31015.json","31016.json","31017.json",
                "31018.json","31019.json","31020.json","31021.json","31022.json",
                "31023.json","31024.json","31025.json","31026.json","31027.json",
                "32001.json","32002.json","32003.json","32004.json","32005.json",
                "32006.json","32007.json","32008.json","32009.json","33001.json",
                "33002.json","33003.json","33004.json","33005.json","33006.json",
                "33007.json","33008.json","34001.json","34002.json","34003.json",
                "34004.json","34005.json","34006.json","34007.json","34008.json",
                "34009.json","35001.json","35002.json","35003.json","36001.json",
                "36002.json","36003.json","36004.json","99101.json","99102.json",
                "99103.json","99104.json","99105.json","99106.json","99108.json",
                "99201.json","99202.json","99203.json","99205.json","99206.json",
                "99207.json","99208.json","99209.json","99210.json","99211.json",
                "99213.json","99214.json","99215.json","99216.json","99217.json",
                "99218.json","99301.json","99302.json","99303.json","99304.json",
                "99305.json","99306.json","99307.json","99308.json","99309.json",
                "99310.json","99311.json","99312.json","99313.json","99314.json",
                "99315.json","99316.json","99317.json","99318.json","99319.json",
                "99320.json","99321.json","99323.json","99324.json","99325.json",
                "99326.json","99327.json","99328.json","99329.json","99330.json",
                "99331.json","99332.json","99333.json","99334.json","99335.json",
                "99336.json","99337.json","99338.json","99339.json","99340.json",
                "99341.json","99342.json","99343.json","99344.json","99401.json",
                "99402.json","99403.json","99404.json","99405.json","99407.json",
                "99408.json","99409.json","99410.json","99412.json","99413.json",
                "99414.json","99415.json","99416.json","99417.json","99418.json",
                "99419.json","99420.json","99421.json","99422.json","99423.json",
                "99424.json","99425.json","99426.json","99427.json","99501.json",
                "99502.json","99503.json","99504.json","99505.json","99506.json",
                "99507.json","99508.json","99509.json","99510.json","99511.json",
                "99512.json","99513.json","99514.json","99515.json","99516.json",
                "99517.json","99518.json","99520.json","99521.json","99523.json",
                "99524.json","99525.json","99526.json","99528.json","99529.json",
                "99530.json","99531.json","99532.json","99601.json","99602.json",
                "99603.json","99604.json","99605.json","99606.json","99607.json",
                "99608.json","99609.json","99610.json","99611.json","99612.json",
                "99613.json","99614.json","99615.json","99616.json","99617.json",
                "99618.json","99619.json","99620.json","99621.json","99622.json",
                "99623.json","99624.json","99625.json","99626.json","99627.json",
                "99628.json","99629.json","99630.json","99631.json","99632.json",
                "99633.json","99634.json","99635.json","99636.json","99637.json",
                "99638.json","99640.json","99643.json","99644.json","99645.json",
                "99646.json","99647.json","99648.json","99649.json","99650.json",
                "99651.json","99652.json","99653.json","99701.json","99702.json",
                "99703.json","99704.json","99705.json","99706.json","99707.json",
                "99708.json","99709.json","99710.json","99711.json","99712.json",
                "99713.json","99714.json","99715.json","99716.json","99717.json",
                "99718.json","99801.json","99802.json","99803.json","99804.json",
                "99805.json","99806.json","99807.json","99808.json","99809.json",
                "99810.json","99811.json","99812.json","99814.json","99815.json",
                "99816.json","99817.json","99818.json","99819.json","99901.json",
                "99902.json","99903.json","99904.json","99905.json","99906.json",
                "99907.json","99908.json","99909.json","99910.json","99911.json",
                "99912.json","99913.json","99914.json","99915.json","99916.json",
                "99917.json","99918.json","99919.json","99920.json","99921.json",
                "99922.json","99923.json","99925.json","99926.json","99927.json","99928.json"
        )
        for (reqJsonFile in reqJson) {
            (reqBaseUrl + reqJsonFile).httpGet().response { _, response, result ->
                when (result) {
                    is Result.Success -> {
                        var res = String(response.data)
                        var resArr = res.split("\n")
                        var json = resArr[2].removePrefix("xml.data = ")
                        val mapper = jacksonObjectMapper()
                        val stations = mapper.readValue<StationLineModel>(json)

                        saveLines(stations.line_name)
                        //Thread.sleep(100)
                        saveStations(stations)
                    }
                    is Result.Failure -> {
                        println("通信に失敗しました。")
                    }
                }
            }
        }
        println("station saved")
    }
}
