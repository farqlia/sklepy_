package strategie.strategiapromocji;


import sklepy.Produkt;

public interface StrategiaPromocji {

    // Wywoływana w metodzie sprzedajProdukt (lub innej metodzie) klasy
    // Zwraca cenę za zakupy bo zastosowaniu własnej promocji
    double naliczRabat(Produkt produkt, int ilosc);

}

