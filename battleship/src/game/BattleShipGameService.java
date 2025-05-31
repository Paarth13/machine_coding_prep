package game;

import javafx.util.Pair;
import model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BattleShipGameService {
    List<List<String>> board;
    Player player1;
    Player player2;
    HashMap<Player,HashMap<String, List<Pair<Integer,Integer>>>> shipsCoordMap;
    HashMap<Pair<Integer,Integer>,String> shipsLocation ;
    public BattleShipGameService(int size) {
        createBoard(size);
        shipsLocation = new HashMap<>();
        shipsCoordMap = new HashMap<>();
        this.player1 = new Player("P1",new Pair<>(0,0),new Pair<>(size/2,size/2));
        this.player2 = new Player("P1",new Pair<>(0,size/2),new Pair<>(size-1,size-1));
    }
    private void createBoard(int size)
    {
        board = new ArrayList<>();
        for(int i=0;i<size;i++)
        {
            List<String> s = new ArrayList<>();
            for(int j =0;j<size;j++)
            {
                s.add("");
            }
            board.add(s);
        }
    }
    public void addShip(int x, int y, Player player, int size)
    {
        if(!checkIfValidMove(x, y, player))
        {
            System.out.println("Invalid move");
            return;
        }
        int counter = player.getCounter();
        List<List<Integer>> l = new ArrayList<List<Integer>>(List.of(new ArrayList<>(List.of(size,0)),new ArrayList<>(List.of(0,size)),
                new ArrayList<>(List.of(size,size))));
        updateShipMap(player,player.getName()+counter,new Pair<>(x,y));
        shipsLocation.put(new Pair<>(x,y),player.getName()+counter);
        for(List<Integer> i: l)
        {
            Pair<Integer,Integer> p = new Pair<>(i.get(0)+x,i.get(1)+y);
            updateShipMap(player,player.getName()+counter,p);
            shipsLocation.put(p,player.getName()+counter);
        }

        player.updateCounter(1);
    }
    public boolean checkIfValidMove(int x, int y, Player player)
    {
        if(x>= player.getStart().getKey() && y< player.getEnd().getValue() && !shipsLocation.containsKey(new Pair<>(x,y)))
        {
            return true;
        }
        return false;
    }
    public void showBoard()
    {
        for(int i=0;i< board.size();i++)
        {
            for(int j =0;j< board.size();j++)
            {
                System.out.print(shipsLocation.getOrDefault(new Pair<>(i, j), "W \t"));
            }
            System.out.println(" ");
        }
    }
    public void updateShipMap(Player player,String name,Pair<Integer,Integer> coords) {
        if(this.shipsCoordMap.containsKey(player))
        {
            shipsCoordMap.get(player).get(name).add(coords);
        }
        else {
            HashMap<String, List<Pair<Integer,Integer>>> mp =  new HashMap<>();
            mp.put(name,new ArrayList<>(List.of(coords)));
            shipsCoordMap.put(player,mp);
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
