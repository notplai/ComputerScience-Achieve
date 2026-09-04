from bitarray import bitarray


def brute_force(l: str, k: int, /) -> tuple[list[list[tuple[int, int]]], int, int]:
    g = [i for i, c in enumerate(l) if c == "G"]
    p = [i for i, c in enumerate(l) if c == "P"]
    num_g, num_p = len(g), len(p)

    if not g or not p: return [], 0, 0

    gi = pi = ms = 0
    while gi < num_g and pi < num_p:
        if abs(g[gi] - p[pi]) <= k:
            ms += 1
            gi += 1
            pi += 1
        elif g[gi] < p[pi]: gi += 1
        else: pi += 1

    if ms == 0:
        return [], 0, 0

    candidates = [
        [pdx for pdx, pos in enumerate(p) if abs(g_pos - pos) <= k]
        for g_pos in g
    ]
    P = bitarray(num_p)
    P.setall(0)

    solarr: list[list[tuple[int, int]]] = []
    path: list[tuple[int, int]] = []

    def _(gdx: int, cs: int) -> None:
        if cs + (num_g - gdx) < ms: return

        if gdx == num_g:
            if cs == ms: solarr.append(path.copy())
            return

        for pdx in candidates[gdx]:
            if not P[pdx]:
                P[pdx] = 1
                path.append((g[gdx], p[pdx]))
                _(gdx + 1, cs + 1)
                path.pop()
                P[pdx] = 0

        if cs + (num_g - 1 - gdx) >= ms:
            _(gdx + 1, cs)

    _(0, 0)
    sol = len(solarr)
    return solarr, sol, ms