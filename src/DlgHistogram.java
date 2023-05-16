import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DlgHistogram extends JDialog {

    private ArrayList<Magazzino> relayMagazzino = null;
    private JCartesian graph = null;
    private int height = 0;
    private int max = 0;

    public DlgHistogram(ArrayList<Magazzino> list) {

        this.relayMagazzino = list;

        setSize(500, 500);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Istogramma magazzino");
        setLocationRelativeTo(null);

        initUI();
        populate();

        setVisible(true);
    }

    private void initUI() {

        findMax();
        this.graph = new JCartesian(450, 450,
                -(this.max * 0.20), -1,
                this.max * 1.10, this.relayMagazzino.size() + 1);
        JScrollPane pane = new JScrollPane(this.graph);
        this.add(pane, BorderLayout.CENTER);
    }

    private void populate() {

        Color[] colors = {Color.RED, Color.BLUE, Color.yellow, Color.GREEN, Color.ORANGE};

        double i = 0;
        for(Magazzino m: this.relayMagazzino) {

            this.graph.fillRect(0.0, i, m.getQta(), i + 0.8, colors[(int) i % 5]);
            this.graph.drawRect(0.0, i, m.getQta(), i + 0.8, Color.BLACK);
            this.graph.drawString(- (this.max * 0.18), i + 0.35, m.getDescrizione(), Color.BLACK);
            i++;
        }

        this.graph.drawLine(0.0, -1, 0, this.relayMagazzino.size() + 1, Color.BLACK);
    }

    public void findMax() {

        for(Magazzino m: this.relayMagazzino)
            if(m.getQta() > this.max)
                this.max = m.getQta();
    }
}
