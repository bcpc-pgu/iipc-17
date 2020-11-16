import java.io.*;
import java.util.*;

public final class C {

    private static final NonBlankFastReader in = new NonBlankFastReader(
            new BufferedReader(new InputStreamReader(System.in)));
    private static final PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    public static void main(String[] args) {
        final int n = in.nextInt();
        StringBuilder s = new StringBuilder(in.nextLine());
        out.println(new C().solve(n, s));
        out.flush(); // Mandatory
    }

    private String solve(int n, StringBuilder s) {
        while (true) {
            boolean found = false;
            for (int i = 0; i < s.length() - 1; i++) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    s.delete(i, i + 2);
                    found = true;
                    continue;
                }
            }
            if (!found)
                break;
        }
        return s.toString();
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