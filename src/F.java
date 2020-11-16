import java.io.*;
import java.util.*;

public final class F {

    private static final NonBlankFastReader in = new NonBlankFastReader(
            new BufferedReader(new InputStreamReader(System.in)));
    private static final PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    public static void main(String[] args) {
        int n = in.nextInt();
        int[] h = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = in.nextInt();
        }
        out.println(new F().solve(h));
        out.flush(); // Mandatory
    }

    private int solve(int[] h) {
        Pair<Integer, Integer> min = min(h, 0, h.length - 1);
        Pair<Integer, Integer> max = max(h, 0, min.a);
        int total = 1;
        outer: while (true) {
            inner: for (int i = min.a + 1; i < h.length; i++) {
                if (max.b > h[i]) { // increase block
                } else {

                    Pair<Integer, Integer> minRange = min(h, i, h.length - 1);
                    if (minRange.b >= min.b && minRange.b < max.b) {
                        min = min(h, i, h.length - 1);
                        max = max(h, i, min.a);
                        continue inner;
                    }

                    total++;
                    if (i + 1 < h.length) {
                        min = min(h, i, h.length - 1);
                        max = max(h, i, min.a);
                    } else {
                        break outer;
                    }
                    continue outer;
                }
            }
            break;
        }
        return total;
    }

    private Pair<Integer, Integer> min(int[] h, int start, int length) {
        int index = start;
        int min = h[index];
        for (int j = index + 1; j <= length; j++) {
            if (h[j] < min) {
                min = h[j];
                index = j;
            }
        }
        return new Pair<>(index, min);
    }

    private Pair<Integer, Integer> max(int[] h, int start, int length) {
        int maxIndex = start;
        int max = h[maxIndex];
        for (int j = maxIndex + 1; j <= length; j++) {
            if (h[j] >= max) {
                max = h[j];
                maxIndex = j;
            }
        }
        return new Pair<>(maxIndex, max);
    }

    private static class Pair<A, B> {
        private A a;
        private B b;

        public Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "Pair{" + "a=" + a + ", b=" + b + '}';
        }
    }

    private static final class NonBlankFastReader implements Closeable {
        private final BufferedReader br;
        private StringTokenizer st;

        private NonBlankFastReader(final BufferedReader bufferedReader) {
            br = bufferedReader;
            st = new StringTokenizer("");
        }

        public String nextLine() {
            if (st.hasMoreTokens()) {
                final StringBuilder sb = new StringBuilder();
                do {
                    sb.append(st.nextToken());
                } while (st.hasMoreTokens());
                return sb.toString();
            } else
                return readLine();
        }

        public String next() {
            while (!st.hasMoreTokens()) {
                st = new StringTokenizer(readLine());
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        private String readLine() {
            try {
                return br.readLine();
            } catch (final IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        @Override
        public void close() {
            st = null;
            try {
                br.close();
            } catch (final IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}