package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;



public class Bursa {

    public static void main(String[] args) {
        LinkedBlockingQueue<Oferta> coada = new LinkedBlockingQueue<>();

        Client s1 = new Vanzator(coada, " Marius", new Oferta("Barsesti", 50, 4.6));
        Client s2 = new Vanzator(coada,"Nicusor", new Oferta("delaBraila", 300, 4.5));
        Client b1 = new Cumparator(coada, "Drondu", new Oferta("Blondu", 50, 4.6));
        Client b2 = new Cumparator(coada, "Ale", new Oferta("Cret&Flex", 175,4.5));
        Client b3 = new Cumparator(coada, "Serj", new Oferta("Bej",50, 4.5));

        try{
            ExecutorService threadPool = Executors.newFixedThreadPool(5);

            threadPool.execute(s1);
            threadPool.execute(s2);
            threadPool.execute(b1);
            threadPool.execute(b2);
            threadPool.execute(b3);
            s2.editareActiuneClient(new Oferta("delaBraila", 500, 4.5));
            b3.editareActiuneClient(new Oferta("Bej",70,4.5));

            executorServiceShutdown(threadPool);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Oferte vanzator: " + DAOService.getOferteVanzatori());
        System.out.println("Cereri cumparator: " + DAOService.getOferteCumparatori());
        System.out.println("Vanzatori: " + DAOService.getVanzatori());
        System.out.println("Cumparatori: " + DAOService.getCumparatori());
        System.out.println("Tranzactii: " + DAOService.getTranzactii());
    }
    private static void executorServiceShutdown(ExecutorService executorService) {
        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

    }
}



