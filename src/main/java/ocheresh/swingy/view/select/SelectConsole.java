package ocheresh.swingy.view.select;

import ocheresh.swingy.controller.SelectController;
import ocheresh.swingy.data.Data;
import ocheresh.swingy.model.Game;
import ocheresh.swingy.view.ConsloeView;
import ocheresh.swingy.view.game.GameConsole;
import ocheresh.swingy.view.game.GameFrame;
import ocheresh.swingy.view.start.MainConsole;

import java.util.Scanner;

public class SelectConsole extends ConsloeView implements MainSelect  {

    private String name;
    private Scanner sc;
    private SelectController selectController;
    private Game game;

    public SelectConsole()
    {
        clearScreen();
        selectController = new SelectController(this);
        enter_name();
        choise();

    }

    public void enter_name()
    {
        clearScreen();

        Data.print();
        if (sc == null)
            sc = new Scanner(System.in);
        System.out.println("Please enter name of new hero:");
        String str = sc.nextLine();

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

    public void choise()
    {
        clearScreen();
        System.out.println("Please enter |Select|,|Back| or |Switch|:");
        if (sc == null)
            sc = new Scanner(System.in);
        String str = sc.nextLine();
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
