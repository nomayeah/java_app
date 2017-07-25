package com.noma.janken;

public enum JankenEnum {
    goooo,
    choki,
    paaaa;

    @Override
    public String toString() {
        switch (this) {
            case goooo: return "ぐーぅ";
            case choki: return "ちょき";
            case paaaa: return "ぱーぁ";
        }
        throw new IllegalStateException();
    }
}

