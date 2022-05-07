package io.github.MateuszNk.GUI.creators;

import javax.swing.*;
import java.awt.*;

public class DoYouWantToClosePanel {

    private final JDialog jDialog;
    public DoYouWantToClosePanel(JFrame frame) {
        jDialog = new JDialog(frame);
        jDialog.setTitle("Exit");
        jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jDialog.setModal(true);

        ImageIcon icon = new ImageIcon(("/home/admin/IdeaProjects/travelAgency/src/resources/graphics/mountain.png"));
        jDialog.setIconImage(icon.getImage());
        jDialog.setLocationRelativeTo(null);
        jDialog.setLayout(null);
        Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int width = 200;
        int height = 150;
        jDialog.setBounds(centerPoint.x - width / 2, centerPoint.y - height / 2, width, height);
        jDialog.setResizable(false);

        createComponents();
        jDialog.setVisible(true);
    }

    private JLabel communicateJLabel;
    private JButton yesJButton;
    private JButton noJButton;
    public void createComponents() {
        communicateJLabel = new JLabel("Do You want to close?");
        yesJButton = new JButton("YES");
        noJButton = new JButton("NO");

        setParametersOfComponents();

        if ( CreateJMenuBar.getIsDarkTheme() ) {
            paintAllComponents(Color.BLACK, Color.LIGHT_GRAY);
        } else {
            paintAllComponents(Color.WHITE, Color.BLACK);
        }

        addActionsListeners();
        addComponents();
    }

    public void setParametersOfComponents() {
        communicateJLabel.setBounds(30, 10, 200, 20);
        yesJButton.setBounds(30, 45, 60, 25);
        noJButton.setBounds(110, 45, 60, 25);
    }

    public void paintAllComponents(Color backgroundColor, Color foregroundColor) {
        jDialog.getContentPane().setBackground(backgroundColor);
        var setTheme = new SetTheme(backgroundColor, foregroundColor);
        setTheme.setJLabelTheme(communicateJLabel);
        setTheme.setJButtonTheme(yesJButton);
        setTheme.setJButtonTheme(noJButton);
    }

    private final int SUCCESS = 0;
    public void addActionsListeners() {
        yesJButton.addActionListener(e -> {
            jDialog.dispose();
            System.exit(SUCCESS);
        });

        noJButton.addActionListener(e -> jDialog.dispose() );
    }

    public void addComponents() {
        jDialog.add(noJButton);
        jDialog.add(yesJButton);
        jDialog.add(communicateJLabel);
    }
}