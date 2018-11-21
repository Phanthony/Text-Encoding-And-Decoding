class BinaryHuffMan(freq: Double, word: String? =null){
    var leftNode: BinaryHuffMan? = null
    var rightNode: BinaryHuffMan? = null
    var parentNode: BinaryHuffMan? = null
    var bitValue = 0
    var freqValue = freq
    var wordValue: String? = word

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