package ocheresh.swingy.view.start;

import ocheresh.swingy.controller.MainController;
import ocheresh.swingy.data.Data;

import javax.swing.*;
import java.util.Scanner;

public class MainConsole extends JFrame implements MainView {

    private Scanner sc;
    private MainController mainController;

    public MainConsole()
    {
        mainController = new MainController(this);
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
            System.out.println("Select");
        }
        else if (str.equalsIgnoreCase("Create"))
        {
            System.out.println("Create");
        }
        else if (str.equalsIgnoreCase("Switch"))
        {
            System.out.println("Switch");
            mainController.switchPressed();
        }
        else{
            System.out.println("Somethig enter wrong. Please repeat.");
            read();
        }
    }

    @Override
    public void createHero()
    {

    }

    @Override
    public void switchView()
    {
        new MainFrame();
    }

    @Override
    public void selectHero()
    {

    }
}
