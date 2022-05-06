package com.app.GUI.creators;

import com.app.GUI.ConfigureConnectionToDataBasePanel;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ItemEvent;

public class CreateJMenuBar {

    public JMenuBar menuJMenuBar;
    public JMenu optionsJMenu;
    public JMenu configurationJMenu;
    public JRadioButtonMenuItem darkThemeJRadioButtonMenuItem;
    public JRadioButtonMenuItem lightThemeJRadioButtonMenuItem;
    public JMenuItem themeJMenuItem;
    public CreateJMenuBar(JFrame frame) {
        menuJMenuBar = new JMenuBar();
        optionsJMenu = new JMenu("Options");
        menuJMenuBar.add(optionsJMenu);
        configurationJMenu = new JMenu("Configuration");
        menuJMenuBar.add(configurationJMenu);

        ButtonGroup groupOfThemes = new ButtonGroup();
        themeJMenuItem = new JMenu("Themes");
        themeJMenuItem.setOpaque(true);

        lightThemeJRadioButtonMenuItem = new JRadioButtonMenuItem("Light Theme");
        lightThemeJRadioButtonMenuItem.setSelected(true);
        groupOfThemes.add(lightThemeJRadioButtonMenuItem);
        themeJMenuItem.add(lightThemeJRadioButtonMenuItem);

        darkThemeJRadioButtonMenuItem = new JRadioButtonMenuItem("Dark Theme");
        groupOfThemes.add(darkThemeJRadioButtonMenuItem);
        themeJMenuItem.add(darkThemeJRadioButtonMenuItem);

        optionsJMenu.add(themeJMenuItem);
        frame.setJMenuBar(menuJMenuBar);
        isDarkThemeOn();

        configurationJMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent me) {
                new ConfigureConnectionToDataBasePanel(null, false);
            }
            @Override
            public void menuCanceled(MenuEvent e) {}
            @Override
            public void menuDeselected(MenuEvent e) {}
        });


    }

    public static boolean isDarkTheme = false;
    public void isDarkThemeOn() {
        if ( isDarkTheme ) {
            paintAllComponents(Color.BLACK, Color.LIGHT_GRAY);
            isDarkTheme = true;
        } else {
            paintAllComponents(Color.WHITE, Color.BLACK);
            isDarkTheme = false;
        }

        darkThemeJRadioButtonMenuItem.addItemListener(event -> {
            int state = event.getStateChange();
            if ( state == ItemEvent.SELECTED && !isDarkTheme ) {
                isDarkTheme = true;
                paintAllComponents(Color.BLACK, Color.LIGHT_GRAY);
            } else if ( state == ItemEvent.DESELECTED && isDarkTheme ) {
                paintAllComponents(Color.WHITE, Color.BLACK);
                isDarkTheme = false;
            }
        });

        lightThemeJRadioButtonMenuItem.addItemListener(event -> {
            int state = event.getStateChange();
            if ( state == ItemEvent.SELECTED && isDarkTheme ) {
                paintAllComponents(Color.WHITE, Color.BLACK);
                isDarkTheme = false;
            } else if ( state == ItemEvent.DESELECTED && !isDarkTheme ) {
                paintAllComponents(Color.BLACK, Color.LIGHT_GRAY);
                isDarkTheme = true;
            }
        });
    }

    public void paintAllComponents(Color backgroundColor, Color foregroundColor) {
        var setTheme = new SetTheme(backgroundColor, foregroundColor);
        setTheme.setJMenuBar(menuJMenuBar);
        setTheme.setJMenu(optionsJMenu);
        setTheme.setJMenu(configurationJMenu);
        setTheme.setJMenuItem(themeJMenuItem);
        setTheme.setJRadioButtonMeuItem(lightThemeJRadioButtonMenuItem);
        setTheme.setJRadioButtonMeuItem(darkThemeJRadioButtonMenuItem);
    }

    public static boolean getIsDarkTheme() {
        return isDarkTheme;
    }
}
