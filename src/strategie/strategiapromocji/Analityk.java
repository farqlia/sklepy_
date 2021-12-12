package strategie.strategiapromocji;
import java.util.List;


public interface Analityk {

    // Pomysł jest taki: jakaś mądra strategia promocji
    // może wykorzystać historię transkacji, wykonać obliczenia i ustalić, jaka powinna
    // być promocja. Np. dla strategii na dzień tygodnia, analiza polegałaby
    // na znalezieniu dnia/dni, gdzie najczęściej dokonuje się
    // sprzedaży, i ustaleniu tych dni jako promocyjne. W ten
    // sposób wykorzystujemy serializację obiektów w jakiś sensowny sposób
    // Zwraca Object, czyli coś co jest wynikiem analizy
    // Trzeba pamiętać, że przy nadpisaniu metody sygnatura musi być ta sama, a typy
    // zwracane kompatybilne: więc w zasadzie można zwrócić cokolwiek, bo wszystko
    // jest obiektem
    Object analizujDane();

}
