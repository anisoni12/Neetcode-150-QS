class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> close = new HashMap<>();
        close.put(')', '(');
        close.put(']', '[');
        close.put('}', '{');

        for(char c : s.toCharArray()) {
            if(close.containsKey(c)) {
                if(!stack.isEmpty() && stack.peek() == close.get(c)) {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}