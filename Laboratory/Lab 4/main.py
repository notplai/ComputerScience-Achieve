import os
from pathlib import Path

from core import brute_force, greedy

# l, k = input().upper(), int(input())
for entry in Path(os.path.join(os.getcwd(), 'example')).glob('*.txt'): 
    if entry.is_file():
        if len(content := entry.read_text(encoding='utf-8').splitlines()) < 2: continue
        l, k = content[0].strip().upper(), int(content[1])
        
        # if not re.match(r"^[GP]+$", l): # using regex to check (fuxkin slow)
        # if not all(char in "GP" for char in l): # using for-all (hmmph... same same)
        # if not set(l).issubset({'G', 'P'}): # using subset to check (ohh! nice man!)
        if not frozenset({'G', 'P'}).issuperset(l): raise TypeError(f"Invalid sequence in {entry.name}: First line must contain 'G' or 'P' only.")
        # using frozenset and superset check instead. (super god tier)
        
        print(f'{entry.name} as {l, k = }')
        print('Brute Force', *brute_force(l, k), sep='\n├ ')
        print(f'Greedy\n╰ {greedy(l, k)}')