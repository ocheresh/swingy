package ocheresh.swingy.controller;

import ocheresh.swingy.view.start.MainView;
import ocheresh.swingy.controller.*;

public class MainController {

    private MainView mainView;

    public MainController(MainView view)
    {
        mainView = view;
    }

    public void createHeroPressed()
    {
        mainView.createHero();
    }

    public void selectHeroPressed()
    {
        mainView.selectHero();
    }

    public void switchPressed()
    {
        mainView.switchView();
    }
}
