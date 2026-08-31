# Time Complexity O(n)
# Memory Complexity O(n)

def greedy(l: str, k: int, /) -> int:
    g, p = [_ for _, c in enumerate(l) if c == 'G'], [_ for _, c in enumerate(l) if c == 'P']
    m, i, j = 0, 0, 0
    
    while i < len(g) and j < len(p):
        if abs(g[i] - p[j]) <= k: m += 1; i += 1; j += 1
        elif g[i] < p[i]: i += 1
        else: j += 1

    return m