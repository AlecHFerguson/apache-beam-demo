package ai.alec.deeppow.exercises

fun main() {
    Palindrome.longestPalindrome("aacabdkacaa")
}

object Palindrome {
    fun longestPalindrome(s: String): String {
        val letterMap = mutableMapOf<Char, MutableList<Int>>()
        s.forEachIndexed { index, char ->
            letterMap.getOrPut(char) {
                mutableListOf()
            }.add(index)
        }
        var longest = ""
        letterMap.forEach { (char, indexList) ->
            if (indexList.isNotEmpty()) {
                val palindrome = s.testForIndices(indexList = indexList)
                if (palindrome.length > longest.length) {
                    longest = palindrome
                }
            }
        }
        return longest
    }

    private fun String.testForIndices(indexList: List<Int>): String {
        var longest = ""
        for (startIndex in 0..indexList.lastIndex) {
            for (endIndex in indexList.lastIndex downTo startIndex) {
                val testString = substring(
                    range = indexList[startIndex]..indexList[endIndex]
                )
                if (testString.isPalindrome() && testString.length > longest.length) {
                    longest = testString
                }

            }
        }
        return longest
    }

    private fun String.isPalindrome(): Boolean {
        return this == reversed()
    }
}