package com.arty.data.model

data class BookAuthorEntity(val id: String, val name: String) {
    constructor(name: String) : this(name.hashCode().toString(), name)
}
