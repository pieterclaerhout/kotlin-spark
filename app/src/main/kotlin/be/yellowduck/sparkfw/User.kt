package be.yellowduck.sparkfw

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

data class User(
    val name: String,
    val email: String,
    val id: Int
) {

    fun toJson() : String {
        return jacksonObjectMapper().writeValueAsString(this)
    }

}