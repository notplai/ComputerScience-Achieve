package common

import (
	"strings"

	"workspacelb2/common/core"
)

var pk = map[rune]rune{
	')': '(',
	']': '[',
	'}': '{',
}

func Parenthese(s string) bool {
	stack := &core.Stack[rune]{}
	for _, elem := range strings.Split(s, "\n") {
		runes := []rune(elem)
		var quote rune
		isString := false
		escaped := false

	lineLoop:
		for i := 0; i < len(runes); i++ {
			c := runes[i]
			if isString {
				switch {
				case escaped:
					escaped = false
				case c == '\\':
					escaped = true
				case c == quote:
					isString = false
				}
				continue
			}

			switch c {
				case '#':
					break lineLoop // rest of line is a comment
				case '"', '\'':
					isString = true
					quote = c
				case '(', '[', '{':
					stack.Push(c)
				case ')', ']', '}':
					t, k := stack.Pop()
					if !k || t != pk[c] { return false }
			}
		}
	}

	return stack.IsEmpty()
}