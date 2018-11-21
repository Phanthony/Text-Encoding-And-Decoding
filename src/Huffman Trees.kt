import java.util.Scanner
import java.io.File

class Huffman : HuffManTree {

    var wordCounter = mutableMapOf<String,Int>()
    var wordCode = mutableMapOf<String,String>()
    var wordCount = 0.0

    override fun readFile(fileLocation: String) {
        val file = Scanner(File(fileLocation))
        while (file.hasNext()) {
            val currentWord = file.next().replace(Regex("\\W+|[0-9]|_+"), "").toLowerCase()
            if (currentWord != "") {
                wordCounter[currentWord] = wordCounter.getOrDefault(currentWord, 0) + 1
                wordCount++
            }
        }
    }

    override fun buildCode() {
        val wordFrequency = wordCounter.keys.map { Pair(it,wordCounter[it]!!/wordCount) }
        val holdingRoots = mutableListOf<BinaryHuffMan>()
        val wordToNode = mutableMapOf<String,BinaryHuffMan>()

        wordFrequency.forEach { val temp = BinaryHuffMan(it.second,it.first)
            wordToNode[it.first] = temp
            holdingRoots.add(temp) }

        holdingRoots.sortedBy { it.freqValue }

        while (holdingRoots.size>1){
            val p1 = holdingRoots.removeAt(holdingRoots.size-1)
            val p2 = holdingRoots.removeAt(holdingRoots.size-1)
            val combinedFreq = p1.freqValue+p2.freqValue
            val root = BinaryHuffMan(combinedFreq)
            root.addLeft(p1)
            root.addRight(p2)
            p2.changeBit()
            holdingRoots.add(root)
            holdingRoots.sortBy { it.freqValue }
        }
        for (i in wordToNode.keys){
            var currentNode = wordToNode[i]
            var code = ""
            while (currentNode!!.hasParent()){
                code = currentNode.bitValue.toString() + code
                currentNode = currentNode.parentNode
            }
            wordCode[i] = code
        }
    }

    override fun decode(fileLocation: String) {

    }

    override fun encode(fileLocation: String) {

    }
}

fun main(args: Array<String>) {
    val t = Huffman()

    t.readFile("C:\\Users\\APhan\\Desktop\\41.txt")
    //println(t.wordCounter)
    t.buildCode()
    println(t.wordCode)
}