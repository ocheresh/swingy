package ocheresh.swingy.model.arifacts;

public class ArtifactsFactory {

    public static Weapon getWeapon(WeaponType type)
    {
        switch (type)
        {
            case LONG_SWORD:
                return new Weapon("long sword", 20);
            case LONG_BOW:
                return new Weapon("long bow", 15);
            case SHORT_SWORD:
                return new Weapon("short sword", 10);
        }
        return null;
    }

    public static WeaponType getWeaponType(String type)
    {
        WeaponType temp;
        if (type.equals("long sword"))
            temp = WeaponType.LONG_SWORD;
        else if (type.equals("long bow"))
            temp = WeaponType.LONG_BOW;
        else
            temp = WeaponType.SHORT_SWORD;
        return temp;
    }

    public static Weapon getWeapon(String type)
    {
        return (ArtifactsFactory.getWeapon(ArtifactsFactory.getWeaponType(type)));
    }

    public static Weapon getRandomWeapon()
    {
        int luck = (int)(Math.random() * 10 % 3);
        switch (luck)
        {
            case 0:
                return new Weapon("long sword", 20);
            case 1:
                return new Weapon("long bow", 15);
            case 2:
                return new Weapon("short sword", 10);
        }
        return null;
    }

    public static Armor getArmor(ArmorType type)
    {
        switch (type)
        {
            case CHINMAIL_HAUBERK:
                return new Armor("chinmail hauberk", 50);
            case BREASTPATES:
                return new Armor("breastpates", 45);
            case PLATE_ARMOR:
                return new Armor("plate armor", 40);
        }
        return null;
    }

    public static ArmorType getArmorType(String type)
    {
        ArmorType temp;
        if (type.equals("breastpates"))
            temp = ArmorType.BREASTPATES;
        else if (type.equals("chinmail hauberk"))
            temp = ArmorType.CHINMAIL_HAUBERK;
        else
            temp = ArmorType.PLATE_ARMOR;
        return temp;
    }

    public static Armor getArmor(String type)
    {
        return (ArtifactsFactory.getArmor(ArtifactsFactory.getArmorType(type)));
    }


    public static Armor getRandomArmor()
    {
        int luck = (int)(Math.random() * 10 % 3);
        switch (luck)
        {
            case 0:
                return new Armor("chinmail hauberk", 50);
            case 1:
                return new Armor("breastpates", 45);
            case 2:
                return new Armor("plate armor", 40);
        }
        return null;
    }

    public static Helm getHelm(HelmType type)
    {
        switch (type)
        {
            case CAP:
                return new Helm("cap", 50);
            case CROWN:
                return new Helm("crown", 45);
            case MASK:
                return new Helm("mask", 40);
        }
        return null;
    }

    public static HelmType getHelmType(String type)
    {
        HelmType temp;
        if (type.equals("cap"))
            temp = HelmType.CAP;
        else if (type.equals("crown"))
            temp = HelmType.CROWN;
        else
            temp = HelmType.MASK;
        return temp;
    }

    public static Helm getHelm(String type)
    {
        return (ArtifactsFactory.getHelm(ArtifactsFactory.getHelmType(type)));
    }


    public static Helm getRandomHelm()
    {
        int luck = (int)(Math.random() * 10 % 3);
        switch (luck)
        {
            case 0:
                return new Helm("cap", 50);
            case 1:
                return new Helm("crown", 45);
            case 2:
                return new Helm("mask", 40);
        }
        return null;
    }
}
