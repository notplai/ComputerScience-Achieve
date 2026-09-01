from bitarray import bitarray


def brute_force(l: str, k: int, /) -> tuple[int, int]:
    ms, sol, g, p = 0, 0, [_ for _, c in enumerate(l) if c == 'G'], [_ for _, c in enumerate(l) if c == 'P']
    P = bitarray(len(p)) # initialize bitarray size = p size
    P.setall(0) # set every bits as 0

    def _(gdx: int, cs: int, /) -> None:
        nonlocal ms, sol
        if gdx == len(g):
            if cs > ms: ms = cs; sol = 1
            elif cs == ms and ms > 0: sol+=1
            return

        for pdx in range(len(P)):
            # if not P[pdx] and (g[gdx] - p[pdx] if g[gdx] >= p[pdx] else p[pdx] - g[gdx]) <= k:
            if not P[pdx] and -k <= g[gdx] - p[pdx] <= k:
                # extract absolute function to |g - p| <= k <=> -k <= g - p <= k
                P[pdx] = 1
                _(gdx + 1, cs + 1)
                P[pdx] = 0

        _(gdx + 1, cs)
    _(0,0)
    return sol, ms