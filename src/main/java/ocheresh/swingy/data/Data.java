package ocheresh.swingy.data;

import lombok.NonNull;
import ocheresh.swingy.model.arifacts.ArtifactsFactory;
import ocheresh.swingy.model.characthers.CharacthersFactory;
import ocheresh.swingy.model.characthers.SuperHero;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class Data {
    public static Statement statmt;
    public static ResultSet resSet;
    public static Connection co;

    public static void connect()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            co = DriverManager.getConnection("jdbc:sqlite:Heroes.db");
            statmt = co.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS heroes " +
                    "(name varchar(255)," +
                    " class varchar(255), " +
                    " level int, " +
                    " experience int, " +
                    " weapon varchar(255), " +
                    " armor varchar(255), " +
                    " helm varchar(255))";
            statmt.executeUpdate(sql);
            resSet = statmt.executeQuery("SELECT * FROM heroes");
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public static void addSHero(@NonNull SuperHero hero) {
        try (PreparedStatement statement = co.prepareStatement(
                "INSERT INTO heroes(`name`, `class`, level, experience, `weapon`, `armor`, `helm`) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?)")) {
            statement.setObject(1, hero.getName());
            statement.setObject(2, hero.getenumClass());
            statement.setObject(3, hero.getLevel());
            statement.setObject(4, hero.getExperience());
            statement.setObject(5, hero.getWeapon().getName());
            statement.setObject(6, hero.getArmor().getName());
            statement.setObject(7, hero.getHelm().getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int size()
    {
        int t = 0;
        try {
            statmt = co.createStatement();
            resSet = statmt.executeQuery("SELECT * FROM heroes");
            while(resSet.next())
                t++;
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        return (t);
    }

    public static void deleteSHero(String id) {
        try (PreparedStatement statement = co.prepareStatement(
                "DELETE FROM heroes WHERE name = ?")) {
            statement.setObject(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> getinfo()
    {
        Map temp = new HashMap<String, String>();

        try {
            statmt = co.createStatement();
            resSet = statmt.executeQuery("SELECT * FROM heroes");

            while(resSet.next())
            {
                String  name = resSet.getString("name");
                String  clas = "Class: " + resSet.getString("class") + "\n";
                String level = "Level: " + Integer.toString(resSet.getInt("level")) + "\n";
                String exper = "Eperience: " + Integer.toString(resSet.getInt("experience")) + "\n";
                String weapon = "Weapon: " + resSet.getString("weapon") + "\n";
                String armor = "Armor: " + resSet.getString("armor") + "\n";
                String helm = "Helm: " + resSet.getString("helm") + "\n";
                String all = level + clas + exper + weapon + armor + helm;
                temp.put(name, all);
            }
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        return (temp);
    }

    public static boolean check_name_of_shero(String name)
    {
        try {
            statmt = co.createStatement();
            resSet = statmt.executeQuery("SELECT * FROM heroes");

            while(resSet.next())
            {
               if (name.equals(resSet.getString("Name")))
                   return (true);
            }
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        return (false);
    }

    public static SuperHero getSuperHero(String heroName)
    {
        Map temp = new HashMap<String, SuperHero>();

        try {
            statmt = co.createStatement();
            resSet = statmt.executeQuery("SELECT * FROM heroes");

            while(resSet.next())
            {
                String  name = resSet.getString("Name");
                String  clas = resSet.getString("class");
                int level = resSet.getInt("level");
                int exper = resSet.getInt("experience");
                String weapon = "Weapon: " + resSet.getString("weapon") + "\n";
                String armor = "Armor: " + resSet.getString("armor") + "\n";
                String helm = "Helm: " + resSet.getString("helm") + "\n";
                SuperHero hero = CharacthersFactory.getSuperHero(clas);
                hero.setName(name);
                hero.setLevel(level);
                hero.setExperience(exper);
                hero.setWeapon(ArtifactsFactory.getWeapon(weapon));
                hero.setArmor(ArtifactsFactory.getArmor(armor));
                hero.setHelm(ArtifactsFactory.getHelm(helm));
                temp.put(name, hero);
            }
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        return ((SuperHero)(temp.get(heroName)));
    }

    public static void print()
    {
        Map <String, String> map = Data.getinfo();
        try {
            for (String key : map.keySet()) {
                System.out.println("Name: " + key + " \n" + map.get(key));
            }
            System.out.println();
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void closeDB()
    {
        try {
            co.close();
            statmt.close();
            resSet.close();
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
