package com.app.GUI;

import java.awt.*;

public class RegisterPanel {

    public Frame frame;
    public RegisterPanel() {
        CreateJFrame createJFrame = new CreateJFrame("Register Panel", 500, 500);
        frame = createJFrame.createJFrame();
    }

    public static void main(String[] args) {
        new RegisterPanel();
    }
}
