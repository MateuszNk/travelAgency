package com.app.GUI.usersPanels;

import com.app.GUI.CreateJFrame;

import java.awt.*;

public class UserPanel {

    public Frame frame;
    public UserPanel() {
        CreateJFrame createJFrame = new CreateJFrame("User Panel", 500, 500);
        frame = createJFrame.createJFrame();
    }

    public static void main(String[] args) { new UserPanel(); }
}
