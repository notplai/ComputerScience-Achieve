package core

type Stack[T any] struct {
	top *Node[T]
	size int
}

func (s *Stack[T]) Push(v T) {
	newNode := &Node[T]{ value: v, next: s.top }

	s.top = newNode
	s.size++
}

func (s *Stack[T]) Pop() (T, bool) {
	var zero T
	if s.IsEmpty() { return zero, false }
	v := s.top.value
	s.top = s.top.next

	return v, true
}

func (s *Stack[T]) Peek() T {
	var zero T
	if s.IsEmpty() { return zero }
	return s.top.value
}

func (s *Stack[T]) IsEmpty() bool { return s.top == nil }
