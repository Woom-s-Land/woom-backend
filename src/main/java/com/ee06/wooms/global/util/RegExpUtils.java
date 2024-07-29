package com.ee06.wooms.global.util;

public class RegExpUtils {
    static public final String EMAIL_EXP = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    static public final String PASSWORD_EXP = ".*";

    static public final String NAME_EXP = "^[가-힣]{2,4}$";

    static public String NICKNAME_MESSAGE = "별명은 한글, 알파벳, 숫자로만 구성할 수 있습니다.";
    public static final String NICKNAME_EXP = "^[a-zA-Z가-힣0-9]+$";
}
