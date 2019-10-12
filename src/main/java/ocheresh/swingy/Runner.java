package ocheresh.swingy;

import ocheresh.swingy.data.Data;
import ocheresh.swingy.data.ScanInfo;
import ocheresh.swingy.model.characthers.CharacthersFactory;
import ocheresh.swingy.model.characthers.EnumSuperHero;
import ocheresh.swingy.model.characthers.SuperHero;
import ocheresh.swingy.view.game.GameFrame;
import ocheresh.swingy.view.select.SelectFrame;
import ocheresh.swingy.view.start.MainConsole;
import ocheresh.swingy.view.start.MainFrame;

import javax.swing.*;
import java.util.Scanner;


public class Runner {



    public static JFrame jFrame;
//    public static Statement statmt;
//    public static ResultSet resSet;

    public static void main(String[] args)
    {
        Data.connect();
        ScanInfo.connect_scan();

        if (args[0].contentEquals("gui")) {
            new MainFrame();
        }
        else if (args[0].contentEquals("console")) {
            new MainConsole();
        }
        else {
            System.out.println("You must enter gui or console");
            return;
        }

//        Data.closeDB();

    }
}
