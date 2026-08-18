package common

import (
	"strings"
	"workspacelb2/common/core"
)

func SetParser(s string) []rune {
	s = strings.TrimSpace(s)
	s = strings.Trim(s, "{} \r\n")
	if len(s) == 0 { return []rune{} }

	elem := make([]rune, 0)
	seen := make(map[rune]bool)

	for _, c := range s {
		if c == ',' || c == ' ' || c == '\t' || c == '\n' || c == '\r' { continue }
		if !seen[c] { seen[c] = true; elem = append(elem, c) }
	}
	return elem
}

func SubSets(set []rune) [][]rune {
	q := &core.Queue[[]rune]{}
	q.Push([]rune{})

	for _, elem := range set {
		lSize := q.Size()
		for range lSize {
			curr, _ := q.Pop()

			excl := make([]rune, len(curr))
			copy(excl, curr)
			q.Push(excl)

			incl := make([]rune, len(curr), len(curr)+1)
			copy(incl, curr)
			incl = append(incl, elem)
			q.Push(incl)
		}
	}

	var r [][]rune
	for !q.IsEmpty() {
		subset, _ := q.Pop()
		r = append(r, subset)
	}
	return r
}

func Subsetf(subsets [][]rune) []string {
	formatted := make([]string, len(subsets))
	for i, sub := range subsets {
		parts := make([]string, len(sub))
		for j, r := range sub {
			parts[j] = string(r)
		}
		formatted[i] = "{" + strings.Join(parts, ", ") + "}"
	}
	return formatted
}