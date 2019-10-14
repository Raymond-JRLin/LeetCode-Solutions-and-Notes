// 353. Design Snake Game
// DescriptionHintsSubmissionsDiscussSolution
// Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.
//
// The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
//
// You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.
//
// Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.
//
// When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
//
// Example:
//
// Given width = 3, height = 2, and food = [[1,2],[0,1]].
//
// Snake snake = new Snake(width, height, food);
//
// Initially the snake appears at position (0,0) and the food at (1,2).
//
// |S| | |
// | | |F|
//
// snake.move("R"); -> Returns 0
//
// | |S| |
// | | |F|
//
// snake.move("D"); -> Returns 0
//
// | | | |
// | |S|F|
//
// snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )
//
// | |F| |
// | |S|S|
//
// snake.move("U"); -> Returns 1
//
// | |F|S|
// | | |S|
//
// snake.move("L"); -> Returns 2 (Snake eats the second food)
//
// | |S|S|
// | | |S|
//
// snake.move("U"); -> Returns -1 (Game over because snake collides with border)



class SnakeGame {

    int n, m;
    Deque<int[]> snake;
    Set<Integer> set; // record snake's boday
    int[][] food;
    int index;
    boolean isDead;

    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        this.n = height;
        this.m = width;
        this.food = food;
        this.index = 0;
        this.snake = new LinkedList<>();
        this.set = new HashSet<>();
        this.isDead = false;
        int[] ori = new int[]{0, 0};
        snake.offer(ori);
        set.add(hash(ori));
    }

    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
        @return The game's score after the move. Return -1 if game over.
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        if (isDead) {
            return -1;
        }
        int[] head = snake.peekFirst();
        int[] next;
        if (direction.equals("U")) {
            next = new int[]{head[0] - 1, head[1]};
        } else if (direction.equals("L")) {
            next = new int[]{head[0], head[1] - 1};
        } else if (direction.equals("R")) {
            next = new int[]{head[0], head[1] + 1};
        } else {
            next = new int[]{head[0] + 1, head[1]};
        }

        // remove tail first, since new head (next) is allow to take tail's position
        int[] tail = snake.peekLast();
        set.remove(hash(tail));
        // check if out of bound
        if (next[0] < 0 || next[0] >= n || next[1] < 0 || next[1] >= m) {
            isDead = true;
            return -1;
        }
        // check if hit snake itself
        if (set.contains(hash(next))) {
            isDead = true;
            return -1;
        }
        // next would be new snake's head, adding new head
        snake.offerFirst(next);
        set.add(hash(next));
        // check if snake can eat food
        if (index < food.length && next[0] == food[index][0] && next[1] == food[index][1]) {
            index++;
            // add tail back to keep old tail, since we add 1 length on new head
            set.add(hash(tail));
            return index;
        }
        // if it doesn't eat food, move 1 step => remove tail
        snake.pollLast();
        return index;
    }

    private int hash(int[] nums) {
        return nums[0] * m + nums[1];
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */
