class Solution {
    public int maxPathScore(int[][] grid, int k) {
         int m = grid.length;
        int n = grid[0].length;

        int[][][] dp = new int[m][n][k + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        dp[0][0][0] = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int cellValue = grid[i][j];
                int cellCost = (cellValue == 0) ? 0 : 1;

                for (int usedCost = 0; usedCost <= k; usedCost++) {

                    if (i == 0 && j == 0) continue;

                    int prevCost = usedCost - cellCost;

                    if (prevCost < 0) continue;

                    int bestPrev = -1;

                    if (i > 0) {
                        bestPrev = Math.max(bestPrev, dp[i - 1][j][prevCost]);
                    }

                    if (j > 0) {
                        bestPrev = Math.max(bestPrev, dp[i][j - 1][prevCost]);
                    }

                    if (bestPrev != -1) {
                        dp[i][j][usedCost] = bestPrev + cellValue;
                    }
                }
            }
        }

        int ans = -1;

        for (int cost = 0; cost <= k; cost++) {
            ans = Math.max(ans, dp[m - 1][n - 1][cost]);
        }

        return ans;
    }
}