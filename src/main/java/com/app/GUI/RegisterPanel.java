package com.app.GUI;

import com.app.GUI.creators.CreateJFrame;

import java.awt.*;

public class RegisterPanel {

    public Frame frame;
    public RegisterPanel() {
        CreateJFrame createJFrame = new CreateJFrame();
        frame = createJFrame.createJFrame("Register Panel", 500, 500);
    }

    public static void main(String[] args) {
        new RegisterPanel();
    }
}
