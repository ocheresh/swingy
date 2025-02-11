package ocheresh.swingy.view.game;

import ocheresh.swingy.controller.GameController;
import ocheresh.swingy.data.Data;
import ocheresh.swingy.data.ScanInfo;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

public class GameFrame extends JFrame implements MainGame {

    private GameController gamecontroller;

    private JPanel panel;
    private JTextArea hero_info;
    private JTextArea game_info;
    private JTextArea move_info;
    private JComboBox mov;
    private JButton butFight;
    private JButton butRun;
    private JButton butSwitch;
    JToggleButton toggleButton;
    private JScrollPane scrollPane;
    private JScrollPane scrollPanemove;
    String [] moves = {"North", "South", "East", "West"};

    public GameFrame()
    {
        super("Game ...");

        gamecontroller = new GameController(this);

        panel = new JPanel();
        move_info = new JTextArea(5, 80);
        move_info.setEnabled(false);
        hero_info = new JTextArea(23, 20);
        hero_info.setEnabled(false);
        game_info = new JTextArea(20, 80);
        game_info.setEnabled(false);
        mov = new JComboBox(moves);
        butFight = new JButton("Fight!!!");
        butFight.setEnabled(false);
        butRun = new JButton("Run. Please Run.");
        butRun.setEnabled(false);
        butSwitch = new JButton("Switch");
        scrollPane = new JScrollPane(game_info);
        scrollPanemove = new JScrollPane(move_info);
        toggleButton = new JToggleButton("Cheat on/off");
        toggleButton.setSelected(false);

        hero_info.setText(gamecontroller.getGame().getInfo_of_hero());

        Box box1 = Box.createVerticalBox();
        box1.add(hero_info);
        box1.add(mov);

        Box box2 = Box.createVerticalBox();
        box2.add(scrollPane);
        box2.add(scrollPanemove);

        Box box3 = Box.createHorizontalBox();
        box3.add(butFight);
        box3.add(butRun);
        box3.add(butSwitch);
        box3.add(toggleButton);

        panel.add(box1, BorderLayout.EAST);
        panel.add(box2, BorderLayout.WEST);
        panel.add(box3, BorderLayout.SOUTH);

        butSwitch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gamecontroller.switchpressed();
                toggleButton.setSelected(false);
                hero_info.setText(gamecontroller.getGame().getInfo_of_hero());
            }
        });

        butFight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sleep_on(3);
                gamecontroller.fightpressed();
                toggleButton.setSelected(false);
                hero_info.setText(gamecontroller.getGame().getInfo_of_hero());
            }
        });

        butRun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sleep_on(2);
                gamecontroller.runpressed();
                toggleButton.setSelected(false);
                hero_info.setText(gamecontroller.getGame().getInfo_of_hero());
            }
        });

        toggleButton.addItemListener(itemListener);


        mov.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                gamecontroller.move_operations(mov.getSelectedItem().toString());
                game_info.setText(gamecontroller.getGame().getInfo_of_game());
                toggleButton.setSelected(false);
                hero_info.setText(gamecontroller.getGame().getInfo_of_hero());
            }
        });

        gamecontroller.getGame().print_map();
        game_info.setText(gamecontroller.getGame().getInfo_of_game());
        move_info.setText(gamecontroller.getGame().getInfo_of_move());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 500);
        setResizable(false);
        setLayout(null);
        setContentPane(panel);
        setVisible(true);
    }

    ItemListener itemListener = new ItemListener() {
        public void itemStateChanged(ItemEvent itemEvent) {
            int state = itemEvent.getStateChange();
            if (state == ItemEvent.SELECTED) {
                gamecontroller.cheat_pressed();
            } else {
                gamecontroller.getGame().print_map();
                move_info.setText("");
            }
            game_info.setText(gamecontroller.getGame().getInfo_of_game());
        }
    };

    @Override
    public void SwitchPressed()
    {
        this.setVisible(false);
        this.dispose();
        new GameConsole();
    }

    @Override
    public void moves_command(String str) {
        gamecontroller.getGame().moves_command(str);
        if(gamecontroller.getGame().getChecks_buton() == false)
        {
            JOptionPane.showMessageDialog( this, "Now you have to choose run or fight", "Choose", JOptionPane.DEFAULT_OPTION );
            butFight.setEnabled(true);
            butRun.setEnabled(true);
            mov.setEnabled(false);
            butSwitch.setEnabled(false);
            gamecontroller.getGame().setChecks_buton(true);
        }
        gamecontroller.getGame().print_map();
        game_info.setText(gamecontroller.getGame().getInfo_of_game());
        move_info.setText(gamecontroller.getGame().getInfo_of_move());
        if (gamecontroller.getGame().getEnd_game() == true) {
            JOptionPane.showMessageDialog(this, "End game", "Inform", JOptionPane.DEFAULT_OPTION);
            Data.deleteSHero(gamecontroller.getGame().getSuperHero().getName());
            Data.addSHero(gamecontroller.getGame().getSuperHero());
            gamecontroller.end_game();
        }

    }

    @Override
    public void fightpressed() {
        if (gamecontroller.getGame().fight() == false)
        {
            game_info.setText(gamecontroller.getGame().getInfo_of_game());
            hero_info.setText(gamecontroller.getGame().getInfo_of_hero());
            move_info.setText(gamecontroller.getGame().getInfo_of_move());
            System.out.println(gamecontroller.getGame().getInfo_of_move());
            JOptionPane.showMessageDialog( this, "You LOSE", "Result of fight.", JOptionPane.DEFAULT_OPTION );
            if (gamecontroller.getGame().getEnd_game() == true) {
                JOptionPane.showMessageDialog(this, "End game", "Inform", JOptionPane.DEFAULT_OPTION);
            }
            gamecontroller.end_game();
        }
        else {
            JOptionPane.showMessageDialog( this, "You WIN", "Result of fight.", JOptionPane.DEFAULT_OPTION );
            gamecontroller.add_artifac_pres();
            butFight.setEnabled(false);
            butRun.setEnabled(false);
            mov.setEnabled(true);
            butSwitch.setEnabled(true);
            gamecontroller.getGame().print_map();
            game_info.setText(gamecontroller.getGame().getInfo_of_game());
            move_info.setText(gamecontroller.getGame().getInfo_of_move());
            if (gamecontroller.getGame().getEnd_game() == true) {
                JOptionPane.showMessageDialog(this, "End game", "Inform", JOptionPane.DEFAULT_OPTION);
                gamecontroller.end_game();
            }
        }
    }

    @Override
    public void runPressed() {
        gamecontroller.getGame().run();
        butFight.setEnabled(false);
        butRun.setEnabled(false);
        mov.setEnabled(true);
        butSwitch.setEnabled(true);
        gamecontroller.getGame().print_map();
        game_info.setText(gamecontroller.getGame().getInfo_of_game());
        move_info.setText(gamecontroller.getGame().getInfo_of_move());
        if (gamecontroller.getGame().getEnd_game() == true) {
            JOptionPane.showMessageDialog(this, "End game", "Inform", JOptionPane.DEFAULT_OPTION);
            gamecontroller.end_game();
        }
    }

    @Override
    public void add_artifac_pres() {
        int luck = (int)(Math.random() * 10 % 2);
        if (luck == 0) {
            int n = JOptionPane.showConfirmDialog(
                    this, "Would you like to take artifact?",
                    "Question",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                gamecontroller.getGame().add_artifacts();
            } else if (n == JOptionPane.NO_OPTION) {
                System.out.println("no artifact");
            }
        }
    }

    @Override
    public void end_game() {
        Data.closeDB();
        ScanInfo.close();
        this.dispose();
        System.exit(0);
    }

    @Override
    public void cheat_pressed() {
        gamecontroller.getGame().print_map_cheat();
        move_info.setText(gamecontroller.getGame().getInfo_of_move());
        gamecontroller.getGame().setInfo_of_move("");
    }


    public void sleep_on(int seconds)
    {
        try {
            Thread.sleep(1000 * seconds);
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

}


