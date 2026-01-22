class Solution {
    public int findContentChildren(int[] student, int[] cookie) {
        Arrays.sort(student);
        Arrays.sort(cookie);

        int stuIndex = 0;
        int cookieIndex = 0;

        while(stuIndex < student.length && cookieIndex < cookie.length) {
            if(cookie[cookieIndex] >= student[stuIndex]) {
                stuIndex++ ;
            }
            cookieIndex++;
        }
        return stuIndex;
    }
}