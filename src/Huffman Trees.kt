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
                /*
                for (i in currentWord.toList()){
                    wordCounter[i.toString()] = wordCounter.getOrDefault(i.toString(),0) + 1
                    wordCount++
                }

            */
                wordCounter[currentWord] = wordCounter.getOrDefault(currentWord,0) + 1
                wordCount++
            }
        }
    }

    override fun buildCode() {
        val wordFrequency = wordCounter.keys.map { Pair(it,wordCounter[it]!!/wordCount) }.sortedBy { it.second }.toMutableList()
        println(wordFrequency)
        println(wordCount)
        val holdingRoots = mutableListOf<BinaryHuffMan>()
        val wordToNode = mutableMapOf<String,BinaryHuffMan>()

        wordFrequency.forEach { val temp = BinaryHuffMan(it.second,it.first)
            wordToNode[it.first] = temp
            holdingRoots.add(temp) }

        holdingRoots.sortByDescending { it.freqValue }

        while (holdingRoots.size>1){
            val p1 = holdingRoots.removeAt(holdingRoots.size-1)
            val p2 = holdingRoots.removeAt(holdingRoots.size-1)
            val combinedFreq = p1.freqValue+p2.freqValue
            val root = BinaryHuffMan(combinedFreq)
            root.addLeft(p1)
            root.addRight(p2)
            p2.changeBit()
            holdingRoots.add(root)
            holdingRoots.sortByDescending { it.freqValue }
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
        val openFile = Scanner(File(fileLocation))
        val file = File("C:\\Users\\APhan\\Desktop\\Testerr.txt")
        file.writeText("")
        while (openFile.hasNextLine()){
            val wordList = openFile.nextLine().toLowerCase().split(" ").toMutableList()
            for (i in wordList) {

                if (i != "") {
                    /*
                    for (x in i.replace(Regex("\\W+|[0-9]|_+"), "")) {
                        file.appendText(wordCode[x.toString()].toString())
                        println(wordCode[x.toString()].toString())
                    }
                    file.appendText(" ")
                }
                */
                    file.appendText(wordCode[i.replace(Regex("\\W+|[0-9]|_+"), "")].toString())
                    println(wordCode[i.replace(Regex("\\W+|[0-9]|_+"), "")])
                    file.appendText(" ")
                }
            }
            file.appendText("\n")
        }
    }
}

fun main(args: Array<String>) {
    val t = Huffman()

    t.readFile("C:\\Users\\APhan\\Desktop\\41.txt")
    t.buildCode()
    println("Done Coding")
    t.encode("C:\\Users\\APhan\\Desktop\\41.txt")
    println("Done Encoding")
}