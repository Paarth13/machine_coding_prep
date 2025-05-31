import game.BattleShipGameService;

public class Main {
    public static void main(String[] args) {
        BattleShipGameService bg =  new BattleShipGameService(8);
        bg.showBoard();
        bg.addShip(0,0,bg.getPlayer1(),4);
        bg.addShip(0,0,bg.getPlayer2(),4);
        bg.addShip(0,0,bg.getPlayer1(),4);
        bg.addShip(0,0,bg.getPlayer2(),4);
        bg.showBoard();
    }
}