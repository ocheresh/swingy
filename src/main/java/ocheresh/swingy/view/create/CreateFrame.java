package ocheresh.swingy.view.create;

import ocheresh.swingy.controller.CreateController;
import ocheresh.swingy.data.Data;
import ocheresh.swingy.model.Game;
import ocheresh.swingy.model.characthers.CharacthersFactory;
import ocheresh.swingy.view.game.GameFrame;
import ocheresh.swingy.view.start.MainFrame;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CreateFrame extends JFrame implements MainCreate {

    private CreateController createController;

    private JPanel createpanel;
    private JTextArea content;
    private final String[] datalist = {"Knight", "Elf"};
    private JButton create;
    private JButton back;
    private JButton switchh;
    private JLabel name;
    private JLabel stats;
    final JList<String> list;
    private JLabel set_name;
    private JTextField textArea;
    private Game game;

    public CreateFrame()
    {
        createController = new CreateController(this);

        createpanel = new JPanel();
        create = new JButton("Create");
        create.setEnabled(false);
        back = new JButton("back");
        content = new JTextArea(20, 10);
        content.setEnabled(false);
        name = new JLabel("Hero: ");
        stats = new JLabel("Stats: ");
        set_name = new JLabel("Please enter name of the hero: (max 250 ch)");
        textArea = new JTextField();
        switchh = new JButton("Switch");

        list = new JList<String>(datalist);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        list.addSelectionInterval(1, 1);
        list.setPrototypeCellValue("Index 1234567890");

        Box box1 = Box.createHorizontalBox();
        box1.add(name);
        box1.add(list);
        box1.add(stats);
        box1.add(content);

        Box box2 = Box.createHorizontalBox();
        box2.add(back);
        box2.add(create);
        box2.add(switchh);

        Box box3 = Box.createVerticalBox();
        box3.add(set_name);
        box3.add(textArea);

        createpanel.add(box3, BorderLayout.SOUTH);
        createpanel.add(box1, BorderLayout.SOUTH);
        createpanel.add(box2, BorderLayout.NORTH);

        back.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            createController.backpressed();
        }
    });

        switchh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createController.switch_pressed();
            }
        });

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createController.createHeroPressed();
            }
        });

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                content.setText(help_selected(list.getSelectedValue()));
                if (textArea.getText().length() > 0 && textArea.getText().length() < 250)
                    create.setEnabled(true);
            }
        });

        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                warn();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                warn();
            }

            public void warn()
            {
                if ((textArea.getText().length() > 0 && textArea.getText().length() < 250) && content.getText().length() > 0)
                    create.setEnabled(true);
                else
                    create.setEnabled(false);
            }
        });

        setResizable(false);
        setContentPane(createpanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 450);
        setVisible(true);
        setLayout(null);

    }

    @Override
    public void back()
    {
        this.setVisible(false);
        new MainFrame();
    }

    @Override
    public void switch_press() {
        this.setVisible(false);
        this.dispose();
        new CreateConsole();
    }

    @Override
    public void create_newHero()
    {
        this.setVisible(false);
        this.dispose();
        game = new Game();
        Game.setSuperHero(CharacthersFactory.getSuperHero(list.getSelectedValue()));
        game.getSuperHero().setName(textArea.getText());
        new GameFrame();
    }

    public String help_selected(String str)
    {
        if (str.equals("Knight"))
        {
            return ("Class: Knight \n" +
                    "Level: 1\n" +
                    "Experience: 0\n" +
                    "Attack: 10\n" +
                    "Defence: 100\n");
        }
        else
            return ("Class: Elf \n" +
                    "Level: 1\n" +
                    "Experience: 0\n" +
                    "Attack: 15\n" +
                    "Defence: 80\n");
    }
}
