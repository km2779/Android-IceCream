package com.example.icecreamapp

import java.io.Serializable

class OrderItem(var date: String, var flavor: String, var size: String, var cost: String) : Serializable {
    val info: String
        get() = date + flavor + size + cost

    fun setInfo(date: String, flavor: String, size: String, cost: String) {
        this.date = date
        this.flavor = flavor
        this.size = size
        this.cost = cost
    }

    override fun toString(): String {
        return date + flavor + size + cost
    }

}