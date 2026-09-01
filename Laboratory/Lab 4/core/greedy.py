def greedy(l: str, k: int, /) -> int:
    # create list g and p to store char g and p: it using more memory allocation
    # g, p = [_ for _, c in enumerate(l) if c == 'G'], [_ for _, c in enumerate(l) if c == 'P']
    # m, i, j = 0, 0, 0 # m is max, i is index of G, j is index of P

    # while i < len(g) and j < len(p):
    #     if abs(g[i] - p[j]) <= k: m+=1; i+=1; j+=1
    #     elif g[i] < p[j]: i+=1
    #     else: j+=1

    # No memory allocation, C traversel, single scan per pointer
    m, g, p = 0, l.find('G'), l.find('P')
    while g != -1 and p != -1:
        # who da fxckin using absolute?!
        # if abs(g - p) <= k: m+=1
        # elif g < p: p = l.find('P', p+1)
        # else: g = l.find('G', g+1)

        # Js using condition >, <. to extract absolute function.
        # if g < p:
        #     if p - g <= k:
        #         m += 1
        #         g = l.find('G', g + 1)
        #         p = l.find('P', p + 1)
        #     else:  # g is too far left
        #         g = l.find('G', g + 1)
        # else:  # g >= p
        #     if g - p <= k:
        #         m += 1
        #         g = l.find('G', g + 1)
        #         p = l.find('P', p + 1)
        #     else:  # p is too far left
        #         p = l.find('P', p + 1)

        # simpify more...
        # |g - p| <= k <=> -k <= g - p <= k
        if -k <= g - p <= k: m, g, p = m + 1, l.find('G', g + 1), l.find('P', p + 1)
        elif g < p: g = l.find('G', g + 1) # g is too far left to catch p
        else: p = l.find('P', p + 1) # p is too far left to catch g
    
    return m