class Solution {
    public int minOperations(int[][] grid, int x) {
        List<Integer> nums = new ArrayList<>();

        for(int[] row : grid) {
            for(int num : row){
                nums.add(num);
            }
        }

        int base = nums.get(0);

        for(int num : nums) {
            if((num - base) % x != 0){
                return -1;
            }
        }
        Collections.sort(nums);
        int median = nums.get(nums.size() / 2);
        int ops = 0;
        for(int num : nums){
            ops += Math.abs(num - median) / x;
        }
        return ops;
    }
}