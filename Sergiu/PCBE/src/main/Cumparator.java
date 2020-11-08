package main;

import java.util.concurrent.LinkedBlockingQueue;

public class Cumparator extends Client {

    public Cumparator(LinkedBlockingQueue<Oferta> coada, String nume, Oferta cerereCumparare){
        super.nume=nume;
        super.oferta=cerereCumparare;
        super.coada=coada;
        super.id=idGen.getNewId();
    }

    public void run(){
        try{
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Cumparator: " + Thread.currentThread().getId() + " a intrat");
                DAOService.adaugaCumparator(this);
                proceseazaOferte();
            }
        }catch(Exception e){
            Thread.currentThread().interrupt();
            System.out.println("Cumparator: "+ Thread.currentThread().getId() + " a iesit" );
        }
    }

    private void trade(Vanzator vanzator, Cumparator cumparator) throws InterruptedException{
        Tranzactie tranzactie = creeazaTranaztie(vanzator,cumparator);
        DAOService.adaugaTranzactielaListaConfirmata(tranzactie);
        System.out.println("Tranzactia: " + tranzactie);
        Oferta cumparatorCatre = cumparator.getOferta();
        Oferta vanzatorCatre = vanzator.getOferta();

        cumparator.trade(vanzatorCatre);
        vanzator.trade(cumparatorCatre);

        DAOService.updateVanzator(vanzator);
        DAOService.updateCumparator(cumparator);
    }

    private void proceseazaOferte() throws InterruptedException{
        System.out.println("Cumparator " + getNumeClient() + " pe thread-ul " + Thread.currentThread().getId() + " a postat: " + oferta.toString());
        Oferta luat=coada.take();
        if(oferta.canTrade(luat)) {
            Vanzator vanzator = (Vanzator) DAOService.getVanzatori().stream().filter(el -> luat.sameId(el.getOferta())).findAny().orElse(null);
            trade(vanzator, this);
        }else{
            coada.offer(luat);
            coada.notifyAll();
        }while (!coada.peek().equals(oferta)){
            wait();
        }


        }

    }


