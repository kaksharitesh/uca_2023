package com.uca.algos;


public class Base64 {
    private static final char[] encoder = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static final int[] decoder = {
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 64, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60,
            61, -1, -1, -1, 64, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1,
            -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42,
            43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1};

    public static void main(String[] args) {
        String input = "ab@12$#A";
        String expected = "YWJAMTIkI0E=";
        assert expected.equals(new Base64().encode(input));
        assert input.equals(new Base64().decode(expected));
    }

    public String decode(String input) {
        StringBuilder sb = new StringBuilder();
        int n = 0;
        int itr = 0;
        for (int i = 0; i < input.length(); ) {
            if (input.charAt(i) == '=') {
                break;
            }
            int c = decoder[input.charAt(i)];
            n <<= 6;
            n = n | c;
            i++;
            itr++;
            if (itr == 4) {
                sb.append((char) (n >> 16 & 255));
                sb.append((char) (n >> 8 & 255));
                sb.append((char) (n & 255));
                itr = 0;
            }

        }
        if (itr == 3) {
            sb.append((char) (n >> 10 & 255));
            sb.append((char) (n >> 2 & 255));
        } else if (itr == 2) {
            sb.append((char) (n >> 4 & 255));
        }

        return sb.toString();
    }

    private String encode(String input) {
        StringBuilder sb = new StringBuilder();
        int extras = input.length() % 3;
        int n = 0;
        for (int i = 0; i < input.length(); ) {
            n <<= 8;
            n = n | input.charAt(i);
            i++;
            if (i % 3 == 0) {
                //first 6 bits n>>18 & 63
                // second  n >> 12 & 63 ,
                sb.append(encoder[n >> 18 & 63]);
                sb.append(encoder[n >> 12 & 63]);
                sb.append(encoder[n >> 6 & 63]);
                sb.append(encoder[n & 63]);
                n = 0;
            }
        }
        if (extras == 1) {
            sb.append(encoder[n >> 2 & 63]);
            sb.append(encoder[n << 4 & 63]);
            sb.append("=").append("=");
        } else if (extras == 2) {
            sb.append(encoder[n >> 10 & 63]);
            sb.append(encoder[n >> 4 & 63]);
            sb.append(encoder[n << 2 & 63]);
            sb.append("=");
        }

        return sb.toString();
    }
}
