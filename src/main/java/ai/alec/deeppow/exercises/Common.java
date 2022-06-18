package ai.alec.deeppow.exercises;

public class Common {
    public String longestCommonPrefix(String[] strs) {
        String longestString = "";
        if (strs.length > 0) {
            longestString = strs[0];
        }

        for (int i = 1; i < strs.length; i++) {
            if (i == 1) {
                longestString = longestCommon(strs[i], strs[i-1]);
            } else {
                longestString = longestCommon(longestString, strs[i]);
            }
            if (longestString.isEmpty()) {
                return longestString;
            }
        }

        return longestString;
    }

    private String longestCommon(String first, String second) {
        StringBuilder longest = new StringBuilder();
        int testLength = Math.min(first.length(), second.length());
        for (int i = 0, n = testLength; i < n; i++) {
            char firstChar = first.charAt(i);
            char secondChar = second.charAt(i);

            if (firstChar == secondChar) {
                longest.append(firstChar);
            } else {
                return longest.toString();
            }
        }
        return longest.toString();
    }
}
