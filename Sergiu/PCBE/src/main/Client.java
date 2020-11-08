package main;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Client implements Runnable {

    protected String nume;
    protected Oferta oferta;
    protected String id;
    protected final IDGenerator idGen = new IDGenerator();
    protected LinkedBlockingQueue<Oferta> coada = null;

    public void editareActiuneClient(Oferta oferta) throws InterruptedException{
        this.oferta = oferta;
    }

    public Tranzactie creeazaTranaztie(Vanzator vanzator, Cumparator cumparator){
        return new Tranzactie(cumparator, vanzator, vanzator.getOferta().minNumarActiune(cumparator.getOferta()));
    }

    protected void trade(Oferta t) throws InterruptedException{
        int a = this.oferta.getNumarActiune();
        int b= t.getNumarActiune();
        if( a > b) {
            editareActiuneClient(new Oferta(this.oferta.getActiuneCompanie(), a - b, oferta.getPretActiune()));
        }else{
            editareActiuneClient(new Oferta(this.oferta.getActiuneCompanie(),0,oferta.getPretActiune()));
        }
    }

    public Oferta getOferta(){
        return oferta;
    }

    public boolean sameId(Client c){
        return this.getId().equals(c.getId());
    }

    public  String getId(){
        return id;
    }

    public String getNumeClient(){
        return nume;
    }



    public String toString() {
        return "Client [nume=" + nume + ", oferta=" + oferta + ", id=" + id + "]";

    }



}
