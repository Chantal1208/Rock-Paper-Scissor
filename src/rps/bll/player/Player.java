package rps.bll.player;

//Project imports
import rps.bll.game.IGameState;
import rps.bll.game.Move;
import rps.bll.game.Result;

//Java imports
import java.util.*;

public class Player implements IPlayer {

    private String name;
    private PlayerType type;
    private int minVal;

    IGameState state = new IGameState() {
        @Override
        public int getRoundNumber() {
            return 0;
        }

        @Override
        public void setRoundNumber(int roundNumber) {

        }

        @Override
        public Collection<Result> getHistoricResults() {
            return null;
        }


    };



    public Player(String name, PlayerType type) {
        this.name = name;
        this.type = type;
    }


    @Override
    public String getPlayerName() {
        return name;
    }


    @Override
    public PlayerType getPlayerType() {
        return type;
    }

    @Override
    public Move doMove(IGameState state) {
        if (convertMoveList.size() <= 3) {
            Random rand = new Random();
            int rand_int1 = rand.nextInt(3);
            if (rand_int1 == 0)
                return Move.Rock;
            else if (rand_int1 == 1)
                return Move.Paper;
            else
                return Move.Scissor;
        } else if (minVal == 1)
            return Move.Rock;
        else if (minVal == 2)
            return Move.Paper;
        else
            return Move.Scissor;
    }

    /**
     * Decides the next move for the bot...
     *
     * @param state Contains the current game state including historic moves/results
     * @return Next move
     */
    ArrayList<Integer> convertMoveList = new ArrayList<>();

    public ArrayList<Integer> convertMove(Move humanMove) {


        if (humanMove == Move.Paper) {
            convertMoveList.add(1);
        } else if (humanMove == Move.Rock)
            convertMoveList.add(2);
        else
            convertMoveList.add(3);
        return convertMoveList;
    }

    public int leastFrequent(IGameState state) {


        Map<Integer, Integer> counts = new HashMap<>();

        for (int i : convertMoveList) {
            if (counts.get(i) == null) {
                counts.put(i, 1);
            } else {
                counts.put(i, counts.get(i) + 1);
            }
        }

        //find min value by sorting values and taking top element
        List<Integer> cs = new ArrayList<Integer>(counts.values());
        Collections.sort(cs);
        int minVal = cs.get(0);

        //find elements with minVal as their count
        List<Integer> minElements = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == minVal) {
                minElements.add(entry.getKey());
            }
        }
        //spit out each element and the count
        for (Integer i : minElements) {
            System.out.println("Element: " + i + " Number of occurences: "
                    + minVal);
        }
        return minVal;
    }

}