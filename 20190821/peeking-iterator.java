// 284. Peeking Iterator
// DescriptionHintsSubmissionsDiscussSolution
//
// Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().
//
// Example:
//
// Assume that the iterator is initialized to the beginning of the list: [1,2,3].
//
// Call next() gets you 1, the first element in the list.
// Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
// You call next() the final time and it returns 3, the last element.
// Calling hasNext() after that should return false.
//
// Follow up: How would you extend your design to be generic and work with all types, not just integer?
// Seen this question in a real interview before?
//
//     Difficulty:Medium


// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

import java.util.NoSuchElementException;

class PeekingIterator implements Iterator<Integer> {

    // 因为要有一个 peek， 所以不能把它拿出来（next()）， 所以用个 variable cache 住

    private Iterator<Integer> it;
    private Integer next;
    private boolean noMoreElement; // 考虑到本身的 input 中会有 null， 所以不通过查看 next 是否为 null 来判断是否 hasNext(), 而是另用一个 boolean

	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    it = iterator;
        myIterator();
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        return next;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    if (noMoreElement) {
            throw new NoSuchElementException();
        }
        Integer result = next;
        myIterator();
        return result;
	}

	@Override
	public boolean hasNext() {
	    return !noMoreElement;
	}

    private void myIterator() {
        if (it.hasNext()) {
            noMoreElement = false;
            next = it.next();
        } else {
            noMoreElement = true;
        }
    }
}
