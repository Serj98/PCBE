package main;
import java.util.concurrent.LinkedBlockingQueue;

public class Vanzator  extends Client{
    public Vanzator(LinkedBlockingQueue<Oferta> coada, String nume, Oferta ofertaVanzare){

        super.nume = nume;
        super.oferta=ofertaVanzare;
        super.coada=coada;
        super.id=idGen.getNewId();
    }

    public void run(){
        try {
            while (!Thread.currentThread().isInterrupted()) {
               System.out.println("Vanzator: " + Thread.currentThread().getId() + " a intrat");
                DAOService.adaugaVanzator(this);
                posteazaOfertaVanzator();
            }
        }catch (Exception e){
            Thread.currentThread().interrupt();
            System.out.println("Seller: " + Thread.currentThread().getId() + " a iesit");

        }
    }

    protected void trade(Oferta t) throws InterruptedException {
        super.trade(t);
        if(this.oferta.getNumarActiune() !=0 ){
            posteazaOfertaVanzator();
        }
    }

    protected void posteazaOfertaVanzator() throws InterruptedException {
        System.out.println("Vanzator " + getNumeClient() + " pe thread-ul " + Thread.currentThread().getId() + " a postat: " + getOferta().toString());
        Oferta ofertaVanzator = oferta;
        if(coada.offer(ofertaVanzator)){
            coada.notifyAll();
        }
        wait();
    }


}
