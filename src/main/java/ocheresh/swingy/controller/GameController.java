package ocheresh.swingy.controller;

import ocheresh.swingy.model.Game;
import ocheresh.swingy.view.game.MainGame;

public class GameController {

    private MainGame game_view;
    private Game game;

    public GameController (MainGame main_game)
    {
        this.game_view = main_game;
        game = Game.getInit();
    }

    public Game getGame()
    {
        return (game);
    }

    public void switchpressed() {
        game_view.SwitchPressed();
    }

    public void move_operations(String str) {
        game_view.moves_command(str);
    }

    public void fightpressed() {
        game_view.fightpressed();
    }

    public void runpressed() {
        game_view.runPressed();
    }

    public void add_artifac_pres()
    {
        game_view.add_artifac_pres();
    }

    public void end_game() { game_view.end_game(); }

    public void cheat_pressed() {game_view.cheat_pressed();}
}
