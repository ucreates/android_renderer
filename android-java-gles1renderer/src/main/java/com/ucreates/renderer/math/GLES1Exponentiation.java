package com.ucreates.renderer.math;
public class GLES1Exponentiation {
    public static int getExponentiation(int number) {
        if ((number & (number - 1)) == 0) {
            return number;
        }
        int ret = 1;
        while (number > 0) {
            ret <<= 1;
            number >>= 1;
        }
        return ret;
    }
}
