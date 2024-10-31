package com.zyb.kmmlib

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform