package com.feige;

public class ReplaceBlank {
    public static String replaceBlank(String str) {
        if(str == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0;i < str.length();i++){
            if(str.charAt(i) == ' ') {
                stringBuilder.append("%20");
            }else {
                stringBuilder.append(String.valueOf(str.charAt(i)));
            }
        }
        return new String(stringBuilder);
    }

    public static void main(String[] args) {
        String str = "we are happy";
        String res = replaceBlank(str);
        System.out.println(res);
    }
}
