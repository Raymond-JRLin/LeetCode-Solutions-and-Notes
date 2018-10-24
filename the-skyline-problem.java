// 218
// A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
//
// Buildings  Skyline Contour
// The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
//
// For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
//
// The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
//
// For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
//
// Notes:
//
// The number of buildings in any input list is guaranteed to be in the range [0, 10000].
// The input list is already sorted in ascending order by the left x position Li.
// The output list must be sorted by the x position.
// There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]


class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
            return new ArrayList<int[]>();
        }
        // return method1(buildings);

        return method2(buildings);
    }

    private List<int[]> method2(int[][] buildings) {
        int n = buildings.length;
        List<int[]> heights = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // 把 start 和 end 都拿出来作为一个边放进去， 注意的是我们用 - 表示开始， + 表示结束， 当然可以反过来， 但是要注意要和下面 sort 相对应
            heights.add(new int[]{buildings[i][0], - buildings[i][2]}); // start
            heights.add(new int[]{buildings[i][1], buildings[i][2]}); // end
        }
        Collections.sort(heights, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 都是 ascending order， 这是和 +/- 表示有关的， 因为碰到左右边重合的时候
                if (o1[0] != o2[0]) {
                    return Integer.compare(o1[0], o2[0]);
                } else {
                    return Integer.compare(o1[1], o2[1]);
                }
            }
        });
        List<int[]> result = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1); // descending order
            }
        });
        pq.offer(0);
        int lastTop = 0;
        for (int[] h : heights) {
            if (h[1] < 0) {
                // it's a start point, offer its height into pq
                pq.offer(- h[1]);
            } else {
                // it's a end point, remove its height from pq
                pq.remove(h[1]);
            }
            int currHeight = pq.peek(); // highest height
            if (currHeight != lastTop) {
                result.add(new int[]{h[0], currHeight});
                lastTop = currHeight;
            }
        }
        return result;
    }

    private List<int[]> method1(int[][] buildings) {
        // use 2 arrays of Building class to collect and sort
        int n = buildings.length;
        Building[] lefts = new Building[n];
        Building[] rights = new Building[n];
        for (int i = 0; i < n; i++) {
            Building b = new Building(buildings[i][0], buildings[i][1], buildings[i][2]);
            lefts[i] = b;
            rights[i] = b;
        }
        // sort left and right Buildings respectively
        Arrays.sort(lefts, new Comparator<Building>() {
            @Override
            public int compare(Building o1, Building o2) {
                if (o1.left != o2.left) {
                    // 按照从左到右的顺序
                    return Integer.compare(o1.left, o2.left);
                } else {
                    // 如果左边界一样， 高的放前面， 因为左边界是用来加入 building 的， 所以要一下就走到最高的点作为答案， 如果矮的在前面的话， 就会出现， 同个 x 有自下往上的好几个点， 就不对了
                    return Integer.compare(o2.height, o1.height); // taller is int the front
                }
            }
        });
        Arrays.sort(rights, new Comparator<Building>() {
            @Override
            public int compare(Building o1, Building o2) {
                if (o1.right != o2.right) {
                    // 按照从左到右的顺序
                    return Integer.compare(o1.right, o2.right); // in case of Overflow
                } else {
                    // 如果右边界一样， 矮的放前面， 因为 right 是用来 remove building 的， 如果高的放前面就会被先 remove， 就会多一个点
                    return Integer.compare(o1.height, o2.height); // shorter is in the front
                }
            }
        });

        List<int[]> result = new ArrayList<>();
        TreeSet<Building> set = new TreeSet<>(new Comparator<Building>() {
            @Override
            public int compare(Building o1, Building o2) {
                if (o1.height != o2.height) {
                    // ascending order since we wanna use last() method to get the highest height
                    return Integer.compare(o1.height, o2.height);
                }
                // 高度一样， 其实谁放前面倒也无所谓了
                if (o1.left != o2.left) {
                    return Integer.compare(o1.left, o2.left);
                }
                return Integer.compare(o1.right, o2.right);
            }
        });

        int left = 1;
        int right = 0; // 2 pointers to go through 2 arrays
        int currHeight = lefts[0].height; // current height
        int lastTop = currHeight; // highest height
        set.add(lefts[0]); // push the 1st building
        result.add(new int[]{lefts[0].left, lastTop});
        int index = 0; // to record the result's x value

        // go through arrays
        while (right < n) {
            // 当左边界和右边界重合的时候， 要先处理左边， 不然的话处理右边就会把前一个 building 从 set 中移出去而导致 (x, 0) 的点
            if (left == n || lefts[left].left > rights[right].right) {
                // do right border
                index = rights[right].right;
                set.remove(rights[right]); // remove this building
                right++;
            } else {
                // do left border
                index = lefts[left].left;
                set.add(lefts[left]); // add this building
                left++;
            }
            if (set.isEmpty()) {
                currHeight = 0;
            } else {
                currHeight = set.last().height; // get the highest height of all Buildings in set
            }
            if (currHeight != lastTop) {
                // height get changed
                result.add(new int[]{index, currHeight}); // add this point
                lastTop = currHeight; // change the height last seen
            }
        }
        return result;
    }
    private class Building {
        int left;
        int right;
        int height;
        public Building(int l, int r, int h) {
            left = l;
            right = r;
            height = h;
        }
    }


}
