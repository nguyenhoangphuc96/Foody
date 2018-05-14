package com.example.sino.foodyv1.TaiKhoan_Fragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SINO on 5/19/2017.
 */

public class Check {
    private static Pattern pattern;
    private static Matcher matcher;
    //kí tự email hợp lệ
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            +"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    /**
     * Validate Email with regular expression
     *
     * @param email
     * @return true for Valid Email and false for Invalid Email
     */
    //Kiểm tra tính hợp lệ của email truyền vào
    public static boolean validate(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();

    }
    //Kiểm tra tính hợp lệ của chiều dài email
    public static boolean validatePasslenght(String Pass){
        return  Pass.length()>=4;
    }
    //kiểm tra tình hợp lệ của pass
    public static boolean validatePass(String Pass, String Confirm){
        return Pass.toString().equals(Confirm.toString());
    }
    /**
     * Checks for Null String object
     *
     * @param txt
     * @return true for not null and false for null String object
     */
    //kiểm tra chuổi rỗng
    public static boolean isNotNull(String txt) {
        return txt != null && txt.trim().length() > 0 ? true : false;
    }
    //Kiểm tra tính hợp lệ giữa 2 chuổi
    public static boolean validateString(String s1, String s2) {
        return s1.matches(s2);
    }
}