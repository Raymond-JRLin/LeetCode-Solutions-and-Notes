// 251. Flatten 2D Vector
// DescriptionHintsSubmissionsDiscussSolution
// Design and implement an iterator to flatten a 2d vector. It should support the following operations: next and hasNext.
//
//
//
// Example:
//
// Vector2D iterator = new Vector2D([[1,2],[3],[4]]);
//
// iterator.next(); // return 1
// iterator.next(); // return 2
// iterator.next(); // return 3
// iterator.hasNext(); // return true
// iterator.hasNext(); // return true
// iterator.next(); // return 4
// iterator.hasNext(); // return false
//
//
// Notes:
//
// Please remember to RESET your class variables declared in Vector2D, as static/class variables are persisted across multiple test cases. Please see here for more details.
// You may assume that next() call will always be valid, that is, there will be at least a next element in the 2d vector when next() is called.
//
//
// Follow up:
//
// As an added challenge, try to code it using only iterators in C++ or iterators in Java.
//
// Seen this question in a real interview before?
// Difficulty:Medium


/*public class Vector2D implements Iterator<Integer> {

    Stack<Integer> stack; // 很蠢的维护了一个 stack

    public Vector2D(List<List<Integer>> vec2d) {
        stack = new Stack<>();
        for (int i = vec2d.size() - 1; i >= 0; i--) {
            List<Integer> list = vec2d.get(i);
            for (int j = list.size() - 1; j >= 0; j--) {
                stack.push(list.get(j));
            }
        }
    }

    @Override
    public Integer next() {
        return stack.pop();
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}*/

public class Vector2D implements Iterator<Integer> {

    // 如果不用 stack， 那么就要一个个拿到 vec2d 中的 list， 然后判断这个 list 是否有下个数， 一个个拿出来， 空了之后换到下一个 list 去
    // 既然题目 followup 要求用 iterator 做， 那么通过上面的分析， 也确实是 iterator 的操作

    private Iterator<List<Integer>> outer; // 用来拿到 vec2d 中的每一个 list
    private Iterator<Integer> inner; // 用来拿到当前 list 中的每一个数

    public Vector2D(List<List<Integer>> vec2d) {

        outer = vec2d.iterator();
    }

    @Override
    public Integer next() {
        // int num = inner.next();
        // System.out.println(num);
        return inner.next();
    }

    @Override
    public boolean hasNext() {
        // 首先要考虑的是当前 inner 里面的数是否被拿完了， 完了就要换到 outer 中的下一个
        // 注意 vec2d 中的 list 有可能为空
        while ((inner == null || !inner.hasNext()) && outer.hasNext()) {
            inner = outer.next().iterator();
        }
        return inner != null && inner.hasNext(); //
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
