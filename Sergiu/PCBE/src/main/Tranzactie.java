package main;

public class Tranzactie {
    private Cumparator cumparator;
    private Vanzator vanzator;
    private Oferta oferta;

    public Tranzactie(Cumparator cumparator, Vanzator vanzator, Oferta oferta) {
        this.cumparator = cumparator;
        this.vanzator = vanzator;
        this.oferta = oferta;
    }

    public Cumparator getCumparator() {
        return cumparator;
    }

    public Vanzator getVanzator() {
        return vanzator;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public String toString() {
        return "Tranzactia [cumparator=" + cumparator.getNumeClient() + ", vanzator=" + vanzator.getNumeClient() + ", oferta=" + oferta + "]";
    }
}
