package javaz.api;


enum Season {
    SPRING, SUMMER, FALL, WINTER, ALL
}

enum Baseball2023 {
    LG(86, 56), KT(79, 62), SSG(76, 65), NC(75, 67);

    private final int win;
    private final int lose;
    Baseball2023(int win, int lose) {
        this.win = win;
        this.lose = lose;
    }

    // 승률 반환 winsRate()
    public double winsRate() {
        return (win * 100.0) / (win + lose) / 100.0;
    }

    // 멤버필드들의 getter들
    public int getWin() {
        return win;
    }

    public int getLose() {
        return lose;
    }
}


public class enumMain {
    public static void main(String[] args) {
        Baseball2023 lg = Baseball2023.LG;
        Baseball2023 kt = Baseball2023.KT;

        System.out.println("team LG : " + lg.getWin() + "승 " + lg.getLose() + "패");
        System.out.println("team KT : " + kt.getWin() + "승 " + kt.getLose() + "패");

//        System.out.println("team LG winRate : " + lg.winsRate());
//        System.out.println("team KT winRate : " + kt.winsRate());
        System.out.println(" - 2023 프로야구팀 승률 - ");

        Baseball2023[] teams = Baseball2023.values();
        for (Baseball2023 t : teams) {
//            System.out.println(team + " : " + team.winsRate());

            System.out.printf("%s\t : %.2f", t, t.winsRate());
            System.out.println();
        }


        
        ///////////////////////////////////////////
//        Season season = Season.SPRING;
//
//        switch(season) {
//            case SPRING: System.out.println("계절 : 봄");   break;
//            case SUMMER: System.out.println("계절 : 여름");   break;
//            case FALL: System.out.println("계절 : 가을");   break;
//            case WINTER: System.out.println("계절 : 겨울");   break;
//            default: System.out.println("계절 : 사계절");   break;
//        }
//
//        System.out.println();
//        Season[] seasons = Season.values();
//        for (Season s : seasons) {
//            System.out.println(s);
//        }




    }
}