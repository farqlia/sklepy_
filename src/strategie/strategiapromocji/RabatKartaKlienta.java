package strategie.strategiapromocji;

import sklepy.Produkt;

public class RabatKartaKlienta implements StrategiaPromocji {

    // Próba rozwiązania problemu: nie możemy pominąć normalnej promocji,
    // której podlega każdy klient
    private StrategiaPromocji oryginalnaStrategiaPromocji;
    private double rabat;

    public RabatKartaKlienta(double rabat, StrategiaPromocji oryginalnaStrategiaPromocji){
        this.rabat = rabat;
        this.oryginalnaStrategiaPromocji = oryginalnaStrategiaPromocji;
    }

    @Override
    public double naliczRabat(Produkt produkt, int ilosc) {
        System.out.println("Sprzedaz z karta klienta");
        return (1 - rabat) * oryginalnaStrategiaPromocji.naliczRabat(produkt, ilosc);
    }
}

