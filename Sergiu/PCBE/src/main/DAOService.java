package main;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;


public class DAOService {
    private static final List<Tranzactie> tranzactii = new CopyOnWriteArrayList<Tranzactie>();
    private static final List<Client> vanzatori = new CopyOnWriteArrayList<>();
    private static final List<Client> cumparatori = new CopyOnWriteArrayList<>();

    public static void adaugaTranzactielaListaConfirmata(Tranzactie tranzactie){
        tranzactii.add(tranzactie);
    }
    public static void adaugaVanzator(Vanzator vanzator){
        adaugaClient(vanzator,vanzatori);
    }
    public static void adaugaCumparator(Cumparator cumparator){
        adaugaClient(cumparator,cumparatori);
    }
    public static void updateVanzator(Vanzator vanzator){
        updateClient(vanzator, vanzatori);
    }

    public static void updateCumparator(Cumparator cumparator){
        updateClient(cumparator,cumparatori);
    }

    private static void adaugaClient(Client client, List<Client> list){
        list.add(client);
    }

    private static void updateClient(Client client,List<Client> list){
        Optional<Client> gasestePrimul = list.stream().filter(el -> el.sameId(client)).findFirst();
        if(gasestePrimul.isPresent()){
            Client toUpdate = gasestePrimul.get();
            list.set(list.indexOf(toUpdate), client);
        }
    }

    public static List<Oferta> getOferteVanzatori(){
        return getOferte(vanzatori);
    }

    public static List<Oferta> getOferteCumparatori(){
        return getOferte(cumparatori);
    }

    private static List<Oferta> getOferte(List<Client> list) {
        List<Oferta> collect= list.stream().map(el -> el.getOferta()).collect(Collectors.toList());
        return eliminareStocuriGoale(collect);
    }

    public static List<Client> getCumparatori(){
        return cumparatori;
    }

    public static List<Client> getVanzatori(){
        return  vanzatori;
    }

    private static List<Oferta> eliminareStocuriGoale(List<Oferta> list){
        List<Oferta> listaNulla = list.stream().filter(el -> el.stocEpuizat()).collect(Collectors.toList());
        list.removeAll(listaNulla);
        return list;
    }

    public static List<Tranzactie> getTranzactii(){
        return tranzactii;

    }




}
