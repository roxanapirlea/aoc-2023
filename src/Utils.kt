import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/inputs", "$name.txt").readLines()

/**
 * Reads text from the given input txt file.
 */
fun readText(fileName: String) =
    File("src/inputs", "$fileName.txt").readText()

/**
 * Reads text from the given input txt file and splits it into a list.
 */
fun readSplitText(fileName: String, separator: String) =
    File("src/inputs", "$fileName.txt").readText().trim().split(separator)

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)
