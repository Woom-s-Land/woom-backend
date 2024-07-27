package com.ee06.wooms.global.util;

import java.util.Random;

public class RandomHelper {
    private static Random rand = new Random();

    public static int generateCostumeNumber() {
        return rand.nextInt(13) + 1;
    }

    public static String generateNickname() {
        String[] actions = {
                "심심한", "웃는", "뛰는", "먹는", "숨는",
                "반짝이는", "꾸는", "걷는", "생각하는", "눈물나는",
                "부끄러운", "우울한", "춤추는", "바쁜", "배고픈",
                "이불 속의", "잠든", "노래하는", "힘든", "지친"
        };

        String[] nouns = {
                "호랑이", "사자", "범죄자", "강아지", "고양이",
                "토끼", "팬더", "여우", "코끼리", "다람쥐",
                "기린", "앵무새", "고릴라", "해달", "문어",
                "돌고래", "거북이", "치타", "늑대", "독수리"
        };

        int randomActionIndex = rand.nextInt(actions.length);
        int randomNounIndex = rand.nextInt(nouns.length);

        return actions[randomActionIndex] + "_" + nouns[randomNounIndex];
    }

    public static String generateRandomMailAuthenticationCode() {
        return rand.ints(48, 122 + 1)
                .filter(i -> (i <=57 || i >=65) && (i <= 90 || i>= 97))
                .limit(6)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
