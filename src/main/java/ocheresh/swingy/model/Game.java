package ocheresh.swingy.model;

import ocheresh.swingy.data.Data;
import ocheresh.swingy.model.arifacts.ArtifactsFactory;
import ocheresh.swingy.model.characthers.Character;
import ocheresh.swingy.model.characthers.CharacthersFactory;
import ocheresh.swingy.model.characthers.SuperHero;

public class Game {

    private static Game game = null;

    private static SuperHero hero = null;
    private Character monster;
    private static int size_square = 0;
    private static boolean[][] map = null;
    private static int position[] = null;
    private static int prev_position[] = null;
    private static String info_of_game = "";
    private static String info_of_move = "";
    private static String info_of_hero = "";
    private boolean checks_buton = true;
    private boolean End_game = false;

    public boolean getEnd_game() { return End_game; }

    public String getInfo_of_hero() {
        info_of_hero = "";
        info_of_hero += "Name:" + hero.getName();
        info_of_hero += "\nClass: " + hero.getenumClass();
        info_of_hero += "\nLevel: " + hero.getLevel();
        info_of_hero += "\nExperience: " + hero.getExperience();
        info_of_hero += "\nAttack: " + hero.getAttack();
        info_of_hero += "\nDefense: " + hero.getDefence();
        info_of_hero += "\nHit Points: " + hero.getHit_points();
        info_of_hero += "\nWeapon: " + hero.getWeapon().getName();
        info_of_hero += "\n  -increases the attack: " + hero.getWeapon().getAttack();
        info_of_hero += "\nArmor: " + hero.getArmor().getName();
        info_of_hero += "\n  -increases defense: " + hero.getArmor().getAttack();
        info_of_hero += "\nHelm: " + hero.getHelm().getName();
        info_of_hero += "\n  -increases hit points: " + hero.getHelm().getAttack();
        info_of_hero += "\n position: [" + position[0] + "][" + position[1] + "]";
        return info_of_hero;
    }

    public String getInfo_of_move() { return info_of_move; }
    public void setInfo_of_move(String info_of_move) { Game.info_of_move = info_of_move; }
    public boolean getChecks_buton() {
        return checks_buton;
    }
    public void setChecks_buton(boolean checks_buton) { this.checks_buton = checks_buton; }
    public String getInfo_of_game() {
        return info_of_game;
    }
    public Game() { }
    public static Game getInit()
    {
        if (game == null) {
            game = new Game();
        }
        return game;
    }
    public static void setSuperHero(SuperHero superHero)
    {
        if (hero == null) {
            hero = superHero;
            position = new int[2];
            prev_position = new int[2];
            int level = hero.getLevel();
            size_square = ((level - 1) * 5) + (10 - (level % 2));
            map = new boolean[size_square][size_square];

            info_of_game += "Size square" + Integer.toString(size_square);
            for (int x = 0; x < size_square; x++) {
                for (int y = 0; y < size_square; y++) {
                    map[x][y] = true;
                }
            }
            set_villain(map);
            info_of_game += "\nMap create.\n";
        }
    }
    public void print_map()
    {
        info_of_game = "";
        for (int x = 0; x < size_square; x++) {
            for (int y = 0; y < size_square; y++) {
                if (x == (position[0]) && y == (position[1]))
                    info_of_game += "|.H.|";
                else if (map[x][y] == true)
                    info_of_game += "|--|";
                else if (map[x][y] == false)
                    info_of_game += "|--|";
            }
            info_of_game += "\n";
        }
    }
    public void print_map_cheat()
    {
        info_of_game = "";
        for (int x = 0; x < size_square; x++) {
            for (int y = 0; y < size_square; y++) {
                if (x == (position[0]) && y == (position[1]))
                    info_of_game += "|.H.|";
                else if (map[x][y] == true)
                    info_of_game += "|_e_|";
                else if (map[x][y] == false)
                    info_of_game += "|_v_|";
            }
            info_of_game += "\n";
            info_of_move = "e - empty cell. v - cell with varrior";
        }
    }

    public SuperHero getSuperHero()
    {
        return hero;
    }

    public static void set_villain(boolean[][] map) {
        int size = (size_square / 2);
        position[0] = prev_position[0] = size;
        position[1] = prev_position[1] = size;

        for (int i = 0; i < size_square; i++) {
            if (i != (size_square / 2)) {
                int random = (int) (Math.random() * 100 % size_square);
                map[i][random] = false;
            }
        }
        info_of_game += "\nSet position x: " + Integer.toString(position[0]) + "  y: " + Integer.toString(position[1]);
    }

    public void moves_command(String str) {
        if (not_end() == true) {
            if (str.equalsIgnoreCase("North")) {
                move_notrh();
            } else if (str.equalsIgnoreCase("South")) {
                move_south();
            } else if (str.equalsIgnoreCase("East")) {
                move_east();
            } else if (str.equalsIgnoreCase("West")) {
                move_west();
            }
            print_map();
        }
    }

    public void move_notrh() {
        position[0]--;
        if (!(check_map(position))) {
            checks_buton = false;
        }
        else {
            prev_position[0]--;
            if (this.not_end() == false)
            {
                End_game = true;
                info_of_move += "\nEnd game and map. You win. Congratulation!!!!";
            }
        }
    }

    public void move_south() {
        position[0]++;
        if (!(check_map(position))) {
            checks_buton = false;
        }
        else {
            prev_position[0]++;
            if (this.not_end() == false)
            {
                End_game = true;
                info_of_move += "\nEnd game and map. You win. Congratulation!!!!";
            }
        }
    }

    public void move_east() {
        position[1]--;
        if (!(check_map(position))) {
            checks_buton = false;
        }
        else {
            prev_position[1]--;
            if (this.not_end() == false)
            {
                End_game = true;
                info_of_move += "\nEnd game and map. You win. Congratulation!!!!";
            }
        }
    }

    public void move_west() {
        position[1]++;
        if (!(check_map(position))) {
            checks_buton = false;
        }
        else {
            prev_position[1]++;
            if (this.not_end() == false)
            {
                End_game = true;
                info_of_move += "\nEnd game and map. You win. Congratulation!!!!";
            }
        }
    }

    public boolean not_end() {
        if ((position[0] == 0) || (position[0] == (size_square - 1))) {
            hero.setExperience(hero.getExperience() + 500);
            incr_level();
            End_game = true;
            return (false); }
        else if ((position[1] == 0) || (position[1] == (size_square - 1))) {
            info_of_game += "\n position[0][1]" + Integer.toString(position[0]) + "  " + Integer.toString(position[1]);
            End_game = true;
            hero.setExperience(hero.getExperience() + 500);
            incr_level();
            return (false);
        }
        return (true);
    }

    public boolean check_map(int[] x) {
        return (map[x[0]][x[1]]);
    }

    public void run()
    {
        info_of_move += "You chose run";
        int what_to_do = (int)(Math.random() * 10 % 2);
        if (what_to_do == 0)
        {
            info_of_move += "\nYou must fight..";
            if (fight())
            {
                map[position[0]][position[1]] = true;
                prev_position[0] = position[0];
                prev_position[1] = position[1];
            }
            else {
                End_game = true;
                info_of_move += "\nYou lose fight. Game over!!!";
            }
        }
        else {
            info_of_move += "\nYou run successful";
            position[0] = prev_position[0];
            position[1] = prev_position[1];
        }
    }

    public boolean fight()
    {
        monster = CharacthersFactory.getRandomVillain();
        int exper_incr = monster.getDefence();
        info_of_move = "";
        while (hero.getHit_points() > 0 && monster.getDefence() > 0)
        {
            int luck = (int)(Math.random() * 10 % 3);
            switch (luck) {
                case 0:
                    info_of_move += "\nHero attack enemy. Enemy attack hero.";
                    monster.setDefence((monster.getDefence() - hero.getAttack()) >= 0 ? (monster.getDefence() - hero.getAttack()):0);
                    hero.setDamage(monster.getAttack());
                    info_of_move += "\n" + monster.getenumClass() + " attack hero: " + monster.getAttack();
                    info_of_move += "\n" + hero.getenumClass() + " attack monster: " + hero.getAttack();
                    break;
                case 1:
                    info_of_move += "\nEnemy attack hero.";
                    hero.setDamage(monster.getAttack());
                    info_of_move += "\n" + monster.getenumClass() + " attack hero: " + monster.getAttack();
                    break;
                case 2:
                    info_of_move += "\nHero attack enemy.";
                    monster.setDefence((monster.getDefence() - hero.getAttack()) >= 0 ? (monster.getDefence() - hero.getAttack()):0);
                    info_of_move += "\n" + hero.getenumClass() + " attack monster: " + hero.getAttack();
                    break;
            }
        }
        if (hero.getHit_points() > 0) {
            hero.setExperience(hero.getExperience() + exper_incr);
            map[position[0]][position[1]] = true;
            incr_level();
            info_of_move += "\nHero is win fight!!!";
            if (this.not_end() == false)
            {
                info_of_move += "\nEnd game end map";
                Data.deleteSHero(hero.getName());
                Data.addSHero(hero);
                sleep_on(2);
            }
            info_of_move += "\nNow you can move. But you must be very carefull!!!";
            return (true);
        }
        info_of_move += "\nHero is lose fight!!!";
        End_game = true;
        return (false);
    }

    public void add_artifacts()
    {
        if (monster.getAttack() == 10) {
            hero.setArmor(ArtifactsFactory.getRandomArmor());
            info_of_move += "\nHero take Armor artifact.";
        }
        else if (monster.getAttack() == 20) {
            hero.setHelm(ArtifactsFactory.getRandomHelm());
            info_of_move += "\nHero take Helm artifact.";
        }
        else {
            hero.setWeapon(ArtifactsFactory.getRandomWeapon());
            info_of_move += "\nHero take Weapon artifact.";
        }
    }

    public void incr_level()
    {
        if (hero.getExperience() >= ((hero.getLevel() + 1) * 1000 + (int)(Math.pow((hero.getLevel() - 1), 2.0)) * 450) && hero.getLevel() <= 6)
        {
            hero.setLevel(hero.getLevel() + 1);
            info_of_move += "\nLevel increase";
        }
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
