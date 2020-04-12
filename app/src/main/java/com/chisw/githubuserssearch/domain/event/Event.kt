package com.chisw.githubuserssearch.domain.event

abstract class Event<T>(private val data: T) {
    private var isInvoked = false

    operator fun invoke(action: (T) -> Unit) {
        if (isInvoked) action(data)
        isInvoked = true
    }

    override fun toString() = PATTERN.format(
            this::class.java.superclass?.simpleName ?: EVENT,
            this::class.java.simpleName,
            data.toString()
    )

    companion object {
        private const val PATTERN = "%s: %s(data=%s)"
        private const val EVENT = "Event"
    }
}