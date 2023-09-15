package com.example.project_gearup.models

class Review (
    var id: String,
    var user: String,
    var title: String,
    var review: String
) {
    override fun toString(): String {
        return "$user\n$title\n$review"
    }
}