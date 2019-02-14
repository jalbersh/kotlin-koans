package i_introduction._1_Java_To_Kotlin_Converter

import util.TODO

fun task1(collection: Collection<Int>): String {
    var s:String = "{"
    for ((index,int) in collection.withIndex()) {
        s += int
        if (index != collection.size-1)
            s += ", "
    }
    s += "}"
    return s
}