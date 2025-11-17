class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;

        int start = nums[0];
        int end = nums[0];

        for(int i = 1; i < nums.length; i++){
            int num = nums[i];

            if((long) num == (long) end + 1L) {
                end = num;
            } else {
                res.add(formatRange(start, end));
                start = end = num;
            }
        }
        res.add(formatRange(start, end));
        return res;
    }

    private String formatRange(int a, int b) {
        return a == b ? Integer.toString(a) : a + "->" + b;
    }
}