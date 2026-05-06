class Solution {
    public char[][] rotateTheBox(char[][] boxGrid) {
        int m = boxGrid.length;
        int n = boxGrid[0].length;

        for(int i = 0; i < m; i++) {
            int emptySpot = n - 1;

            for(int j = n-1; j >= 0; j--){
                if(boxGrid[i][j] == '*') {
                    emptySpot = j-1;
                }
                else if(boxGrid[i][j] == '#') {
                    char temp = boxGrid[i][emptySpot];
                    boxGrid[i][emptySpot] = '#';
                    boxGrid[i][j] = temp;
                    emptySpot-- ;
                }
            }
        }
        char[][] rotated = new char[n][m];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                rotated[j][m-1-i] = boxGrid[i][j];
            }
        }
        return rotated;
    }
}