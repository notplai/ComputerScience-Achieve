# Time Complexity O(p!)
# Memory Complexity ~O(n)

def brute_force(l: str, k: int, /) -> tuple[int, int]:
    g, p = [_ for _, c in enumerate(l) if c == 'G'], [_ for _, c in enumerate(l) if c == 'P']

    max_state, solution, p_used = 0, 0, [False] * len(p)
    def _(gdx: int, current_state: int, /) -> None:
        nonlocal max_state, solution
        if gdx == len(g):
            if current_state > max_state: max_state = current_state; solution = 1
            elif current_state == max_state and max_state > 0: solution += 1
            return

        for pdx in range(len(p)):
            if not p_used[pdx] and abs(g[gdx] - p[pdx]) <= k:
                p_used[pdx] = True
                _(gdx+1, current_state+1)
                p_used[pdx] = False

        _(gdx+1, current_state)

    _(0, 0)
    return solution, max_state
