class BinaryHuffMan(freq: Int){
    var leftNode: BinaryHuffMan? = null
    var rightNode: BinaryHuffMan? = null
    var parentNode: BinaryHuffMan? = null
    var bitValue = 0
    var freqValue = freq
}