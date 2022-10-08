package ai.alec.deeppow.exercises

import ai.alec.deeppow.exercises.LinkedList.sortedListToBST

fun main() {
    val head = listOf(-10, -3, 0, 5, 9).toLinkedList()
    val tree = sortedListToBST(head)
    println(tree)
}

private fun List<Int>.toLinkedList(): LinkedList.ListNode? {
    var list: LinkedList.ListNode? = null
    for (i in lastIndex.downTo(0)) {
        list = LinkedList.ListNode(
            value = get(i),
            next = list
        )
    }
    return list
}

object LinkedList {
    fun sortedListToBST(head: ListNode?): TreeNode? {
        return populateInitialTree(head = head)
    }

    private fun populateInitialTree(head: ListNode?): TreeNode? {
        var treeNode: TreeNode? = null
        var listNode: ListNode? = head
        var processCount = 1

        while (listNode != null) {
            repeat(times = processCount) { iteration ->
                listNode = listNode?.next
                if (listNode != null) {
                    if (iteration == 0) {
                        val oldTreeNode = treeNode
                        treeNode = TreeNode(value = listNode!!.value)
                        if (oldTreeNode != null) {
                            treeNode!!.insert(oldTreeNode)
                        }
                    } else {
                        treeNode!!.insert(
                            TreeNode(value = listNode!!.value)
                        )
                    }
                }
            }
            processCount += 1
        }
        return treeNode
    }

    class ListNode(
        val value: Int,
        var next: ListNode? = null
    )

    class ChainNode(
        val value: TreeNode,
        var next: ChainNode? = null
    )

    class TreeNode(
        val value: Int,
        var height: Int = 1,
        var left: TreeNode? = null,
        var right: TreeNode? = null
    ) {
        fun insert(node: TreeNode, chainNode: ChainNode? = null) {
            val lookupChain = ChainNode(
                value = node,
                next = chainNode
            )
            if (node.value > value) {
                if (right == null) {
                    right = node
                    rebalanceNodes(newNode = node, lookupChain = lookupChain)
                } else {
                    right!!.insert(node = node, chainNode = lookupChain)
                }
            } else if (node.value < value) {
                if (left == null) {
                    left = node
                    rebalanceNodes(newNode = node, lookupChain = lookupChain)
                } else {
                    left!!.insert(node = node, chainNode = lookupChain)
                }
            }
        }

        private fun leafHeights(height: Int, heightList: MutableList<Int>): List<Int> {
            if (left == null && right == null) {
                heightList.add(height)
                return heightList
            }
            val newHeightList = mutableListOf<Int>()
            if (left != null) {
                newHeightList.addAll(
                    leafHeights(
                        height = height + 1,
                        heightList = heightList
                    )
                )
            }
            if (right != null) {
                newHeightList.addAll(
                    leafHeights(
                        height = height + 1,
                        heightList = heightList
                    )
                )
            }
            return newHeightList
        }

        private fun isUnbalanced(): Boolean {
            val leafHeights = leafHeights(height = 0, heightList = mutableListOf())
            var maxHeight: Int? = null
            var minHeight: Int? = null
            leafHeights.forEach {
                if (minHeight == null || it < minHeight!!) {
                    minHeight = it
                }
                if (maxHeight == null || it > maxHeight!!) {
                    maxHeight = it
                }
            }
            return maxHeight == null || minHeight == null || maxHeight!! - minHeight!! > 1
        }

        private fun rebalanceNodes(newNode: TreeNode, lookupChain: ChainNode?) {
            var node: ChainNode? = lookupChain
            while (node != null) {
                if (node.value.isUnbalanced()) {
                    node.value.rebalance(newNode = newNode)
                    return
                }
                node = node.next
            }
        }

        private fun rebalance(newNode: TreeNode) {
            if (newNode.value < value && left != null) {
                // Left Left case
                if (newNode.value < left!!.value) {
                    rightRotate()
                    // Left Right case
                } else if (newNode.value > left!!.value) {
                    left = left!!.leftRotate()
                    rightRotate()
                }
            } else if (newNode.value > value && right != null) {
                // Right Left case
                if (newNode.value < right!!.value) {
                    right = right!!.rightRotate()
                    leftRotate()
                } else if (newNode.value > right!!.value) {
                    rightRotate()
                }
            }
        }

        private fun rightRotate(): TreeNode {
            val x = left
            val tTwo = x!!.right
            x.right = this
            left = tTwo
            return x
        }

        private fun leftRotate(): TreeNode {
            val y = right
            val tTwo = y!!.left
            y.left = this
            right = tTwo
            return y
        }
    }
}
