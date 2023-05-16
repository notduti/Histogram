import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DlgMagazzino extends JDialog implements ActionListener {

    private Magazzino relayMagazzino = null;
    private JTextField txtCodice = null;
    private JTextField txtDescrizione = null;
    private JTextField txtQta = null;
    private JButton btnUpdate = null;

    public DlgMagazzino(Magazzino m) {

        this.relayMagazzino = m;

        setSize(200, 150);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Dettaglio magazzino");
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

        JPanel pnlCenter = new JPanel(new GridLayout(3,1));

        JPanel pnlCodice = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblCodice = new JLabel("Code:");
        this.txtCodice = new JTextField(10);
        pnlCodice.add(lblCodice);
        pnlCodice.add(this.txtCodice);
        pnlCenter.add(pnlCodice);

        JPanel pnlDescrizione = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblDescrizione = new JLabel("Descrizione:");
        this.txtDescrizione = new JTextField(10);
        pnlDescrizione.add(lblDescrizione);
        pnlDescrizione.add(this.txtDescrizione);
        pnlCenter.add(pnlDescrizione);

        JPanel pnlQta = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblQta = new JLabel("Quantity:");
        this.txtQta = new JTextField(10);
        pnlQta.add(lblQta);
        pnlQta.add(this.txtQta);
        pnlCenter.add(pnlQta);

        this.add(pnlCenter, BorderLayout.CENTER);
    }

    private void pnlSouth() {

        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.btnUpdate = new JButton("Update");
        pnlSouth.add(this.btnUpdate);
        this.add(pnlSouth, BorderLayout.SOUTH);
        this.btnUpdate.addActionListener(this);
    }

    private void populate() {

        this.txtCodice.setText(this.relayMagazzino.getCodice() + "");
        this.txtDescrizione.setText(this.relayMagazzino.getDescrizione());
        this.txtQta.setText(this.relayMagazzino.getQta() + "");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == this.btnUpdate) {

            this.relayMagazzino.setCodice(Integer.parseInt(this.txtCodice.getText()));
            this.relayMagazzino.setDescrizione(this.txtDescrizione.getText());
            this.relayMagazzino.setQta(Integer.parseInt(this.txtQta.getText()));
        }
    }
}
