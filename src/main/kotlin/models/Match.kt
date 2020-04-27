package models

class Match {

    private lateinit var title: String
    private lateinit var address: String
    private var latitude: Long
    private var altitude: Long
    private lateinit var sport: Sport
    private lateinit var contactWay: String
    private lateinit var description: String
    private var maxOfPlayers: Int
    private var numberOfAssistans: Int

    constructor(title: String, address: String, latitude: Long, altitude: Long, sport: Sport, contactWay: String, description: String, maxOfPlayers: Int, numberOfAssistans: Int) {
        this.title = title
        this.address = address
        this.latitude = latitude
        this.altitude = altitude
        this.sport = sport
        this.contactWay = contactWay
        this.description = description
        this.maxOfPlayers = maxOfPlayers
        this.numberOfAssistans = numberOfAssistans
    }
}