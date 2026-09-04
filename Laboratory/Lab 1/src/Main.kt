import java.io.File
import kotlin.math.sqrt

data class GCDResult(val gcd: Long, val operations: Long)

fun main() {
    val dir = File("src/example")
    val dataList: List<List<Long>> = dir
        .listFiles { file -> file.isFile }
        ?.flatMap { file ->
            file.readLines()
                .filter { it.isNotBlank() }
                .map { line ->
                    line.split(",")
                        .map { it.trim().toLong() }
                        .filter { it > 0L }
                }
                .filter { it.isNotEmpty() }
        } ?: emptyList()

    for (numbers in dataList) {
        println("Numbers: ${numbers.joinToString(", ")}")
        val r1 = findGCD1(numbers)
        val r2 = findGCD2(numbers)
        val r3 = findGCD3(numbers)
        println("  GCD1 (naive):  gcd=${r1.gcd}, operations=${r1.operations}")
        println("  GCD2 (sieve):  gcd=${r2.gcd}, operations=${r2.operations}")
        println("  GCD3 (Euclid): gcd=${r3.gcd}, operations=${r3.operations}")
    }
}

// Exact integer exponentiation for Long.
fun longPow(base: Long, exp: Int): Long {
    var r = 1L
    repeat(exp) { r *= base }
    return r
}

// Trial-division prime factorization.
fun primeFactors(n: Long): Pair<List<Long>, Long> {
    var num = n
    val factors = mutableListOf<Long>()
    var d = 2L
    var ops = 0L
    while (d * d <= num) {
        while (num % d == 0L) {
            ops++
            factors.add(d)
            num /= d
        }
        ops++
        d++
    }
    if (num > 1L) {
        factors.add(num)
    }
    return Pair(factors, ops)
}

/**
 * Calculates GCD from a list of prime factor frequency maps.
 * Finds prime factors common to all numbers and uses their minimum exponent.
 */
fun gcdFromFactorMaps(maps: List<Map<Long, Int>>): Pair<Long, Long> {
    if (maps.isEmpty()) return Pair(1L, 0L)
    if (maps.size == 1) {
        val singleGcd = maps[0].entries.fold(1L) { acc, (p, exp) -> acc * longPow(p, exp) }
        return Pair(singleGcd, 0L)
    }

    var gcd = 1L
    var ops = 0L
    val baseMap = maps.minByOrNull { it.size } ?: maps[0]

    for ((p, count) in baseMap) {
        ops++
        var minCount = count
        var presentInAll = true

        for (map in maps) {
            if (map === baseMap) continue
            ops++
            val c = map[p]
            if (c == null) {
                presentInAll = false
                break
            }
            if (c < minCount) {
                minCount = c
            }
        }

        if (presentInAll) {
            gcd *= longPow(p, minCount)
        }
    }
    return Pair(gcd, ops)
}

// Naive Solution (Trial Division for N numbers)
fun findGCD1(numbers: List<Long>): GCDResult {
    if (numbers.isEmpty()) return GCDResult(0L, 0L)

    var totalFactorOps = 0L
    val factorMaps = numbers.map { n ->
        val (factors, ops) = primeFactors(n)
        totalFactorOps += ops
        factors.groupingBy { it }.eachCount()
    }

    val (gcd, combineOps) = gcdFromFactorMaps(factorMaps)
    return GCDResult(gcd, totalFactorOps + combineOps)
}

// Sieve of Eratosthenes up to limit.
fun sievePrimes(limit: Int): Pair<List<Long>, Long> {
    if (limit < 2) return Pair(emptyList(), 0L)
    val isPrime = BooleanArray(limit + 1) { true }
    isPrime[0] = false
    isPrime[1] = false

    var ops = 0L
    var p = 2
    while (p.toLong() * p <= limit) {
        if (isPrime[p]) {
            var j = p * p
            while (j <= limit) {
                ops++
                isPrime[j] = false
                j += p
            }
        }
        p++
    }

    val primes = mutableListOf<Long>()
    for (i in 2..limit) {
        if (isPrime[i]) primes.add(i.toLong())
    }
    return Pair(primes, ops)
}

// Factorization using precomputed primes from the Sieve.
fun primeFactorsSieve(n: Long, primes: List<Long>): Pair<List<Long>, Long> {
    val factors = mutableListOf<Long>()
    var num = n
    var ops = 0L

    for (p in primes) {
        if (p * p > num) break
        while (num % p == 0L) {
            ops++
            factors.add(p)
            num /= p
        }
        ops++
    }
    if (num > 1L) {
        factors.add(num)
    }
    return Pair(factors, ops)
}

// Sieve-assisted Factorization GCD for N numbers
fun findGCD2(numbers: List<Long>): GCDResult {
    if (numbers.isEmpty()) return GCDResult(0L, 0L)

    val maxVal = numbers.maxOrNull() ?: 0L
    val limit = sqrt(maxVal.toDouble()).toInt() + 1
    val (primes, sieveOps) = sievePrimes(limit)

    var totalFactorOps = 0L
    val factorMaps = numbers.map { n ->
        val (factors, ops) = primeFactorsSieve(n, primes)
        totalFactorOps += ops
        factors.groupingBy { it }.eachCount()
    }

    val (gcd, combineOps) = gcdFromFactorMaps(factorMaps)
    return GCDResult(gcd, sieveOps + totalFactorOps + combineOps)
}

// Helper: Pairwise Euclidean GCD with operation counting.
fun euclideanGCD(m: Long, n: Long): Pair<Long, Long> {
    var a = m
    var b = n
    var ops = 0L
    while (a != 0L && b != 0L) {
        if (a == b) return Pair(a, ops)
        if (a > b) {
            a %= b
            ops++
        } else {
            b %= a
            ops++
        }
    }
    return Pair(if (a == 0L) b else a, ops)
}

// Euclidean Algorithm for N numbers
fun findGCD3(numbers: List<Long>): GCDResult {
    if (numbers.isEmpty()) return GCDResult(0L, 0L)
    if (numbers.size == 1) return GCDResult(numbers[0], 0L)

    var currentGcd = numbers[0]
    var totalOps = 0L

    for (i in 1 until numbers.size) {
        val (gcd, ops) = euclideanGCD(currentGcd, numbers[i])
        currentGcd = gcd
        totalOps += ops
        if (currentGcd == 1L) break // Early exit if GCD drops to 1
    }

    return GCDResult(currentGcd, totalOps)
}