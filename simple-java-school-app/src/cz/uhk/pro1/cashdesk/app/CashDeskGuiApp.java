package cz.uhk.pro1.cashdesk.app;

import javax.swing.*;

public class CashDeskGuiApp {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() { // anonymní vnitřní třída
            @Override
            public void run() {
                new CashDeskFrame().setVisible(true);
            }
        });
//        SwingUtilities.invokeLater(() -> new CashDeskFrame().setVisible(true)); // nebo lambda výraz

    }

}
