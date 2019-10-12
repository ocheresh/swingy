package ocheresh.swingy;

import ocheresh.swingy.data.Data;
import ocheresh.swingy.data.ScanInfo;
import ocheresh.swingy.view.start.MainConsole;
import ocheresh.swingy.view.start.MainFrame;


public class Runner {

    public static void main(String[] args)
    {

        if ((args.length != 1) || (!(args[0].equals("gui")) && !(args[0].equals("console"))))
        {
            System.out.println("You must enter gui or console");
            System.exit(0);
        }

        Data.connect();
        ScanInfo.connect_scan();
        if (args[0].equals("gui")) {
            new MainFrame();
        }
        else if (args[0].equals("console")) {
            new MainConsole();
        }
    }
}
