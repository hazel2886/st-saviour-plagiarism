package main.java;

public class RabinKarp {
    // d is the number of characters in the input alphabet
    private static final int d = 256;
    // q is a prime number for hashing
    private static final int q = 101;

    public static boolean search(String pattern, String text) {
        int m = pattern.length();
        int n = text.length();
        int p = 0; // hash value for pattern
        int t = 0; // hash value for text
        int h = 1;

        if (m > n || m == 0) return false;

        // The value of h would be "pow(d, m-1) % q"
        for (int i = 0; i < m - 1; i++)
            h = (h * d) % q;

        // Calculate the initial hash value of pattern and first window of text
        for (int i = 0; i < m; i++) {
            p = (d * p + pattern.charAt(i)) % q;
            t = (d * t + text.charAt(i)) % q;
        }

        // Slide the pattern over text one by one
        for (int i = 0; i <= n - m; i++) {
            if (p == t) {
                // Check characters if hash matches
                int j;
                for (j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) break;
                }
                if (j == m) return true;
            }

            if (i < n - m) {
                t = (d * (t - text.charAt(i) * h) + text.charAt(i + m)) % q;
                if (t < 0) t = (t + q);
            }
        }
        return false;
    }
}