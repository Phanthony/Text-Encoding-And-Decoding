import java.util.Scanner
import java.io.File

class Huffman : HuffManTree {

    var wordCounter = mutableMapOf<String,Int>()
    var wordCode = mutableMapOf<String,Int>()
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
        val wordFrequency = mutableListOf<Pair<String?, Double>>()
        val keyList = wordCounter.keys

        for (i in keyList) {
            wordFrequency.add(Pair(i, wordCounter[i]!! / wordCount))
        }
        wordFrequency.sortBy { it.second }

    }

    override fun decode(fileLocation: String) {

    }

    override fun encode(fileLocation: String) {

    }
}

fun main(args: Array<String>) {
    val t = Huffman()

    t.readFile("C:\\Users\\APhan\\Desktop\\war.txt")

    println(t.wordCounter)
}