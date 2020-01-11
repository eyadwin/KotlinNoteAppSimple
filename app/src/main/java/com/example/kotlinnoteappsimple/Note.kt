package com.example.kotlinnoteappsimple

class Note(id: Int, title: String, content: String) {

    var id: Int? = null
    var title: String? = null
    var content: String? = null

    init {
        this.id = id
        this.title = title
        this.content = content
    }

}