package core

type Queue[T any] struct {
	head *Node[T]
	tail *Node[T]
	size int
}

func (q *Queue[T]) Enqueue(v T) {
	newNode := &Node[T]{ value: v }
	if q.tail == nil {
		q.tail = newNode
		q.head = newNode
	} else {
		q.tail.next = newNode
		q.tail = newNode // change tag holder
	}
	q.size++
}

func (q *Queue[T]) Dequeue() (T, bool) {
	var zero T
	if q.head == nil { return zero, false }
	v := q.head.value
	q.head = q.head.next
	if q.head == nil { q.tail = nil }
	q.size--
	return v, true
}

func (q *Queue[T]) Size() int { return q.size }
func (q *Queue[T]) IsEmpty() bool { return q.head == nil }