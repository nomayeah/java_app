package com.noma.janken;

public class MainActivity {
    public static void main( String[] args ) {
        System.out.println(JankenEnum.goooo);
        System.out.println(JankenEnum.choki);
        System.out.println(JankenEnum.paaaa);

        JankenNPC NPC_1 = new JankenNPC("冴えるBLACK");
        JankenNPC NPC_2 = new JankenNPC("REAL_GOLD");

        JankenEnum deme_1 = NPC_1.JankenRandom();
        JankenEnum deme_2 = NPC_2.JankenRandom();

        System.out.println(
            String.format(
                "%s vs %s!!%n%s o=('ω')=o %s",
                NPC_1.getNpcName(), NPC_2.getNpcName(), deme_1, deme_2
            )
        );
    }
}
