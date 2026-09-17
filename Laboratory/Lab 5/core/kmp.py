def pef(pattern: str, /) -> list[int]:
    pi = [(k:=0)] * (m:=len(pattern))
    for q in range(1, m):
        _ = pattern[q]
        while k and pattern[k] != _: k = pi[k - 1]
        if k or pattern[0] == _: k += 1
        pi[q] = k
    return pi

# def kmp(pattern: str, s: str, /) -> list[tuple[int, str]]:
#     if not (m:=len(pattern)) or not (n:=len(s)) or m > n: return []
#     pi = pef(pattern)

#     def _(seq, /, pat=pattern, pi=pi, m=m, p0=pattern[0]):
#         point, u = 0, []
#         append = u.append
#         for i, c in enumerate(seq, 1):
#             while point and pat[point] != c: point = pi[point - 1]
#             if point or p0 == c: point += 1
#             if point == m:
#                 append(i - m + 1)
#                 point = pi[point - 1]
#         return u

#     return [(pos, "LR") for pos in _(s)] + [(n - pos + 1, "RL") for pos in _(s[::-1])[::-1]]

# def pef(pattern: str, /) -> list[int]:
#     pi = [(k:=0)] * (m:=len(pattern))

#     for q in range(1, m):
#         _ = pattern[q]
#         while k > 0 and pattern[k] != _: k = pi[k - 1]
#         if pattern[k] == _: k += 1
#         pi[q] = k

#     return pi

# def kmp(pattern: str, s: str, /) -> list[tuple[int, str]]:
#     if not (m:=len(pattern)) or not (n:=len(s)) or m > n: return []
#     pi = pef(pattern)

#     def _(iterable):
#         point, u = 0, []
#         append = u.append
#         for i, c in enumerate(iterable, 1):
#             while point > 0 and pattern[point] != c: point = pi[point - 1]
#             if pattern[point] == c: point += 1
#             if point == m:
#                 append(i - m + 1)
#                 point = pi[point - 1]
#         return u

    # u is LR, v is RL
    # u = [(pos, "LR") for pos in _(s)]
    # WHO DA FXCKING USING SORT?!~
    # O(k) reverse instead of O(k log k) sort
    # v = reversed([(n - pos + 1, "RL") for pos in _(reversed(s))])
    # u.extend(v)
    # return u

def kmp(pattern: str, s: str, /) -> list[tuple[int, str]]:
    if not (m:=len(pattern)) or not (n:=len(s)) or m > n: return []
    pi = pef(pattern)

    def _(iterable):
        point, append = 0, (u:=[]).append
        for i, c in enumerate(iterable, 1):
            while point > 0 and pattern[point] != c: point = pi[point - 1]
            if pattern[point] == c: point += 1
            if point == m:
                append((i - m) % n + 1)
                point = pi[point - 1]
        return u

    clr = s + s[:m - 1]
    crl = (r := s[::-1]) + r[:m - 1]

    return [(pos, "LR") for pos in _(clr)] + [((n - pos) % n + 1, "RL") for pos in _(crl)[::-1]]
    # return [(pos, "LR") for pos in _(s)] + [(n - pos + 1, "RL") for pos in _(s[::-1])[::-1]]