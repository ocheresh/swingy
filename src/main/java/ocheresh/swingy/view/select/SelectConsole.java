package ocheresh.swingy.view.select;

import ocheresh.swingy.controller.SelectController;
import ocheresh.swingy.data.Data;
import ocheresh.swingy.data.ScanInfo;
import ocheresh.swingy.model.Game;
import ocheresh.swingy.view.ConsloeView;
import ocheresh.swingy.view.game.GameConsole;
import ocheresh.swingy.view.start.MainConsole;


public class SelectConsole extends ConsloeView implements MainSelect  {

    private String name;
    private SelectController selectController;
    private Game game;

    public SelectConsole()
    {
        clearScreen();
        selectController = new SelectController(this);
        if (Data.size() != 0) {
            enter_name();
            choise();
        }
        else
        {
            no_heroes();
        }

    }

    public void enter_name()
    {
        clearScreen();

        Data.print();
        System.out.println("Please enter name of new hero:");
        String str = ScanInfo.getSc().nextLine();

        if (str.length() > 0)
        {
            if (Data.check_name_of_shero(str) == true)
                name = str;
            else
                enter_name();
        }
        else{
            System.out.println("Somethig enter wrong. Please repeat name.");
            enter_name();
        }
    }

    public void no_heroes()
    {
        clearScreen();
        System.out.println("No heroes in database. Please enter back and createt hero.");
        System.out.println("Please enter |Back|:");
        if (ScanInfo.getSc().nextLine().equalsIgnoreCase("Back"))
        {
            selectController.press_back();
        }
        else
        {
            no_heroes();
        }

    }

    public void choise()
    {
        clearScreen();
        System.out.println("Please enter |Select|,|Back| or |Switch|:");
        String str = ScanInfo.getSc().nextLine();
        if (str.equalsIgnoreCase("Select"))
        {
            selectController.select();
        }
        else if (str.equalsIgnoreCase("Back"))
        {
            selectController.press_back();
        }
        else if (str.equalsIgnoreCase("Switch"))
        {
            selectController.switch_controller();
        }
        else{
            clearScreen();
            System.out.println("Somethig enter wrong. Please repeat your choise.");
            choise();
        }
    }



    @Override
    public void select_press() {
        game = new Game();
        Game.setSuperHero(Data.getSuperHero(name));
        new GameConsole();
    }

    @Override
    public void switch_press() {
        new SelectFrame();
    }

    @Override
    public void back_pres() {
        new MainConsole();
    }
}
