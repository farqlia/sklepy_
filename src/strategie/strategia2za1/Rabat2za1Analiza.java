package strategie.strategia2za1;

import sklepy.Produkt;
import sklepy.Sklep;
import strategie.strategiaanalizy.Analityk;
import strategie.strategiapromocji.StrategiaPromocji;

import java.util.*;
import java.util.stream.Collectors;

public class Rabat2za1Analiza implements StrategiaPromocji, Analityk<List<Produkt>> {

    private static final long serialVersionUID = 3L;

    private final Sklep sklep;
    private final int jakaIloscProduktowMaBycRabatowana;

    //Rabatowanie, w tym przypadku, 
    //polega na tym, ze co drugi produkt
    //tego samego typu jest darmowy.
    public Rabat2za1Analiza(Sklep sklep, int jakaIloscProduktowMaBycRabatowana) {
        this.sklep = sklep;
        this.jakaIloscProduktowMaBycRabatowana = jakaIloscProduktowMaBycRabatowana;
    }

    @Override
    public List<Produkt> analizujDane() {

        //Pobieramy stan magazynu danego sklepu z klasy Sklep
        Map<Produkt, Integer> magazyn = sklep.getStanMagazynu();

        //Z magazynu danego sklepu wyszukujemy
        //produkty ktorych jest najwiecej oraz
        //dodajemy je do nowej mapy.
        //Ilosc produktow, ktore zostana dodane do nowej mapy
        //zalezy od zmiennej "jakaIloscProduktowMaBycRabatowana".
        Map<Produkt, Integer> produktyPodlegajacePrzecenie =
                magazyn.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(jakaIloscProduktowMaBycRabatowana)
                        .collect(Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


        return new ArrayList<>(produktyPodlegajacePrzecenie.keySet());
    }

    @Override
    //Rabat jest naliczany w zaleznosci od tego
    //czy ilosc produktu, ktory chcemy kupic jest parzysta
    //lub nie parzysta
    //W przypadku nieparzystosci naliczamy rabat 2 w cenie jednego
    //na cene parzystej ilosci produktow oraz dodajemy
    //jeden produkt po normalnej cenie.
    public double naliczRabat(Produkt produkt, int ilosc) {
        if (analizujDane().contains(produkt)) {
            if (ilosc % 2 == 0) return produkt.getCena() * ilosc * 0.5;
            return (produkt.getCena() * (ilosc - 1) * 0.5) + produkt.getCena();
        }
        return produkt.getCena() * ilosc;
    }

}
