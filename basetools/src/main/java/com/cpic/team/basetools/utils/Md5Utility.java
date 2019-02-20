package com.cpic.team.basetools.utils;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

/**
 * Created by Administrator on 2017/5/3 0003.
 */

public class Md5Utility {
    public Md5Utility() {
    }

    public static String hexdigest(String var0) {
        String var1 = null;
        char[] var2 = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            MessageDigest var3 = MessageDigest.getInstance("MD5");
            var3.update(var0.getBytes());
            byte[] var4 = var3.digest();
            char[] var5 = new char[32];
            int var6 = 0;

            for(int var7 = 0; var7 < 16; ++var7) {
                byte var8 = var4[var7];
                var5[var6++] = var2[var8 >>> 4 & 15];
                var5[var6++] = var2[var8 & 15];
            }

            var1 = new String(var5);
        } catch (Exception var9) {
            ;
        }

        return var1;
    }

    public static String getFileMD5(String var0) {
        try {
            FileInputStream var1 = new FileInputStream(var0);
            MessageDigest var2 = MessageDigest.getInstance("MD5");
            byte[] var3 = new byte[1024];

            int var4;
            while((var4 = var1.read(var3)) != -1) {
                var2.update(var3, 0, var4);
            }

            var1.close();
            byte[] var5 = var2.digest();
            StringBuffer var6 = new StringBuffer();

            for(int var7 = 0; var7 < var5.length; ++var7) {
                int var8 = var5[var7] & 255;
                if(var8 < 16) {
                    var6.append("0");
                }

                var6.append(Integer.toHexString(var8));
            }

            return var6.toString();
        } catch (Exception var9) {
            var9.printStackTrace();
            return null;
        }
    }

    public static String getFileMD5(File var0) {
        try {
            FileInputStream var1 = new FileInputStream(var0);
            MessageDigest var2 = MessageDigest.getInstance("MD5");
            byte[] var3 = new byte[1024];

            int var4;
            while((var4 = var1.read(var3)) != -1) {
                var2.update(var3, 0, var4);
            }

            var1.close();
            byte[] var5 = var2.digest();
            StringBuffer var6 = new StringBuffer();

            for(int var7 = 0; var7 < var5.length; ++var7) {
                int var8 = var5[var7] & 255;
                if(var8 < 16) {
                    var6.append("0");
                }

                var6.append(Integer.toHexString(var8));
            }

            return var6.toString();
        } catch (Exception var9) {
            var9.printStackTrace();
            return null;
        }
    }

    public static String getStringMD5(String var0) {
        char[] var1 = var0.toCharArray();
        return getCharArrayMD5(var1);
    }

    public static String getCharArrayMD5(char[] var0) {
        byte[] var1 = new byte[var0.length];

        for(int var2 = 0; var2 < var0.length; ++var2) {
            var1[var2] = (byte)var0[var2];
        }

        return getByteArrayMD5(var1);
    }

    public static String getByteArrayMD5(byte[] var0) {
        try {
            MessageDigest var1 = MessageDigest.getInstance("MD5");
            var1.update(var0);
            byte[] var2 = var1.digest();
            StringBuffer var3 = new StringBuffer();

            for(int var4 = 0; var4 < var2.length; ++var4) {
                int var5 = var2[var4] & 255;
                if(var5 < 16) {
                    var3.append("0");
                }

                var3.append(Integer.toHexString(var5));
            }

            return var3.toString();
        } catch (Exception var6) {
            var6.printStackTrace();
            return null;
        }
    }
}
