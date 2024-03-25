import javax.swing.*;

public class UserInterface {
    User user;
    public UserInterface(User user){
        this.user = user;
        DisplayUserInterface mainFrame = new DisplayUserInterface(user);
        mainFrame.setTitle("Westminster Shopping Centre");
        mainFrame.setSize(800,600);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
