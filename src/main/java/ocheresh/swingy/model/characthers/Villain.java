package ocheresh.swingy.model.characthers;

//import lombok.Builder;
//
////@Builder
public class Villain extends Character {

    public static class Builder {

        private Villain villain;

        public Builder()
        {
            villain = new Villain();
        }

        public Builder withAttack(int attack)
        {
            villain.setAttack(attack);
            return this;
        }

        public Builder withDefence(int defence)
        {
            villain.setDefence(defence);
            return this;
        }

        public Villain build()
        {
            return villain;
        }
    }

}


