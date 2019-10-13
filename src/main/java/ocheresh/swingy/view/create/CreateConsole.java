package ocheresh.swingy.view.create;

import ocheresh.swingy.controller.CreateController;
import ocheresh.swingy.data.ScanInfo;
import ocheresh.swingy.model.Game;
import ocheresh.swingy.model.characthers.CharacthersFactory;
import ocheresh.swingy.model.characthers.EnumSuperHero;
import ocheresh.swingy.view.ConsloeView;
import ocheresh.swingy.view.game.GameConsole;
import ocheresh.swingy.view.start.MainConsole;


public class CreateConsole extends ConsloeView implements MainCreate {


    private CreateController createController;
    private Game game;
    private String name;
    private EnumSuperHero temphero;

    public CreateConsole()
    {
        createController = new CreateController(this);
        clearScreen();
        System.out.println("Welcome to Create Console game");
        enter_name();
        create_hero();
        choise();
    }

    public void enter_name()
    {
        clearScreen();
        System.out.println("Please enter name of new hero:(max size 250 characters)");
        String str = ScanInfo.getSc().nextLine();

        if (str.length() > 0 && str.length() < 250)
        {
            name = str;
        }
        else{
            System.out.println("Somethig enter wrong. Please repeat.");
            enter_name();
        }
    }

    public void create_hero()
    {
        clearScreen();
        System.out.println("You can choose:");
        System.out.print((
                          "Class: Knight     Class: Elf \n"
                        + "Level: 1          Level: 1\n"
                        + "Experience: 0     Experience: 0\n"
                        + "Attack: 10        Attack: 15\n"
                        + "Defence: 100      Defence: 80\n"));

        System.out.println("Please enter class of new hero:");
        String str = ScanInfo.getSc().nextLine();
        if (str.equalsIgnoreCase("KNIGHT"))
            temphero = EnumSuperHero.KNIGHT;
        else if (str.equalsIgnoreCase("ELF"))
            temphero = EnumSuperHero.ELF;
        else{
            System.out.println("Somethig enter wrong. Please repeat.");
            create_hero();
        }
    }

    public void choise()
    {
        clearScreen();
        System.out.println("Please enter |Create|, |Switch| or |Back|:");
        String str = ScanInfo.getSc().nextLine();
        if (str.equalsIgnoreCase("Create"))
        {
            createController.createHeroPressed();
        }
        else if (str.equalsIgnoreCase("Back"))
        {
            createController.backpressed();
        }
        else if (str.equalsIgnoreCase("Switch"))
        {
            createController.switch_pressed();
        }
        else{
            System.out.println("Somethig enter wrong. Please repeat.");
            clearScreen();
            choise();
        }
    }

    @Override
    public void create_newHero()
    {
        game = new Game();
        Game.setSuperHero(CharacthersFactory.getSuperHero(temphero));
        game.getSuperHero().setName(name);
        new GameConsole();
    }

    @Override
    public void back()
    {
        new MainConsole();
    }

    @Override
    public void switch_press() {
        new CreateFrame();
    }
}
