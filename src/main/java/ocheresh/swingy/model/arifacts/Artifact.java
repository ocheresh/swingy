package ocheresh.swingy.model.arifacts;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Artifact {

    private String name;
    private int attack;

    public Artifact(String name, int attack) {
        this.attack = attack;
        this.name = name;
    }
}
