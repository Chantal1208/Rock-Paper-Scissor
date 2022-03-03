package rps.bll.player;

//Project imports
import rps.bll.game.IGameState;
import rps.bll.game.Move;
import rps.bll.game.Result;

//Java imports
import java.util.*;

/**
 * Example implementation of a player.
 *
 * @author smsj
 */
public class Player implements IPlayer {

    private String name;
    private PlayerType type;


    /**
     * @param name
     */
    public Player(String name, PlayerType type) {
        this.name = name;
        this.type = type;
    }

    public int[] leastFrequent(IGameState state) {

        int[] results1 = {1, 1, 2, 3, 4, 2, 2, 3, 3, 4, 4, 4};
        Map<Integer, Integer> counts = new HashMap<>();

        for (int i : results1) {
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
        return results1;
    }


    @Override
    public String getPlayerName() {
        return name;
    }


    @Override
    public PlayerType getPlayerType() {
        return type;
    }


    /**
     * Decides the next move for the bot...
     * @param state Contains the current game state including historic moves/results
     * @return Next move
     */
    @Override
    public Move doMove(IGameState state) {
        ArrayList<Result> results = (ArrayList<Result>) state.getHistoricResults();
       if (results.size() <= 3)
        {
            Random rand = new Random();
            int rand_int1 = rand.nextInt(3);
            if (rand_int1 == 0)
                return Move.Rock;
            else if (rand_int1 == 1)
                return Move.Paper;
            else
                return Move.Scissor;
        }
        else
        if (results.size() == 0)
            return Move.Rock;
        else if (results.size() == 1)
            return Move.Paper;
        else
            return Move.Scissor;
    }
}
