def naive(pattern: str, s: str, /) -> list[tuple[int, str]]:
    if not (m:=len(pattern)) or not (n:=len(s)) or m > n: return []
    add = (u:=[]).append
    r = pattern[::-1]

    # end = n - (m | m < n) + 1; distance of slicing can go...
    for _ in range(n - m + 1):
        if s[_ : _ + m] == pattern: add((_ + 1, "LR"))
    for _ in range(n - m + 1):
        if s[_ : _ + m] == r: add((_ + m, "RL"))

    return u