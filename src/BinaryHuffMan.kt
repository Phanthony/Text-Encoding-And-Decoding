class BinaryHuffMan(freq: Double, character: String? = null){
    var leftNode: BinaryHuffMan? = null
    var rightNode: BinaryHuffMan? = null
    var parentNode: BinaryHuffMan? = null
    var bitValue = 0
    var freqValue = freq
    val wordValue = character

    fun addLeft(child: BinaryHuffMan){
        leftNode = child
        child.parentNode = this
    }
    fun addRight(child: BinaryHuffMan){
        rightNode = child
        child.parentNode = this
    }
    fun changeBit(){
        bitValue = 1
    }

    fun hasParent(): Boolean {
        return (parentNode != null)
    }
}