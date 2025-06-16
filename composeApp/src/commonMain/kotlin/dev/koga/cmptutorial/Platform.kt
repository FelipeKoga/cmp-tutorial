package dev.koga.cmptutorial

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform