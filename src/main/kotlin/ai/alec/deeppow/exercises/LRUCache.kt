package ai.alec.deeppow.exercises

class LRUCache<Key, Value>(val maxEntries: Int = 50000) {
    private val cache = mutableMapOf<Key, Value>()

    fun get(key: Key): Value? {
        val foundValue = cache[key]
        cache.iterator().next()
        return foundValue
    }

    fun put(key: Key, value: Value) {
        cache[key] = value
    }
}
