package ocheresh.swingy.model.characthers;

import ocheresh.swingy.model.arifacts.*;

public class CharacthersFactory {

    public static SuperHero getSuperHero(EnumSuperHero type)
    {
        switch (type)
        {
            case ELF:
                return new SuperHero.Builder()
                        .withName("")
                        .withClass(EnumSuperHero.ELF)
                        .withLevel(1)
                        .withExperience(0)
                        .withAttack(50)
                        .withDefence(10)
                        .withHitPoints(100)
                        .withWeapon(ArtifactsFactory.getWeapon(WeaponType.LONG_SWORD))
                        .withArmor(ArtifactsFactory.getArmor(ArmorType.BREASTPATES))
                        .withHelm(ArtifactsFactory.getHelm(HelmType.MASK))
                        .build();
            case KNIGHT:
                return new SuperHero.Builder()
                        .withName("")
                        .withClass(EnumSuperHero.KNIGHT)
                        .withLevel(1)
                        .withExperience(0)
                        .withAttack(30)
                        .withDefence(50)
                        .withHitPoints(100)
                        .withWeapon(ArtifactsFactory.getWeapon(WeaponType.LONG_SWORD))
                        .withArmor(ArtifactsFactory.getArmor(ArmorType.PLATE_ARMOR))
                        .withHelm(ArtifactsFactory.getHelm(HelmType.MASK))
                        .build();

        }
        return null;
    }

    public static EnumSuperHero getEnumSuperHero(String type)
    {
        if(type.equals("KNIGHT"))
            return (EnumSuperHero.KNIGHT);
        return (EnumSuperHero.ELF);
    }

    public static SuperHero getSuperHero(String type)
    {
        return (CharacthersFactory.getSuperHero(CharacthersFactory.getEnumSuperHero(type)));
    }

    public static SuperHero getRandomSuperHero(String type)
    {
        int luck = (int)(Math.random() * 10 % 2);
        switch (luck)
        {
            case 0:
                return new SuperHero.Builder()
                        .withName("")
                        .withClass(EnumSuperHero.ELF)
                        .withLevel(1)
                        .withExperience(0)
                        .withAttack(50)
                        .withDefence(10)
                        .withHitPoints(100)
                        .withWeapon(ArtifactsFactory.getWeapon(WeaponType.LONG_SWORD))
                        .withArmor(ArtifactsFactory.getArmor(ArmorType.BREASTPATES))
                        .withHelm(ArtifactsFactory.getHelm(HelmType.MASK))
                        .build();
            case 1:
                return new SuperHero.Builder()
                        .withName("")
                        .withClass(EnumSuperHero.KNIGHT)
                        .withLevel(1)
                        .withExperience(0)
                        .withAttack(30)
                        .withDefence(50)
                        .withHitPoints(100)
                        .withWeapon(ArtifactsFactory.getWeapon(WeaponType.LONG_SWORD))
                        .withArmor(ArtifactsFactory.getArmor(ArmorType.PLATE_ARMOR))
                        .withHelm(ArtifactsFactory.getHelm(HelmType.MASK))
                        .build();
        }
        return null;
    }

    public static Villain getVillain(VillainType type)
    {
        switch (type)
        {
            case EVIL:
               return new Villain.Builder()
                        .withAttack(10)
                        .withDefence(100)
                        .build();
            case BULLY:
                return new Villain.Builder()
                        .withAttack(20)
                        .withDefence(120)
                        .build();
            case DARK_LORD:
                return new Villain.Builder()
                        .withAttack(30)
                        .withDefence(130)
                        .build();
        }
        return null;
    }

    public static Villain getVillain(String type)
    {
        switch (type)
        {
            case "EVIL":
                return new Villain.Builder()
                        .withAttack(10)
                        .withDefence(100)
                        .build();
            case "BULLY":
                return new Villain.Builder()
                        .withAttack(20)
                        .withDefence(120)
                        .build();
            case "DARK_LORD":
                return new Villain.Builder()
                        .withAttack(30)
                        .withDefence(130)
                        .build();
        }
        return null;
    }

    public static Villain getRandomVillain()
    {
        int luck = (int)(Math.random() * 10 % 3);
        switch (luck)
        {
            case 0:
                return new Villain.Builder()
                        .withAttack(10)
                        .withDefence(100)
                        .build();
            case 1:
                return new Villain.Builder()
                        .withAttack(20)
                        .withDefence(120)
                        .build();
            case 2:
                return new Villain.Builder()
                        .withAttack(30)
                        .withDefence(130)
                        .build();
        }
        return null;
    }
}
