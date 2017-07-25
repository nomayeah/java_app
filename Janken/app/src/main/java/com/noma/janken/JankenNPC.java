package com.noma.janken;

import java.util.Random;

/**
 * Created by y_nonaka on 2017/07/24.
 */

public class JankenNPC {

    private String npcName;
    private Random jankenRandom = new Random();

    public JankenNPC(String npcName) {
        this.npcName = npcName;
    }

    public String getNpcName() {
        return this.npcName;
    }

    public JankenEnum JankenRandom() {
        int n = jankenRandom.nextInt(3);
        switch (n) {
            case 0: return JankenEnum.goooo;
            case 1: return JankenEnum.choki;
            case 2: return JankenEnum.paaaa;
        }
        throw new IllegalStateException();
    }
}
