import os
import pathlib

import core


def main():
    def _(file_path: pathlib.Path):
        if len(ln:=[_.strip() for _ in file_path.read_text(encoding="utf-8").splitlines() if _.strip()]) < 4: return
    
        a = "".join(ln[0].split()) # set of alphabet
        m, n = map(int, ln[1].split()) # lenght of pattern: in python is unused
        pattern, s = "".join(ln[2].split()), "".join(ln[3].split())
    
        # assert len(pattern) == m, f"Pattern length mismatch: expected {m}, got {len(pattern)}"
        # assert len(s) == n, f"Text length mismatch: expected {n}, got {len(s)}"
        # assert set(pattern).issubset(a), "Pattern contains invalid characters"
        # assert set(s).issubset(a), "Text contains invalid characters"
    
        print(f"|>------ {file_path.name}")
        print("| ", "".join(map(str, core.pef(pattern))), end=f"\n| KMP: {len(k1:=core.kmp(pattern, s))}\n")
        
        for pos, direction in core.kmp(pattern, s): print(f"| {pos = } {direction = }")
        
        print(f"| Naive: {len(k2:=core.naive(pattern, s))}")
        for pos, direction in core.naive(pattern, s): print(f"| {pos = } {direction = }")

    for entry in pathlib.Path(os.path.join(os.getcwd(), "example")).iterdir():
        if entry.is_dir(): continue
        _(entry)

if __name__ == "__main__": main()

# _WS = str.maketrans("", "", " \t\r\n\v\f")

# def _(path: str, name: str):
#     with open(path, "r", encoding="utf-8") as f:
#         if len(ln := [x for line in f if (x := line.strip())]) < 4: return

#     a = ln[0].translate(_WS)
#     m, n = map(int, ln[1].split())
#     pattern, s = ln[2].translate(_WS), ln[3].translate(_WS)

#     assert len(pattern) == m, f"Pattern length mismatch: expected {m}, got {len(pattern)}"
#     assert len(s) == n, f"Text length mismatch: expected {n}, got {len(s)}"
#     assert set(pattern) <= (alpha := set(a)), "Pattern contains invalid characters"
#     assert set(s) <= alpha, "Text contains invalid characters"

#     kmp_out = "\n".join(f"{pos = } {direction = }" for pos, direction in core.kmp(pattern, s))
#     naive_out = "\n".join(f"{pos = } {direction = }" for pos, direction in core.naive(pattern, s))

#     sys.stdout.write(
#         f"{name}"
#         f"{''.join(map(str, core.pef(pattern)))}\n"
#         f"{kmp_out}\n\n"
#         f"{naive_out}\n\n"
#     )

# def main():
#     for entry in os.scandir("example"):
#         if entry.is_file(): _(entry.path, entry.name)

# if __name__ == "__main__": main()