package ocheresh.swingy.view.create;

import ocheresh.swingy.controller.CreateController;
import ocheresh.swingy.data.Data;
import ocheresh.swingy.view.start.MainFrame;

import javax.swing.*;
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
    private JLabel name;
    private JLabel stats;
    final JList<String> list;

    public CreateFrame()
    {
        createController = new CreateController(this);

        createpanel = new JPanel();
        create = new JButton("Create");
        back = new JButton("back");
        content = new JTextArea(20, 10);
        name = new JLabel("Hero: ");
        stats = new JLabel("Stats: ");

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

        createpanel.add(box1, BorderLayout.SOUTH);
        createpanel.add(box2, BorderLayout.NORTH);

        back.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            createController.backpressed();
        }
    });

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                content.setText(help_selected(list.getSelectedValue()));
            }
        });

        setResizable(false);
        setContentPane(createpanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 400);
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
    public void create_newHero()
    {

    }

    public String help_selected(String str)
    {
        if (str.equals("Knight"))
        {
            return ("Name: Knight \n" +
                    "Class: Knight \n" +
                    "Level: 1\n" +
                    "Experience: 0\n" +
                    "Attack: 10\n" +
                    "Defence: 100\n");
        }
        else
            return ("Name: Elf \n" +
                    "Class: Elf \n" +
                    "Level: 1\n" +
                    "Experience: 0\n" +
                    "Attack: 15\n" +
                    "Defence: 80\n");
    }
}
