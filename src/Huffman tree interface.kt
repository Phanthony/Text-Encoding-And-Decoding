interface HuffManTree{
    fun readFile(fileLocation: String)

    fun decode(fileLocation: String)

    fun encode(fileLocation: String)

    fun buildCode()
}