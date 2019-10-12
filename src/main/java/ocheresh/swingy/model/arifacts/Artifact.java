package ocheresh.swingy.model.arifacts;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public abstract class Artifact {

    @NotNull(message = "Name cannot be null")
    private String name;
    @Min(value = 0, message = "Attack should not be less than 0")
    @Max(value = 100, message = "Attack should not be greater than 100")
    private int attack;

    public Artifact(String name, int attack) {
        this.attack = attack;
        this.name = name;
    }
}
