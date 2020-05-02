import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val jin = BufferedReader(InputStreamReader(System.`in`))
    for (c in 1..jin.readLine().toInt()) {
        val n = jin.readLine().toInt()
        val middle = StringBuilder()
        val prefixes = mutableListOf<String>()
        val suffixes = mutableListOf<String>()
        for (j in 1..n) {
            val pattern = jin.readLine()
            var ix1 = pattern.indexOf('*')
            val ixL = pattern.lastIndexOf('*')
            prefixes.add(pattern.substring(0, ix1))
            suffixes.add(pattern.substring(ixL + 1))
            if (ix1 < ixL) {
                middle.append(pattern.substring(ix1 + 1, ixL).replace("*", ""))
            }
        }
        prefixes.sortBy { it.length }
        suffixes.sortBy { it.length }
        var works = true
        for (j in 1 until n) {
            if (!prefixes[j].startsWith(prefixes[j - 1])) {
                works = false
            }
            if (!suffixes[j].endsWith(suffixes[j - 1])) {
                works = false
            }
        }
        if (works) {
            println("Case #$c: ${prefixes.last()}$middle${suffixes.last()}")
        } else {
            println("Case #$c: *")
        }
    }
}