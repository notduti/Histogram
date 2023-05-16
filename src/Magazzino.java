public class Magazzino {

    private int codice = 0;
    private String descrizione = null;
    private int qta = 0;

    public Magazzino(int codice, String descrizione, int qta) {
        this.codice = codice;
        this.descrizione = descrizione;
        this.qta = qta;
    }

    public Magazzino() {}

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getQta() {
        return qta;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }

    @Override
    public String toString() {
        return "Magazzino{" +
                "codice=" + codice +
                ", descrizione='" + descrizione + '\'' +
                ", qta=" + qta +
                '}';
    }

    public String[] toRow() {

        String[] ret = new String[3];
        ret[0] = this.codice + "";
        ret[1] = this.descrizione;
        ret[2] = this.qta + "";
        return ret;
    }

    public String toLine() {
        return this.codice + ";" +
                this.descrizione + ";" +
                this.qta + "\n";
    }
}