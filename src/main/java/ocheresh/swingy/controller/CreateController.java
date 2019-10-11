package ocheresh.swingy.controller;

import ocheresh.swingy.view.create.MainCreate;

public class CreateController {

    private MainCreate mainCreate;

    public CreateController(MainCreate mainCreatenew) { mainCreate = mainCreatenew; }

    public void createHeroPressed() { mainCreate.create_newHero(); }

    public void backpressed() {mainCreate.back();}

    public void switch_pressed() { mainCreate.switch_press(); }
}
