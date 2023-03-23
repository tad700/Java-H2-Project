import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedPane extends JFrame{

    JTabbedPane tab=new JTabbedPane();

    JPanel personP=new JPanel();
    JPanel carP=new JPanel();
    JPanel rentaP=new JPanel();
    JPanel sprP=new JPanel();

    public TabbedPane() {
        this.setSize(400, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        tab.add(personP, "Хора");
        tab.add(carP,"Коли");
        tab.add(rentaP,"Наем");
        tab.add(sprP,"������� �� ��� � ����");

        this.add(tab);

        this.setVisible(true);
    }

}
