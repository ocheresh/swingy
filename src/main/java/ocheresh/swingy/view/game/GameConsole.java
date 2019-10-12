package ocheresh.swingy.view.game;

import ocheresh.swingy.controller.GameController;
import ocheresh.swingy.data.Data;
import ocheresh.swingy.data.ScanInfo;
import ocheresh.swingy.view.ConsloeView;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class GameConsole extends ConsloeView implements MainGame{

    private GameController gamecontroller;
    private boolean STOP = false;

    public GameConsole() {
        gamecontroller = new GameController(this);

        new_window();
        while (gamecontroller.getGame().getEnd_game() == false && STOP == false)
            choise();

    }

    public void new_window()
    {
        clearScreen();
        System.out.println("Welcome to GameConsole!!!");
        System.out.println(gamecontroller.getGame().getInfo_of_hero());
        gamecontroller.getGame().print_map();
        System.out.println(gamecontroller.getGame().getInfo_of_game());
    }

    public void inform_player()
    {
        System.out.println("Please enter something:");
        ScanInfo.getSc().nextLine();
    }

    public void choise()
    {
        new_window();
        System.out.println("Please enter |NORTH|,|SOUTH|,|EAST| or |WEST|: (Also you can enter |CHEAT| or |SWITCH|)");
        String str = ScanInfo.getSc().nextLine();
        if ((str.equalsIgnoreCase("NORTH")) || (str.equalsIgnoreCase("SOUTH"))
                || (str.equalsIgnoreCase("EAST")) || (str.equalsIgnoreCase("WEST")))
            gamecontroller.move_operations(str);
        else if (str.equalsIgnoreCase("CHEAT"))
            gamecontroller.cheat_pressed();
        else if (str.equalsIgnoreCase("SWITCH"))
            gamecontroller.switchpressed();
        else
            {
                System.out.println("Somethig enter wrong in choise. Please repeat your choise.");
                ScanInfo.getNextLine();
                choise();
            }
    }

    public void choise_second()
    {
        System.out.println("Please enter |FIGHT| or |RUN|: (Also you can enter |SWITCH|)");
        String str = ScanInfo.getSc().nextLine();
        if (str.equalsIgnoreCase("FIGHT"))
        {
            gamecontroller.fightpressed();
        }
        else if (str.equalsIgnoreCase("RUN"))
            gamecontroller.runpressed();
        else if (str.equalsIgnoreCase("SWITCH"))
            gamecontroller.switchpressed();
        else{
            System.out.println("Somethig enter wrong in choise. Please repeat your choise.");
            new_window();
            choise_second();
        }
    }

    @Override
    public void SwitchPressed() {
        STOP = true;
        new GameFrame();
    }

    @Override
    public void moves_command(String str) {
        gamecontroller.getGame().moves_command(str);
        if(gamecontroller.getGame().getChecks_buton() == false)
        {
            choise_second();
            gamecontroller.getGame().setChecks_buton(true);
        }
        if (gamecontroller.getGame().getEnd_game() == true) {
            clearScreen();
            System.out.println("You win. Stats of hero save.");
            Data.deleteSHero(gamecontroller.getGame().getSuperHero().getName());
            Data.addSHero(gamecontroller.getGame().getSuperHero());
            inform_player();
            gamecontroller.end_game();
        }
    }

    @Override
    public void fightpressed() {
        sound_on("./src/main/java/sounds/rage_of_blades-Blaga_Saun-1763516257.wav");
        if (gamecontroller.getGame().fight() == false)
        {
            new_window();
            System.out.println(gamecontroller.getGame().getInfo_of_move());
            System.out.println("You lose the fight");
            if (gamecontroller.getGame().getEnd_game() == true)
                System.out.println("End the game...");
            inform_player();
            gamecontroller.end_game();
        }
        else {
            new_window();
            System.out.println(gamecontroller.getGame().getInfo_of_move());
            System.out.println("You win the fight");
            sleep_on(1);
            gamecontroller.add_artifac_pres();
            if (gamecontroller.getGame().getEnd_game() == true) {
                System.out.println("End the game...");
                inform_player();
                gamecontroller.end_game();
            }
            else
                inform_player();
        }

    }

    @Override
    public void runPressed() {
        sound_on("./src/main/java/sounds/Run Forrest run.wav");
        gamecontroller.getGame().run();
        new_window();
        System.out.println(gamecontroller.getGame().getInfo_of_move());
        if (gamecontroller.getGame().getEnd_game() == true) {
            System.out.println("End the game...");
            inform_player();
            gamecontroller.end_game();
        }
        else
            inform_player();
    }

    @Override
    public void add_artifac_pres() {
//        clearScreen();
        int luck = (int)(Math.random() * 10 % 2);
        if (luck == 0)
        {
            while (luck == 0) {
                System.out.println("Would you like to take artifact? (Enter |Yes| or |No|)");
//                if (sc == null)
//                    sc = new Scanner(System.in);
                String str = ScanInfo.getNextLine();
                if (str.equalsIgnoreCase("Yes")) {
                    gamecontroller.getGame().add_artifacts();
                    luck = 1;
                } else if (str.equalsIgnoreCase("No")) {
                    System.out.println("no artifact");
                    luck = 1;
                }
                else {
                    clearScreen();
                    System.out.println("Somethig enter wrong when you add artifacts. Please repeat your choise.");
                }
            }

        }
    }

    @Override
    public void end_game() {
        Data.closeDB();
        ScanInfo.close();
        System.exit(0);
    }

    @Override
    public void cheat_pressed() {
        clearScreen();
        System.out.println("Cheat on. Good boy or girl.");
        sound_on("./src/main/java/sounds/Male Laugh Short-SoundBible.com-1728242766.wav");
        gamecontroller.getGame().print_map_cheat();
        System.out.println(gamecontroller.getGame().getInfo_of_game());
        inform_player();
        choise();

    }

    public void sound_on(String soundName)
    {
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
