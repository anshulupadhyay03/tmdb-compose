package com.retroent.moviebuff.data.moviedetails

data class KeywordsResponse(
    val id: Int = 0,
    val keywords: List<Keyword> = listOf()
) {
    data class Keyword(
        val id: Int = 0,
        val name: String = ""
    )
}