import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Prob3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command = reader.readLine();
        List<SoccerTeam> soccerTeams = new ArrayList<>();
        while (!"stop".equalsIgnoreCase(command)){
            String[] matchInfo = command.split(" \\| ");
            String soccerTeamName1 = matchInfo[0];
            String soccerTeamName2 = matchInfo[1];
            String winner = "";
            String looser = "";
            String[] scoresAtTeam1Soil = matchInfo[2].split(":");
            String[] scoresAtTeam2Soil = matchInfo[3].split(":");

            int team1TotalScore = Integer.parseInt(scoresAtTeam1Soil[0]) + Integer.parseInt(scoresAtTeam2Soil[1]);
            int team2TotalScore = Integer.parseInt(scoresAtTeam1Soil[1]) + Integer.parseInt(scoresAtTeam2Soil[0]);

            if (team1TotalScore>team2TotalScore){
                winner = soccerTeamName1;
                looser = soccerTeamName2;
            } else if (team1TotalScore<team2TotalScore){
                winner = soccerTeamName2;
                looser = soccerTeamName1;
            } else if (team1TotalScore==team2TotalScore){
                int team1AwayScore = Integer.parseInt(scoresAtTeam2Soil[1]);
                int team2AwayScore = Integer.parseInt(scoresAtTeam1Soil[1]);
                if (team1AwayScore>team2AwayScore){
                    winner=soccerTeamName1;
                    looser=soccerTeamName2;
                } else {
                    winner=soccerTeamName2;
                    looser=soccerTeamName1;
                }
            }

            addWinnerLooser(winner,looser,soccerTeams);
            command = reader.readLine();
        }

        Collections.sort(soccerTeams, new MyComparator());
        for (SoccerTeam soccerTeam : soccerTeams) {
            System.out.println(soccerTeam);
        }
    }
    public static class MyComparator implements Comparator<SoccerTeam>{
        @Override
        public int compare(SoccerTeam o1, SoccerTeam o2) {
            if (o1.getWins() < o2.getWins()) {
                return 1;
            } else if (o1.getWins() > o2.getWins()) {
                return -1;
            }
            return o1.compareTo(o2);
        }
    }

    private static void addWinnerLooser(String winner, String looser, List<SoccerTeam> soccerTeams) {
        boolean foundWinner = false;
        boolean foundLooser = false;
        for (SoccerTeam soccerTeam : soccerTeams) {
            if (soccerTeam.getName().equalsIgnoreCase(winner)){
                foundWinner=true;
                soccerTeam.addOponent(looser);
                soccerTeam.addWin();
            } else if (soccerTeam.getName().equalsIgnoreCase(looser)){
                foundLooser=true;
                soccerTeam.addOponent(winner);
            }
        }
        if (!foundWinner){
            SoccerTeam winnerTeam = new SoccerTeam(winner,1);
            winnerTeam.addOponent(looser);
            soccerTeams.add(winnerTeam);
        }
        if (!foundLooser){
            SoccerTeam looserTeam = new SoccerTeam(looser,0);
            looserTeam.addOponent(winner);
            soccerTeams.add(looserTeam);
        }
    }

    private static class SoccerTeam implements Comparable<SoccerTeam> {
        private String name;
        private int wins;
        private List<String> oponents;

        public SoccerTeam(String name, int wins) {
            this.name = name;
            this.wins = wins;
            this.oponents = new LinkedList<>();
        }

        public String getName() {
            return this.name;
        }

        public int getWins() {
            return this.wins;
        }

        public void addWin() {
            this.wins++;
        }

        public List<String> getOponents() {
            return this.oponents;
        }

        public void addOponent(String oponent){
            this.oponents.add(oponent);
        }

        @Override
        public String toString() {
            oponents.sort(Comparator.naturalOrder());
            StringBuilder builder = new StringBuilder();
            builder.append(getName()).append(System.lineSeparator());
            builder.append("- Wins: ").append(getWins()).append(System.lineSeparator());
            builder.append("- Opponents: ").append(String.join(", ",oponents));
            return builder.toString();
        }

        @Override
        public int compareTo(SoccerTeam o) {
            return this.getName().compareTo(o.getName());
        }
    }
}
