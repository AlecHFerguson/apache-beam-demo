package ai.alec.deeppow.exercises

import kotlin.math.min

fun main() {
    MaxArea.maxArea(
        intArrayOf(8, 10, 14, 0, 13, 10, 9, 9, 11, 11)
    )
}

object MaxArea {
    fun maxArea(height: IntArray): Int {
        if (height.size < 2) {
            return 0
        }
        var leftPointer = 0
        var rightPointer = height.lastIndex
        var maxArea = 0

        while (leftPointer < rightPointer) {
            val area = getArea(height, leftPointer, rightPointer)
            if (area > maxArea) {
                maxArea = area
            }
            if (height[leftPointer] < height[rightPointer]) {
                leftPointer += 1
            } else {
                rightPointer -= 1
            }
        }
        return maxArea
    }

    private fun getArea(heightArray: IntArray, leftIndex: Int, rightIndex: Int): Int {
        val height = min(heightArray[leftIndex], heightArray[rightIndex])
        val width = rightIndex - leftIndex
        return height * width
    }
}
