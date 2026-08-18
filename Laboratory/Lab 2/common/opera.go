package common

import "workspacelb2/common/core"

func precedence(operator rune) int {
	switch operator {
		case '+', '-': return 1
		case '*', '/': return 2
	}
	return 0
}

func eval(operator rune, b int, a int) int {
	switch operator {
		case '+': return a + b
		case '-': return a - b
		case '*': return a * b
		case '/':
			if b == 0 { panic("division by 0") }
			return a / b
	}
	return 0
}

func Opera(s string) int {
	operands := &core.Stack[int]{}
	operators := &core.Stack[rune]{}

	operatorEvaluation := func() {
		op, _ := operators.Pop()
		b, _ := operands.Pop()
		a, _ := operands.Pop()
		r := eval(rune(op), int(b), int(a))
		operands.Push(r)
	}

	for i := 0; i < len(s); i++ {
		switch c := s[i]; {
			case c == ' ': continue

			case c >= '0' && c <= '9':
				n := int(c - '0')
				for i+1 < len(s) && s[i+1] >= '0' && s[i+1] <= '9' {
					i++
					n = n*10 + int(s[i]-'0')
				}
				operands.Push(n)

			case c == '(': operators.Push(rune(c))

			case c == ')':
				for !operators.IsEmpty() && operators.Peek() != '(' {
					operatorEvaluation()
				}
				operators.Pop()

			case c == '+', c == '-', c == '*', c == '/':
				for !operators.IsEmpty() && precedence(rune(operators.Peek())) >= precedence(rune(c)) { operatorEvaluation() }
				operators.Push(rune(c))
		}
	}

	for !operators.IsEmpty() { operatorEvaluation() }

	r, _ := operands.Pop()
	return int(r)
}
