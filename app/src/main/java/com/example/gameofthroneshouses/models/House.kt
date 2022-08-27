package com.example.gameofthroneshouses.models

import com.example.gameofthroneshouses.models.base.IConvertibleTo

data class House(
    val ancestralWeapons: List<String>,
    val cadetBranches: List<Any>,
    val coatOfArms: String,
    val currentLord: String,
    val diedOut: String,
    val founded: String,
    val founder: String,
    val heir: String,
    val name: String,
    val overlord: String,
    val region: String,
    val seats: List<String>,
    val swornMembers: List<Any>,
    val titles: List<String>,
    val url: String,
    val words: String
): IConvertibleTo<House> {
    override fun convertTo() = House (
        this.ancestralWeapons,
        this.cadetBranches,
        this.coatOfArms,
        this.currentLord,
        this.diedOut,
        this.founded,
        this.founder,
        this.heir,
        this.name,
        this.overlord,
        this.region,
        this.seats,
        this.swornMembers,
        this.titles,
        this.url,
        this.words
    )
}