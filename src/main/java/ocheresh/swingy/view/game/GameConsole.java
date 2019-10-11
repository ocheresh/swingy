package ocheresh.swingy.view.game;

import ocheresh.swingy.controller.GameController;

public class GameConsole implements MainGame{

    private GameController gamecontroller;

    public GameConsole() {
        gamecontroller = new GameController(this);

        System.out.println(gamecontroller.getGame().getSuperHero().getInfo());
        System.out.println(gamecontroller.getGame().getInfo_of_game());

        try {
            java.util.concurrent.TimeUnit.SECONDS.sleep(2);
        }
        catch (Exception e)
        {
            System.out.println("Error GameConsole:" + e.getMessage());
        }

        SwitchPressed();

    }

    @Override
    public void SwitchPressed() {
        new GameFrame();
    }

    @Override
    public void moves_command(String str) {
    }

    @Override
    public void fightpressed() {

    }

    @Override
    public void runPressed() {

    }

    @Override
    public void add_artifac_pres() {

    }

    @Override
    public void end_game() {

    }
}
