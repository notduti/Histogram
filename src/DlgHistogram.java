import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DlgHistogram extends JDialog implements ActionListener {

    private ArrayList<Magazzino> relayMagazzino = null;

    public DlgHistogram(ArrayList<Magazzino> list) {

        this.relayMagazzino = list;

        setSize(200, 150);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Istogramma magazzino");
        setLocationRelativeTo(null);

        initUI();
        populate();

        setVisible(true);
    }

    private void initUI() {

    }

    private void populate() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
