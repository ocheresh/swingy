package ocheresh.swingy.data;

import java.util.Scanner;

public class ScanInfo {

    private static Scanner sc = null;

    public static void connect_scan()
    {
        if (sc == null)
            sc = new Scanner(System.in);
    }

    public static void close()
    {
        if (sc != null)
            sc.close();
    }

    public static Scanner getSc()
    {
        return sc;
    }

    public static String getNextLine()
    {
        if (sc != null)
            return sc.nextLine();
        return "";
    }
}
