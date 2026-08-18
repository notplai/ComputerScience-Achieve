package common

import "workspacelb2/common/core"

func Parenthese(s string) bool {
	stack := &core.Stack[rune]{}

	pk := map[rune]rune {
		')': '(',
		']': '[',
		'}': '{',
	}

	for _, c := range s {
		switch c {
			case '(', '[', '{': stack.Push(c)
			case ')', ']', '}':
				t, k := stack.Pop()
				if !k || t != pk[c] { return false }
		}
	}

	if !stack.IsEmpty() { return false }
	return true
}
