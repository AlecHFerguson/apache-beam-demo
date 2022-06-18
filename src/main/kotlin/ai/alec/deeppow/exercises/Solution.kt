package ai.alec.deeppow.exercises

fun main() {
    val result = Solution.findMedianSortedArrays(intArrayOf(1, 3, 5), intArrayOf(2, 4, 6))
    println(result)
}

object Solution {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val newArray = nums1.toMutableList()
        nums2.forEach { element ->
            val insertPos = newArray.binarySearchInsertPosition(value = element)
            newArray.add(
                index = insertPos,
                element = element
            )
        }

        val length = newArray.count()
        if (length % 2 == 0) {
            return (newArray[length / 2] + newArray[(length / 2) - 1]) / 2.0
        }
        return newArray[length / 2].toDouble()
    }

    private fun List<Int>.binarySearchPosition(value: Int, startIndex: Int = 0, endIndex: Int = this.lastIndex): Int {
        if (count() == 1) {
            return startIndex
        }
        val testIndex = lastIndex / 2
        val middleValue = get(testIndex)
        if (value == middleValue) {
            return testIndex + startIndex
        }
        if (value < middleValue) {
            return slice(0..testIndex).binarySearchPosition(
                value = value,
                startIndex = startIndex,
                endIndex = testIndex
            )
        }
        return slice(testIndex..lastIndex).binarySearchPosition(
            value = value,
            startIndex = startIndex + testIndex,
            endIndex = endIndex
        )
    }

    private fun List<Int>.binarySearchInsertPosition(value: Int): Int {
        var startIndex: Int = 0
        var endIndex: Int = lastIndex

        while (startIndex <= endIndex) {
            val testIndex = (endIndex + startIndex) / 2
            val testValue = get(testIndex)
            if (testValue == value) {
                return testIndex
            }
            if (value < testValue) {
                endIndex = testIndex - 1
            } else {
                startIndex = testIndex + 1
            }
        }
        return endIndex + 1
    }
}