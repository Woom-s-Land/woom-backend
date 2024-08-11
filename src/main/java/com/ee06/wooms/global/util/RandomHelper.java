package com.ee06.wooms.global.util;

import java.util.Random;

public class RandomHelper {
    public static final String USER_AUTH_MAIL_TITLE = "[WOOMS] 회원 가입 인증 이메일 입니다.";
    public static final String USER_RE_ISSUE_PASSWORD_TITLE = "[WOOMS] 비밀번호 재발급 이메일 입니다.";
    public static final String SOCIAL_SENTENCES = "이미 소셜 회원으로 가입 되어 있는 상태입니다.<br>계정을 통합하려면 계속 회원가입을 진행해주세요";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()_+[]{}|;:,.<>?";
    private static final String ALL_CHARS = LOWERCASE + UPPERCASE + DIGITS + SPECIAL_CHARS;

    private static Random rand = new Random();

    public static int generateCostumeNumber() {
        return rand.nextInt(12);
    }


    public static String generateNickname() {
        String[] actions = {
                "심심한", "웃는", "뛰는", "먹는", "숨는",
                "반짝이는", "꿈꾸는", "걷는", "생각하는", "눈물나는",
                "부끄러운", "우울한", "춤추는", "바쁜", "배고픈",
                "이불속의", "잠든", "노래하는", "힘든", "지친"
        };

        String[] nouns = {
                "호랑이", "사자", "범죄자", "강아지", "고양이",
                "토끼", "팬더", "여우", "코끼리", "다람쥐",
                "기린", "앵무새", "고릴라", "해달", "문어",
                "돌고래", "거북이", "치타", "늑대", "독수리"
        };

        int randomActionIndex = rand.nextInt(actions.length);
        int randomNounIndex = rand.nextInt(nouns.length);

        return actions[randomActionIndex] + nouns[randomNounIndex];
    }

    public static String generateRandomMailAuthenticationCode() {
        return rand.ints(48, 122 + 1)
                .filter(i -> (i <=57 || i >=65) && (i <= 90 || i>= 97))
                .limit(6)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String getEmailAuthContent(String socialUserContent, String code) {
        String content =
                "Wooms를 방문해주셔서 감사합니다.<br>" + socialUserContent +
                        "<br><br>" +
                        "인증 번호는 " + code + "입니다." +
                        "<br>" +
                        "회원가입 창에 해당 인증번호를 입력해주세요.";
        return content;
    }

    public static String getEmailReIssueContent(String password) {
        String content =
                "Wooms를 방문해주셔서 감사합니다." +
                        "<br><br>" +
                        "임시 비밀번호는 " + password + "입니다." +
                        "<br>" +
                        "로그인 후 비밀번호를 재설정 해주세요";
        return content;
    }

    public static String generateRandomPassword() {
        return rand.ints(0, ALL_CHARS.length())
                .distinct()
                .limit(8)
                .mapToObj(ALL_CHARS::charAt)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
