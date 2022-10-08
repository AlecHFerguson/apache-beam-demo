package ai.alec.deeppow.exercises

object Parens {
    fun isValid(s: String): Boolean {
        val parenChain = mutableListOf<Paren>()

        s.forEach { char ->
            when (char.toString()) {
                "(" -> {
                    parenChain.add(Parenthesis)
                }
                "[" -> {
                    parenChain.add(Bracket)
                }
                "{" -> {
                    parenChain.add(Brace)
                }
                ")" -> {
                    if (parenChain.lastOrNull() is Parenthesis) {
                        parenChain.removeAt(parenChain.lastIndex)
                    } else {
                        return false
                    }
                }
                "]" -> {
                    if (parenChain.lastOrNull() is Bracket) {
                        parenChain.removeAt(parenChain.lastIndex)
                    } else {
                        return false
                    }
                }
                "}" -> {
                    if (parenChain.lastOrNull() is Brace) {
                        parenChain.removeAt(parenChain.lastIndex)
                    } else {
                        return false
                    }
                }
                else -> {
                    return false
                }
            }
        }
        return parenChain.isEmpty()
    }

    private sealed interface Paren

    private object Parenthesis : Paren
    private object Bracket : Paren
    private object Brace : Paren
}
