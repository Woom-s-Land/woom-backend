package com.ee06.wooms.global.util;

public class RegExpUtils {
    static public final String EMAIL_EXP = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    static public final String PASSWORD_EXP = ".*";

    static public final String NAME_EXP = "^[가-힣]{2,4}$";

    public static final String NICKNAME_EXP = "^[a-zA-Z가-힣0-9]+$";
}
