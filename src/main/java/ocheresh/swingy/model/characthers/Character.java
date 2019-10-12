package ocheresh.swingy.model.characthers;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
public abstract class Character {

    @Min(value = 0, message = "Attack should not be less than 0")
    @Max(value = 100, message = "Attack should not be greater than 100")
    private int attack;
    @Min(value = 0, message = "Defence should not be less than 0")
    @Max(value = 100, message = "Defence should not be greater than 100")
    private int defence;
    public abstract String getenumClass();
}
