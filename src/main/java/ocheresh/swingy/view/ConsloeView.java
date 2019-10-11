package ocheresh.swingy.view;

public abstract class ConsloeView {
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void sleep_on(int seconds)
    {
        try {
            Thread.sleep(1000 * seconds);
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
