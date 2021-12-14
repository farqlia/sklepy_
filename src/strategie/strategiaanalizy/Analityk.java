package strategie.strategiaanalizy;

import serializacja.Transakcja;

import java.io.Serializable;
import java.util.List;

// To jest oddzielna strategia, więc teraz mielibyśmy dwie
public interface Analityk<E> extends Serializable {

    // Pomysł jest taki: jakaś mądra strategia promocji
    // może wykorzystać historię transkacji, wykonać obliczenia i ustalić, jaka powinna
    // być promocja. Np. dla strategii na dzień tygodnia, analiza polegałaby
    // na znalezieniu dnia/dni, gdzie najczęściej dokonuje się
    // sprzedaży, i ustaleniu tych dni jako promocyjne. W ten
    // sposób wykorzystujemy serializację obiektów w jakiś sensowny sposób

    E analizujDane();

}
