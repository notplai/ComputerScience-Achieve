package core

type Node[T any] struct {
	value T
	next *Node[T]
}