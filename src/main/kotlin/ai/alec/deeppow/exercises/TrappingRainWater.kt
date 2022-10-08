package ai.alec.deeppow.exercises

import kotlin.math.min

fun main() {
    TrappingRainWater.trap(
//        intArrayOf(4, 2, 0, 3, 2, 5)
        intArrayOf(28, 0, 29, 0, 28)
    )
}

object TrappingRainWater {
    fun trap(height: IntArray): Int {
        if (height.size < 3) {
            return 0
        }

        var totalArea = 0
        var (leftBound, rightBound) = findTrapBoundary(startIndex = 0, height = height)
        while (leftBound < height.lastIndex - 2) {
            totalArea += findTrappedWater(height = height, leftBound = leftBound, rightBound = rightBound)
            val newBounds = findTrapBoundary(startIndex = rightBound, height = height)
            leftBound = newBounds.first
            rightBound = newBounds.second
        }
        return totalArea
    }

    private fun findTrapBoundary(startIndex: Int, height: IntArray): Pair<Int, Int> {
        for (leftBound in (startIndex..height.lastIndex - 2)) {
            if (height[leftBound] > height[leftBound + 1]) {
                for (rightBound in (leftBound + 2)..height.lastIndex) {
                    if (height[rightBound - 1] < height[leftBound] &&
                        height[rightBound - 1] < height[rightBound] &&
                        height[rightBound] >= height[leftBound] &&
                        (rightBound == height.lastIndex || height[rightBound] >= height[rightBound + 1])
                    ) {
                        return Pair(leftBound, rightBound)
                    }
                }
            }
        }
        return Pair(height.lastIndex - 1, height.lastIndex)
    }

    private fun findTrappedWater(height: IntArray, leftBound: Int, rightBound: Int): Int {
        val maxHeight = min(height[leftBound], height[rightBound])
        var totalArea = 0
        for (block in (leftBound + 1) until rightBound) {
            if (height[block] < maxHeight) {
                totalArea += maxHeight - height[block]
            }
        }
        return totalArea
    }
}
