package strategie.strategia2za1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import sklepy.Produkt;
import sklepy.Sklep;
import strategie.strategiaanalizy.Analityk;
import strategie.strategiapromocji.StrategiaPromocji;

public class Rabat2za1Analiza implements StrategiaPromocji, Analityk<List<Produkt>> {

	private Sklep sklep;
	private int i;
	
	//Podszedlem do tego problemu w taki sposob, aby analizowac ilosc produktow znajdujacych sie na magazynie
	//i dodawac do gazetki produkty ktore "zalegaja"(ktorych jest najwieszka ilosc). Wtedy te produkty podlegaja naszej przecenie 2 w cenie 1
	public Rabat2za1Analiza(Sklep sklep, int i) {
		this.sklep = sklep;
		this.i = i;
	}

	@Override
	public List<Produkt> analizujDane() {
		Map<Produkt, Integer> m = sklep.getStanMagazynu();
		
		//Tworzymy nowa mape produktow zawierajaca "i" produktow o najwiekszej liczebnosci na magazynie
		Map<Produkt, Integer> produktyPodlegajacePrzecenie = 
				m.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.limit(i)
				.collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		

		return new ArrayList<>(produktyPodlegajacePrzecenie.keySet());
	}

	@Override
	public double naliczRabat(Produkt produkt, int ilosc) {
		if(((analizujDane()).contains(produkt))) {
			if(ilosc % 2 == 0) return produkt.getCena() * ilosc * 0.5;
			return (produkt.getCena() * (ilosc-1) * 0.5) + produkt.getCena(); 
		}
		return produkt.getCena() * ilosc;
	}

}
