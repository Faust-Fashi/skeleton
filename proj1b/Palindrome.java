/*2022/11/19 10:18
 * by IDEA
 */
public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        int length = word.length();
        LinkedListDeque<Character> list = new LinkedListDeque<>();
        for (int i = 0; i < length; i++) {
            list.addLast(word.charAt(i));
        }
        return list;
    }
    private boolean isPalindromeHelper(Deque list) {
        int size = list.size();
        if (size < 2) {
            return true;
        } else if (list.removeFirst() != list.removeLast()) {
            return false;
        } else {
            return isPalindromeHelper(list);
        }
    }
    public boolean isPalindrome(String word) {
        Deque list = wordToDeque(word);
        return isPalindromeHelper(list);
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        int length = word.length();
        if (word == null || length < 2) {
            return true;
        }
        for (int i = 0; i < length / 2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(length - i - 1))){
                return false;
            }
        }
        return true;
    }
//    public boolean isPalindrome(String word) {
//        int length = word.length();
//        if (word == null || length < 2) {
//            return true;
//        }
//        for (int i = 0; i < length / 2; i++) {
//            if (word.charAt(i) != word.charAt(length - i - 1)) {
//                return false;
//            }
//        }
//        return true;
//
//    }

}
