import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class FrmMagazzino extends JFrame implements ActionListener {

    private ArrayList<Magazzino> relayMagazzino = null;
    private JTable tblMagazzini = null;
    private JButton btnSave = null;
    private JButton btnDelete = null;
    private JButton btnNew = null;
    private JButton btnOpen = null;
    private JButton btnUpdate = null;
    private JButton btnHistogram = null;

    public FrmMagazzino(ArrayList<Magazzino> list) {

        this.relayMagazzino = list;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Magazzini");
        setSize(650, 400);
        setLocationRelativeTo(null);

        initUI();
        populate();

        setVisible(true);
    }

    private void initUI() {

        pnlCenter();
        pnlSouth();
    }

    private void pnlCenter() {

        this.tblMagazzini = new JTable();
        JScrollPane pane = new JScrollPane(this.tblMagazzini);
        this.add(pane, BorderLayout.CENTER);
    }

    private void pnlSouth() {

        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        this.btnNew = new JButton("New...");
        this.btnUpdate = new JButton("Update...");
        this.btnDelete = new JButton("Delete");
        this.btnSave = new JButton("Save...");
        this.btnOpen = new JButton("Open...");
        this.btnHistogram = new JButton("Histogram...");

        pnlSouth.add(this.btnNew);
        pnlSouth.add(this.btnSave);
        pnlSouth.add(this.btnUpdate);
        pnlSouth.add(this.btnOpen);
        pnlSouth.add(this.btnDelete);
        pnlSouth.add(this.btnHistogram);

        this.add(pnlSouth, BorderLayout.SOUTH);

        this.btnNew.addActionListener(this);
        this.btnUpdate.addActionListener(this);
        this.btnDelete.addActionListener(this);
        this.btnSave.addActionListener(this);
        this.btnOpen.addActionListener(this);
        this.btnHistogram.addActionListener(this);
    }

    private void populate() {

        DefaultTableModel model = new DefaultTableModel();
        String[] cols = {"Code", "Description", "Quantity"};
        for(String s: cols) model.addColumn(s);
        for(Magazzino m: this.relayMagazzino) model.addRow(m.toRow());
        this.tblMagazzini.setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == this.btnNew) {

            Magazzino m = new Magazzino();
            new DlgMagazzino(m);
            this.relayMagazzino.add(m);
            populate();
        }
        if(e.getSource() == this.btnSave) {

            JFileChooser fc = new JFileChooser();
            int rc = fc.showSaveDialog(this);
            if(rc != JFileChooser.APPROVE_OPTION) return;
            String fileName = fc.getSelectedFile().getAbsolutePath();
            try {
                PrintWriter pw = new PrintWriter(new FileWriter(fileName));
                String buffer = "";
                for(Magazzino m: this.relayMagazzino) buffer = buffer + m.toLine();
                pw.print(buffer);
                pw.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource() == this.btnOpen) {

            this.relayMagazzino = new ArrayList<>();
            JFileChooser fc = new JFileChooser();
            int rc = fc.showOpenDialog(this);
            if(rc != JFileChooser.APPROVE_OPTION) return;
            String fileName = fc.getSelectedFile().getAbsolutePath();

            try {
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                String line = "";
                while((line = br.readLine()) != null) {

                    String[] els = line.split(";");
                    this.relayMagazzino.add(new Magazzino(Integer.parseInt(els[0]), els[1], Integer.parseInt(els[2])));
                }
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            populate();
        }
        if(e.getSource() == this.btnUpdate) {

            if(this.tblMagazzini.getSelectedRow() != -1) {

                new DlgMagazzino(this.relayMagazzino.get(this.tblMagazzini.getSelectedRow()));
                populate();
            }
        }
        if(e.getSource() == this.btnDelete) {

            if(this.tblMagazzini.getSelectedRow() != -1) {

                this.relayMagazzino.remove(this.relayMagazzino.get(this.tblMagazzini.getSelectedRow()));
                populate();
            }
        }
        if(e.getSource() == this.btnHistogram) {

            new DlgHistogram(this.relayMagazzino);
        }
    }

    public static void main(String[] args) {
        ArrayList<Magazzino> mag = new ArrayList<>();
        mag.add(new Magazzino(1, "viti", 34));
        mag.add(new Magazzino(2, "chiodi", 42));
        SwingUtilities.invokeLater(() -> new FrmMagazzino(mag));
    }
}
