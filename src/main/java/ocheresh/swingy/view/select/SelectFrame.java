package ocheresh.swingy.view.select;

import ocheresh.swingy.controller.SelectController;
import ocheresh.swingy.data.Data;
import ocheresh.swingy.model.Game;
import ocheresh.swingy.model.characthers.SuperHero;
import ocheresh.swingy.model.characthers.CharacthersFactory;
import ocheresh.swingy.view.game.GameFrame;
import ocheresh.swingy.view.start.MainFrame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;

public class SelectFrame extends JFrame implements MainSelect{

    private JPanel panel;
    private JLabel lbavailableheroes;
    private JLabel lbherostats;
    private JList aravailb;
    private JTextArea arherostas;
    private JButton butSelect;
    private JButton butBack;
    private JButton butSwitch;
    private Game game;
    private SelectController selectController;

    public SelectFrame()
    {
        super("SWINGY..Select hero");
        panel = new JPanel();
        lbavailableheroes = new JLabel("Available heroes");
        lbherostats = new JLabel("Hero stats");

        selectController = new SelectController(this);

        arherostas = new JTextArea(25, 20);
        arherostas.setEditable(false);
        butSelect = new JButton("Select");
        butSelect.setEnabled(false);
        butBack = new JButton("Back");
        butSwitch = new JButton("Switch");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Map<String, String> map = Data.getinfo();
        String availab[] = new String[map.size()];
        int i = 0;
        for (String key : map.keySet())
        {
            availab[i++] = key;
        }
        aravailb = new JList(availab);

        Box box1 = Box.createVerticalBox();
        box1.add(lbavailableheroes);
        box1.add(aravailb);

        Box box2 = Box.createVerticalBox();
        box2.add(lbherostats);
        box2.add(arherostas);

        Box box3 = Box.createHorizontalBox();
        box3.add(butBack);
        box3.add(butSelect);
        box3.add(butSwitch);

        panel.add(box1, BorderLayout.EAST);
        panel.add(box2, BorderLayout.WEST);
        panel.add(box3, BorderLayout.SOUTH);

        butSwitch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectController.switch_controller();
            }
        });

        butBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectController.press_back();
            }
        });

        butSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectController.select();
            }
        });


        aravailb.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                arherostas.setText(Data.getinfo().get(aravailb.getSelectedValue().toString()));
                butSelect.setEnabled(true);
            }
        });


        setSize(600, 500);
        setResizable(false);
        setLayout(null);
        setContentPane(panel);
        setVisible(true);

    }

    @Override
    public void select_press()
    {
        this.setVisible(false);
        game = new Game();
        Game.setSuperHero(Data.getSuperHero(aravailb.getSelectedValue().toString()));
        new GameFrame();
    }

    @Override
    public void switch_press()
    {
        super.setVisible(false);
        new SelectConsole();
    }

    @Override
    public void back_pres() {
        super.setVisible(false);
        new MainFrame();
    }

}
