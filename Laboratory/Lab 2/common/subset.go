package common

import (
	"strings"
	"workspacelb2/common/core"
)

// SetParser extracts top-level elements by comma, respecting nested braces.
func SetParser(s string) []string {
	s = strings.TrimSpace(s)
	// Remove only the outermost pair of braces
	if strings.HasPrefix(s, "{") && strings.HasSuffix(s, "}") {
		s = s[1 : len(s)-1]
	}
	s = strings.TrimSpace(s)
	if len(s) == 0 {
		return []string{}
	}

	var elements []string
	var current strings.Builder
	depth := 0
	seen := make(map[string]bool)

	for _, r := range s {
		switch r {
		case '{':
			depth++
			current.WriteRune(r)
		case '}':
			depth--
			current.WriteRune(r)
		case ',':
			// Only split on commas at the top level
			if depth == 0 {
				elem := strings.TrimSpace(current.String())
				println(elem)
				if elem != "" && !seen[elem] {
					seen[elem] = true
					elements = append(elements, elem)
				}
				current.Reset()
			} else {
				current.WriteRune(r)
			}
		default:
			current.WriteRune(r)
		}
	}

	if current.Len() > 0 {
		elem := strings.TrimSpace(current.String())
		if elem != "" && !seen[elem] {
			seen[elem] = true
			elements = append(elements, elem)
		}
	}

	return elements
}
// ["{1}"]
func SubSets(set []string) [][]string {
	q := &core.Queue[[]string]{}
	q.Enqueue([]string{}) // [{}]

	for _, elem := range set {
		lSize := q.Size()
		for range lSize {
			curr, _ := q.Dequeue()

			excl := make([]string, len(curr))
			copy(excl, curr)
			q.Enqueue(excl)

			incl := make([]string, len(curr), len(curr)+1)
			copy(incl, curr)
			incl = append(incl, elem)
			q.Enqueue(incl)
		}
	}

	var r [][]string
	for !q.IsEmpty() {
		subset, _ := q.Dequeue()
		r = append(r, subset)
	}
	return r
}

func Subsetf(subsets [][]string) []string {
	formatted := make([]string, len(subsets))
	for i, sub := range subsets {
		formatted[i] = "{" + strings.Join(sub, ", ") + "}"
	}
	return formatted
}