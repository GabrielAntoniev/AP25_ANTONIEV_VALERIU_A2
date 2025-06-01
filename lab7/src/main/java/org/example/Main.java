package org.example;

public class Main {
    public static void main(String[] args) {
        try{
            Game game = new Game();
            game.addPlayer(new Player("Player 1", game));
            game.addPlayer(new Player("Player 2", game));
            game.addPlayer(new Player("Player 3", game));
            System.out.println(game.getBag().getBagContent().size());
            game.play();

//            while(!game.getBag().getBagContent().isEmpty()){
//                System.out.println(game.getBag().getBagContent().size());
//                Thread.sleep(1000);
//            }
            for(var p : game.getPlayers()){
                System.out.println(p.getName() + ": " + p.getScore());
            }
        }
        catch(Exception e){e.printStackTrace();}
    }
}