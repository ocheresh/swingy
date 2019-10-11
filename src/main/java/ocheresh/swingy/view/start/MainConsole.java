package ocheresh.swingy.view.start;

import ocheresh.swingy.controller.MainController;
import ocheresh.swingy.data.Data;
import ocheresh.swingy.view.ConsloeView;
import ocheresh.swingy.view.create.CreateConsole;
import ocheresh.swingy.view.game.MainGame;
import ocheresh.swingy.view.select.SelectConsole;

import javax.swing.*;
import java.util.Scanner;

public class MainConsole extends ConsloeView implements MainView {

    private Scanner sc;
    private MainController mainController;

    public MainConsole()
    {
        mainController = new MainController(this);
        clearScreen();
        System.out.println("Welcome to SWINGY game \n" +
                "If you want to start you must Enter:\n"+
                "1. Create.\n" +
                "2. Select.\n" +
                "3. Switch.");
        read();
    }

    public void read()
    {
        sc = new Scanner(System.in);
        String str = sc.nextLine();

        if (str.equalsIgnoreCase("Select"))
        {
            mainController.selectHeroPressed();
        }
        else if (str.equalsIgnoreCase("Create"))
        {
            mainController.createHeroPressed();
        }
        else if (str.equalsIgnoreCase("Switch"))
        {
            mainController.switchPressed();
        }
        else{
            System.out.println("Somethig enter wrong. Please repeat.");
            read();
            clearScreen();
        }
    }

    @Override
    public void createHero() { new CreateConsole(); }

    @Override
    public void switchView()
    {
        new MainFrame();
    }

    @Override
    public void selectHero() { new SelectConsole(); }
}
