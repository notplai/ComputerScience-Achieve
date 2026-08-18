import java.io.File
import kotlin.math.sqrt

data class GCDResult(val gcd: Long, val operations: Long)

fun main() {
    val dir = File("src/example")
    val dataList: List<Pair<Long, Long>> = dir
        .listFiles { file -> file.isFile }
        ?.flatMap { file ->
            file.readLines()
                .filter { it.isNotBlank() }
                .map { line ->
                    val (first, second) = line.split(",").map { it.trim().toLong() }
                    first to second
                }
        } ?: emptyList()

    for ((m, n) in dataList) {
        println("m= $m, n= $n")
        val r1 = findGCD1(m, n)
        val r2 = findGCD2(m, n)
        val r3 = findGCD3(m, n)
        println("  GCD1 (naive):  gcd=${r1.gcd}, operations=${r1.operations}")
        println("  GCD2 (sieve):  gcd=${r2.gcd}, operations=${r2.operations}")
        println("  GCD3 (Euclid): gcd=${r3.gcd}, operations=${r3.operations}")
    }
}

/** Exact integer exponentiation for Long. */
fun longPow(base: Long, exp: Int): Long {
    var r = 1L
    repeat(exp) { r *= base }
    return r
}

/**
 * Trial-division prime factorization.
 * Returns the factor list plus the number of division/step tests performed.
 */
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
 * Calculates GCD from prime factor frequency maps.
 */
fun gcdFromFactors(fn: Map<Long, Int>, fm: Map<Long, Int>): Pair<Long, Long> {
    var gcd = 1L
    var ops = 0L

    for ((pm, nm) in fm) {
        ops++
        val nn = fn[pm]
        if (nn != null) {
            gcd *= longPow(pm, minOf(nm, nn))
        }
    }
    return Pair(gcd, ops)
}

// Naive Solution (Trial Division)
fun findGCD1(m: Long, n: Long): GCDResult {
    val (fmList, opsM) = primeFactors(m)
    val (fnList, opsN) = primeFactors(n)
    val fm = fmList.groupingBy { it }.eachCount()
    val fn = fnList.groupingBy { it }.eachCount()

    val (gcd, ops) = gcdFromFactors(fn, fm)
    return GCDResult(gcd, opsM + opsN + ops)
}

/**
 * Sieve of Eratosthenes up to sqrt(max(m, n)).
 * Limits memory footprint to < 4MB, preventing OutOfMemoryError on large inputs.
 */
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
        if (isPrime[i]) {
            primes.add(i.toLong())
        }
    }
    return Pair(primes, ops)
}

/**
 * Factorization using precomputed primes from the Sieve.
 * Only tests prime divisors, avoiding composite checks.
 */
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

// Sieve-assisted Factorization GCD
fun findGCD2(m: Long, n: Long): GCDResult {
    val limit = sqrt(maxOf(m, n).toDouble()).toInt() + 1
    val (primes, sieveOps) = sievePrimes(limit)
    val (fmList, opsM) = primeFactorsSieve(m, primes)
    val (fnList, opsN) = primeFactorsSieve(n, primes)
    val fm = fmList.groupingBy { it }.eachCount()
    val fn = fnList.groupingBy { it }.eachCount()

    val (gcd, ops) = gcdFromFactors(fn, fm)
    return GCDResult(gcd, sieveOps + opsM + opsN + ops)
}

/**
 * Euclidean Algorithm.
 * Returns the GCD plus the number of modulo steps performed.
 */
fun findGCD3(m: Long, n: Long): GCDResult {
    var a = m
    var b = n
    var ops = 0L
    while (a != 0L && b != 0L) {
        if (a == b) return GCDResult(a, ops)
        else if (a > b) {
            a %= b
            ops++
        } else {
            b %= a
            ops++
        }
    }
    return GCDResult(if (a == 0L) b else a, ops)
}