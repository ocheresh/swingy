package ocheresh.swingy.model.characthers;


//import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import ocheresh.swingy.model.arifacts.Armor;
import ocheresh.swingy.model.arifacts.Helm;
import ocheresh.swingy.model.arifacts.Weapon;
import ocheresh.swingy.model.arifacts.WeaponType;

//@Builder
@Getter
@Setter
public class SuperHero extends Character {
    @NonNull
    private Weapon weapon;
    @NonNull
    private Armor armor;
    @NonNull
    private Helm helm;

    String name;
    EnumSuperHero enumSuperHero;
    int level;
    int experience;
    int hit_points;

    @Override
    public String getenumClass()
    {
        if (enumSuperHero == EnumSuperHero.ELF)
            return "ELF";
        return "KNIGHT";
    }

    public void setDamage(int attack)
    {
        int armor_attack = this.getArmor().getAttack() - attack;
        if (armor_attack >= 0)
        {
            this.getArmor().setAttack(armor_attack);
        }
        else {
            this.getArmor().setAttack(0);
            int defence = this.getDefence() + armor_attack;
            if (defence >= 0)
                this.setDefence(defence);
            else
            {
                this.setDefence(0);
                int hit_point = this.getHelm().getAttack() + defence;
                if (hit_point >= 0)
                    this.getHelm().setAttack(hit_point);
                else {
                    this.getHelm().setAttack(0);
                    int hit_live = this.getHit_points() + hit_point;
                    if (hit_live >= 0)
                        this.setHit_points(hit_live);
                    else
                        this.setHit_points(0);
                }
            }
        }
    }

    public static class Builder {

        private SuperHero superHero;

        public Builder()
        {
            superHero = new SuperHero();
        }

        public Builder withName(String name)
        {
            superHero.name = name;
            return this;
        }

        public Builder withClass(EnumSuperHero enumSuperHero)
        {
            superHero.enumSuperHero = enumSuperHero;
            return this;
        }

        public Builder withLevel(int level)
        {
            superHero.level = level;
            return this;
        }

        public Builder withExperience(int experience)
        {
            superHero.experience = experience;
            return this;
        }

        public Builder withAttack(int attack)
        {
            superHero.setAttack(attack);
            return this;
        }

        public Builder withDefence(int defence)
        {
            superHero.setDefence(defence);
            return this;
        }

        public Builder withHitPoints(int hitPoints)
        {
            superHero.hit_points = hitPoints;
            return this;
        }

        public Builder withWeapon(Weapon weapon)
        {
            superHero.weapon = weapon;
            return this;
        }

        public Builder withArmor(Armor armor)
        {
            superHero.armor = armor;
            return this;
        }

        public Builder withHelm(Helm helm)
        {
            superHero.helm = helm;
            return this;
        }

        public SuperHero build()
        {
            return superHero;
        }
    }
}
