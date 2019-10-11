package ocheresh.swingy.controller;


import ocheresh.swingy.view.select.MainSelect;

public class SelectController {

    private MainSelect selectController;

    public SelectController(MainSelect main)
    {
        selectController = main;
    }

    public void select()
    {
        selectController.select_press();
    }

    public void switch_controller()
    {
        selectController.switch_press();
    }

    public void press_back()
    {
        selectController.back_pres();
    }
}
