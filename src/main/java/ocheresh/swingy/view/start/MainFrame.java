package ocheresh.swingy.view.start;

import ocheresh.swingy.controller.MainController;
import ocheresh.swingy.view.create.CreateFrame;
import ocheresh.swingy.view.select.SelectFrame;
import ocheresh.swingy.view.start.MainView;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements MainView
{
    private JPanel mainPanel;
    private JButton butCreateHero;
    private JButton butSelectPrevious;
    private JButton butSwitch;

    private MainController mainController;

    public MainFrame() {
        super("SWINGY");
        mainController = new MainController(this);
        mainPanel = new JPanel();
        butCreateHero = new JButton("Create a Hero");
        setSize(250, 500);
        butSelectPrevious = new JButton("Select previous Hero");
        butSwitch = new JButton("Switch");
        mainPanel.add(createPanel(new SoftBevelBorder(BevelBorder.RAISED),butCreateHero));
        mainPanel.add(createPanel(new SoftBevelBorder(BevelBorder.RAISED),butSelectPrevious));
        mainPanel.add(createPanel(new SoftBevelBorder(BevelBorder.RAISED),butSwitch));


        setContentPane(mainPanel);
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        butSwitch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainController.switchPressed();
            }
        });

        butSelectPrevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.selectHeroPressed();
            }
        });

        butCreateHero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.createHeroPressed();
            }
        });

    }

    private JPanel createPanel(Border border, JButton button) {
        JPanel panel = new JPanel();
        panel.add(button);
        panel.setBorder(new CompoundBorder(new EmptyBorder(12,12,12,12), border));
        return panel;
    }

    @Override
    public void createHero()
    {
        setVisible(false);
        new CreateFrame();
    }

    @Override
    public void switchView()
    {
        setVisible(false);
        new MainConsole();
    }

    @Override
    public void selectHero()
    {
        setVisible(false);
        new SelectFrame();
    }
}
