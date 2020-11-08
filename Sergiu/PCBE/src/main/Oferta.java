package main;

public class Oferta {
    private String actiuneCompanie;
    private int numarActiune;
    private double pretActiune;
    private String id;
    private final IDGenerator idGen = new IDGenerator();

    public Oferta(String actiuneCompanie, int numarActiune, double pretActiune){
        this.actiuneCompanie = actiuneCompanie;
        this.numarActiune = numarActiune;
        this.pretActiune = pretActiune;
        this.id = idGen.getNewId();
    }

    public boolean canTrade(Oferta t){
        return this.getActiuneCompanie().equals(t.getActiuneCompanie()) && this.getPretActiune() == t.pretActiune;

    }

    public Oferta minNumarActiune(Oferta t){
        return new Oferta(t.getActiuneCompanie(), Math.min(this.numarActiune, t.numarActiune), t.pretActiune);
    }


    public boolean sameId(Oferta t) {
        return this.getId().equals(t.getId());

    }

    public boolean stocEpuizat() {
        return this.getNumarActiune() == 0;

    }

    public String getId(){
        return id;
    }

    public String getActiuneCompanie(){
        return actiuneCompanie;

    }

    public int getNumarActiune(){
        return numarActiune;
    }

    public double getPretActiune(){
        return pretActiune;
    }

        public  String toString() {
            return "Oferta [actiuneCompanie=" + actiuneCompanie + ", numarActiune=" + numarActiune + ", pretActiune=" + pretActiune + "]";

        }
}
