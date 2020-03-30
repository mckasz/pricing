Create "3 for 2" promotion. Every third product should be for free. 
Promotion should take Product with quantity as input and return list of com.mkaszynski.tdd.pricing.Products,
where part will be counted as full price and part as free. For example:

- 1x Milk 2pln -> 1x Milk 2pln 
- 2x Milk 2pln -> 2x Milk 2pln
- 3x Milk 2pln -> 2x Milk 2pln, 1x Milk 0pln
- 8x Milk 2pln -> 6x Milk 2pln, 2x Milk 0pln

### 1: Live template do asercji 
- Live template do asercji:
    - `⌘, / Ctrl+Alt+S` - Przejdź do ustawień
    - Wpisz Live Templates
    - `⌘N / Shift+Insert` - dodaj grupę np 'tests'
    - `⌘N / Shift+Insert` - dodaj template 'ast' z opisem np: 'Creates assertThat'
    - Wpisz kod template'u: `org.assertj.core.api.Assertions.assertThat($END$)`
    - Zdefiniuj kontekst przez kliknięcie na niebieski napis `Define` i wybranie `Java -> statement`
    - Zaznacz `Use static import if possible`
    - Upewnij się że pole `Shorten FQ names` jest zaznaczone 
    - `Ctrl+Enter` - Zatwierdź zmiany
    
### 2. Utwórz test dla klasy `Promotion3for2`
- Utwórz klasę `Promotion3for2`:  
    - `⌘1 / Alt+1` - przejście do zakładki Projektu
    - `Ctrl+Enter / Alt+Insert` - New... Tworzenie nowej klasy  
- Utwórz test do klasy `Promotion3for2`:
    - `⌘+Shift+T / Shift+Ctrl+T` - Tworzenie testu dla klasy
    - `Ctrl+Enter` - Potwierdza okno dialogowe 

### 3. Utwórz pierwszy test
#### Pisanie testu (RED)
Bądąc w klasie `Promotion3for2Test`
- `Ctrl+Enter / Alt+Insert - Generate -> Test Method` - Tworzenie testu
- Wpisz nazwę testu np: `shouldReturnProductWithSamePrice_whenOnlyOneProductGiven` - czyli chcemy po prostu zwrócić ten sam 
    produkt, który podaliśmy w argumencie metody `apply()`. Implementacja będzie banalna i taka ma być. Ten test ma pozwala 
    na zastnanowienie się jak powinno wyglądać api i struktura testów   
- Napisz kod testu
    - Napisz inicjalizacje klasy `Promotion3for2`, czyli po prostu:
    ```
    Promotion3for2 promotion = new Promotion3for2();
    ``` 
    Jest to punkt startowy. Pisząc pierwszy test możemy bez zastanowienia zacząć od takiej linijki. 
    - Wywołanie metody apply():
        - Można napisać: 
      ```
      List<Product> result = promotion.apply(new Product("ProductName", 220, 1
      ``` 
      IntelliJ dopisuje za nas zamykające nawiasy
        - `⌘+Shift+Enter / Shift + Ctrl + Enter` - Przeskoczenie od razu na koniec linii i dopisanie średnika  
    - Metoda apply() nie jest zaimplementowana, przez co mamy błąd kompilacji
        - `F2` - ustawia kursor w miejscu błędu 
        - `Alt+Enter` - Show context actions: pojawia się propozycja wygenerowania metody apply w klasie Promotion3for2
        - `Enter` - zatwierdzenie stworznia nowej metody
        - `Enter` - zatwierdzenie typu wyjściowego
        - Dobrze jest zmienić nazwę parametru na `product`
    - Assercja:
        - Nawigacja między plikami - do testu
            - `⌘E / Ctrl+E` - Ostatnio używane pliki
            - `Enter` - Zaznaczony jest plik testu więc od razu możemy go wybrać
        - Kod assercji 
            - `ast + Enter` - generowanie `assertThat` przy pomocy template'u stworzonego w punkcie [1: Live template do asercji](#1-live-template-do-asercji)
            - dopisujemy result i wybieramy warunek `.containsOnly(new Product("ProductName", 220, 1`  
            - `⌘+Shift+Enter / Shift + Ctrl + Enter` - Przeskoczenie od razu na koniec linii i dopisanie średnika  
    - Uruchomienie testu
        - `Ctrl+Shift+R / Shift+Ctrl+F10` - Kontekstowe uruchomienie testu, czyli uruchamiamy fragment w którym znajduje się kursor 
        - Warto przejść poza metodę i uruchomić wszystkie testy w tej klasie. Pozwoli to potem ponowić ostatnie uruchomienie dla
         wszystkich testów, czyli skrót: `Ctrl+R / Shift+F10`
        - Test nie przechodzi
#### Implementacja (GREEN)
- `⌘E / Ctrl+E` i `Enter` Nawigacja między plikami - do klasy produkcyjnej, to już było :) 
- Implementacja
    - Wystaczy by klasa produkcyjna zwróciłe listę z testowanym produktem, więc zamiast `return null` można dopisać:
    `return new ArrayList();`
- Można w tym momencie uruchomić test ponownie, by zweryfikować, że zmienił się komunikat błędu: `Ctrl+R / Shift+F10`
    Warto zauważyć że komunikat ten jest dość mało czytelny (brakuje metody toString), ale po kolei najpierw test musi przechodzić.
- `Alt⌘V / Ctrl+Alt+V` wyciągamy `ArrayList<>()` do zmiennej.
    - Warto zwrócić uwage na deklarowany typ zmiennej: czy jest to `ArrayList<Product>` czy też `List<Product>`
        IntelliJ będzie podstawi ostatnio użyty typ, jednak można to zmienić:
        `Shift+Tab` - przejście do deklaracji typu, gdzie można wybrać `List<Product>` 
    - można zmienić nazwę listy na `result`
- `⌘+Shift+Enter / Shift+Ctrl+Enter` - w linii z deklarowaną listą doda nową linię 
- dopisujemy `result.add(product);`   
- Uruchomienie testu:
    - `Ctrl+R / Shift+F10`
- Test dalej nie przechodzi, komunikat błędu jest dość zagatkowy. 
Zachęcam do tego by zastanowić się co jest nie tak... 

- Chodzi o brak metod `equals` i `hashCode` w klasie Product. Po prostu porównujemy ze sobą dwa obiekty a nie ich wartości. 
Można oczywiście dopisać te metody, chociaż bardziej polecam wygenerowanie ich, zaś najlepszą opcją jest użycie biblioteki 
Lombok i jej adnoacji `@EqualsAndHashCode`
- Jest kilka opcji by przejść do klasy Product:
    - `⌘B/Ctrl+B` na nazwie klasy w klasie Promotion3for2
    - `⌘1/Alt+1` otwiera zakładkę projektu, gdzie można znaleźć klasę Product którą można otworzyć przy pomocy ⌘↓/F4 
    - `⌘O/Ctrl+N` otwiera wyszukiwarkę klas, gdzie można wpisać Product (w tym projekcie jest ich kilka, więc należy
     wybrać tę z projektu `2-startup`) 
- Należy teraz dopisać adnotację `@EqualsAndHashCode` na klasie product lecz zanim to zrobimy dopisałbym adnotację `@ToString`,
która pozwoli na czytelniejsze komunikaty w testach. Można to sprawdzić uruchamiając test - Ctrl+R / Shift+F10 
- Dopisujemy adnotację @EqualsAndHashCode i uruchamiamy test ponownie. Test powinien przejść.
* Do zastanowienia: dlaczego w tym wypadku dobrze jest porównać obiekty przez wartości? Kiedy takie porównanie może 
się nie sprawdzać?
            
#### Refactoring (BLUE)
W tym momencie pierwszy test przechodzi. Następuje faza refactoringu. Przejrzyjmy wszystkie nasze klasy.
- Przegląd klas
    - Klasa `Product` ledwo się zmieniła - moim zdaniem nie ma w niej nic do poprawienia
    - Klasa `Promotion3for2` - moim zdaniem nie ma w niej nic do poprawienia
    - Klasa `Promotion3for2Test` - W tym 3 linijkowym teście można zauważyć, że powtarzamy tworzenie nowego obiektu Product. Warto 
    wydzielić taki powtarzający się kod do metody:
        - Mając kursor gdzieś na wywołaniu konstruktora
        - `Alt↑ / Ctrl+W` rozszerza zaznaczenie 
        - `Alt⌘V / Ctrl+Alt+V` wydzielenie metody
            - metoda może mieć nazwę `product`
            - po zatwierdzeniu IntelliJ znajduje duplikat tworzenia produktu w tej klasie który również wydzielamy enterem 
            (bo dokładnie o to nam chodziło)  
     
### 4: Drugi test
#### Pisanie testu (RED)
- `⌘E / Ctrl+E i Enter` - Nawigacja między plikami - do klasy testu, ostatni raz o tym skrócie :) 
- Kopiowanie pierwszego testu
    - `Alt↑ / Ctrl+W` rozszerzenie zaznaczenia, tak by zaznaczyć cały test
    - `⌘D / Ctrl+D` duplikowanie zaznaczenia
- Zmiana nazwy na np: `shouldReturnTwoProductsWithSamePrice_whenTwoProductsGiven` - czyli zmienimy wartość `quantity` na 2 
    i spodziewamy się takiego samego rezultatu.
- Ekstrakcja parametru: W teście istnieje już metoda tworząca produkt, jednak należy wyciągnąć `quantity` do parametru
    - Zaznaczamy wartość `1` czyli parametr `quantity` co możemy sprawdzić skrótem `⌘P/Ctrl+P`
    - ⌘Alt+P/Ctrl+Alt+P ekstrakcja parametru  
    - Teraz wystarczy w drugim teście zmienić wartość quantity na 2 w obu przypadkach tworzenia produktu.
- Ctrl+R / Shift+F10 Uruchomienie testu
    
#### Implementacja
Test przechodzi, mimo że nie dodaliśmy żadnego kodu produkcyjnego. Jednak w tym wypadku to jest ok. Tak wygląda nasza 
promocja. Być może jest to sygnał, że któryś z tych testów jest nie potrzebny i należy go usunąć, jednak na ten moment 
pozostawmy oba testy.
 
#### Refactoring (GREEN/BLUE)
Nic nie zmieniliśmy w kodzie produkcyjnym. Jedynie w testach widać, że mamy powieloną metodę inicializującą klasę 
Promotion3for2. Łatwo się tego pozbyć umieszczając kursor na zmiennej `promotion` i przy pomocy skrótu 
`⌘Alt+F/Ctrl+Alt+F` wyekstraktować ją do pola. Warto zauważyć, że w oknie dialogowym mamy możliwość zmiany miejsca 
inicializacji zmiennej. I można to oczywiście zrobić bez używania myszki! Pomoże w tym skrót `Crtl+Alt+I/(jeszcze nie
 wiem jaki to będzie skrót na windowsie)`
- Można usunąć inicjalizację w drugim teście, tak że oba testy będą korzystały z pola
- * W tym wypadku może wyglądać na to, że współdzielimy kod w obu testach. Czy tak rzeczywiście jest? 

### 5: Trzeci test
#### Pisanie testu (RED)
- Kopiujemy drugi test i zmieniamy nazwę na np: `shouldReturn2ProductWithSamePriceAnd1ForFree_whenThreeProductsGiven`
- W wejściowym produkcie zmieniamy ilość na 3
- W assercji trzeba teraz dodać drugą metodę, która stworzy darmowy product
- Uruchamiamy test - nie przechodzi

#### Implementacja (GREEN)
- Jak najłatwiej sprawić by ten test przeszedł? 
- Wytarczy dodać warunek: jeżeli liczba produktów jest równa 3 to powinniśmy zwrócić 2 produkty w tej samej cenie i jeden darmowy.
W przeciwnym wypadku zostawiamy produkt wejściowy.
- Przydatne skróty:
    - `⌘D/Ctrl+D` - kopiowanie linii
    - `⌘Shift↑/Ctrl+Shift+↑` (lub ↓) przesuwanie linii kodu
- Spodziewany rezultat:
```java
class Promotion3for2 {
    List<Product> apply(Product product) {
        List<Product> result = new ArrayList<>();
        if (product.quantity() == 3) {
            result.add(new Product(product.name(), product.price(), 2));
            result.add(new Product(product.name(), 0, 1));
        } else {
            result.add(product);
        }
        return result;
    }
}
```
- Uruchamiamy test - powinien przejść


#### Refactoring (BLUE)
Ok, kod nie jest zbyt ogólny i piękny ale mamy przechodzące testy. Można teraz na spokojnie zastanowić się, jak uczynić go
bardziej czytelnym. Zostawmy warunek na liczbę produktów równą 3. Zajmijmy się za to tworzeniem nowych wersji produktu. 
Są tam jakieś parametry jednak nie jest to bardzo czytelne. Wydzielamy więc metody:
    - Mając kursor na tworzeniu nowego produktu z zmiejszoną ilością 
    - `Alt↑ / Ctrl+W` rozszerza zaznaczenie 
    - `Alt⌘M / Ctrl+Alt+M` wydzielenie metody
    - Można podać nazwę `fullPrice`
    - W kolejnym oknie zaznaczamy `Keep Original Signature` (ta sugestia prawie nigdy nie ma sensu :/ )
- Podobnie z wywołaniem konstruktora dla darmowego produktu, gdzie możemy stworzyć metodę `free`
- Z obu metod - nieco tylko wybiegając w przyszłość - można wyciągnąć parametr `quantity` (`Alt⌘P / Ctrl+Alt+P`)
W rezultacie klasa Promotion3for2 wygląda tak:
```java
class Promotion3for2 {
    List<Product> apply(Product product) {
        List<Product> result = new ArrayList<>();
        if (product.quantity() == 3) {
            result.add(fullPrice(product, 2));
            result.add(free(product, 1));
        } else {
            result.add(product);
        }
        return result;
    }

    private Product fullPrice(Product product, int quantity) {
        return new Product(product.name(), product.price(), quantity);
    }

    private Product free(Product product, int quantity) {
        return new Product(product.name(), 0, quantity);
    }
}
```  
Warto zwrócić uwagę na sygnaturę metod `fullPrice` i `free`. Obie pracują wyłącznie na argumentach, nie na polach tej klasy. 
Dodatkowo przekazujemy obiekt i wartość jaką będziemy wykorzystywać do zmiany tego obiektu (w tym wypadku stworzenia kopii).
W tej sytuacji warto pójść krok dalej i przenieść te metody do klasy `Product`.
- umieszczamy kursor na nazwie `fullPrice` 
- `F6` - przesunięcie metody
- W oknie dialogowym IntelliJ podpowiada nam jedyny słuszny kierunek (na podstawie argumentów), który zatwierdzamy
- Po przeniesieniu możemy używać pól `name` i `price` bezpośrednio a nie przez metody, co można osiągnąć poprzez
- `Alt⌘N / Ctrl+Alt+N` inline 
  
Ostatecznie klasa Promotion3for2 wygląda tak:
```java
class Promotion3for2 {
    List<Product> apply(Product product) {
        List<Product> result = new ArrayList<>();
        if (product.quantity() == 3) {
            result.add(product.fullPrice(2));
            result.add(product.free(1));
        } else {
            result.add(product);
        }
        return result;
    }
}
```        
- `Ctrl+R / Shift+F10` Sprawdzamy czy testy dalej przechodzą
           
### 5: Czwarty test
#### Pisanie testu (RED)
Kopiujemy trzeci test i zmieniamy nazwę na np: `shouldReturn3ProductWithSamePriceAnd1ForFree_when4ProductsGiven`. Czyli dalej 
podążamy w tym samym kierunku. Na razie nie zastanawiamy się nad tym jaka matematyka stoi za liczbami w tej promocji. 
- Zmieniamy numerki quantity w teście
- `Ctrl+R / Shift+F10` Uruchamiamy nowy test  

#### Implementacja (GREEN)
Wystarczy dopisać if'a dla przypadku z 4 produktami

#### Refactoring (BLUE)
Nie ma nic do poprawy

           
### 5: Piąty test
Jak wyżej tylko dla 5 produktów lub możemy od razu przejść do kolejnego punktu.
           
### 5: Szósty test
#### Pisanie testu (RED)
Piszemy test dla 6 produktów. 

#### Implementacja (GREEN)
Dopisujemy if'a z 6 produktami -> 4 w tej samej cenie + 2 darmowe

#### Refactoring (BLUE)
Ok, mamy dość dobre rozpisanie jak powinien dziłać algorytm. Możemy też dla pewności dopisać if'y do przypadków dla `quantity` 
równego 1 i 2. Ciągle sprawczamy czy testy przechodzą... 
Pytanie ile jest darmowych produktów w zależności od ilości produktów wejściowych? 

... do zastanowienia 

Będzie to wynik dzielenia przez 3. Jeżeli tak to można `product.quantity()/3` wstawić wszędzie jako argument metody `free()`.
Można też od razu wydzielić tę operację do metody: `numberOfFreeProducts()` (`Alt⌘M / Ctrl+Alt+M`)

A ile powinno być produktów w tej samej cenie? 

...

Będzie to początkowa ilość produktów ale bez tych darmowych czyli `product.quantity - numberOfFreeProducts()`. Co można 
wydzielić do metody `numberOfSamePriceProducts`. 

Ok, to chyba możemy usunąć wszystkie if'y i otrzymamy następującą klasę:
```java
class Promotion3for2 {
    List<Product> apply(Product product) {
        List<Product> result = new ArrayList<>();
        result.add(product.fullPrice(numberOfFullPriceProducts(product.quantity())));
        result.add(product.free(numberOfFreeProducts(product.quantity())));
        return result;
    }

    private int numberOfFullPriceProducts(int quantity) {
        return quantity - numberOfFreeProducts(quantity);
    }

    private int numberOfFreeProducts(int quantity) {
        return quantity / 3;
    }
}
```  

Uruchamiamy testy i ... ? 

Dwa testy nie przechodzą...Ok. Co poszło nie tak? 

...

Czytając komunikat błędu widać, że chodzi o to że zawsze dodajemy darmowy produkt do listy. Nawet wtedy kiedy jego ilość to 0. 
Załużmy że nie jest to pożądana funkcjonalność. Nie chcemy mieć takich pustych produktów. Można to szybko naprawić dodając if'a,
sprawdzającego czy mamy jakieś darmowe produkty: 
```java
class Promotion3for2 {
    List<Product> apply(Product product) {
        List<Product> result = new ArrayList<>();
        result.add(product.fullPrice(numberOfFullPriceProducts(product.quantity())));
        if (numberOfFreeProducts(product.quantity()) > 0) {
            result.add(product.free(numberOfFreeProducts(product.quantity())));
        }
        return result;
    }
    // (...)
}
``` 

Tylko teraz nasz piękny algorytm już nie jest taki ładny. Możemy to poprawić zmieniając sposób działania listy. Tzn, chcięlibyśmy 
mieć listę która pomija produkty o `quantity` równe 0. No to napiszmy taką listę!

- Nowa klasa: `com.mkaszynski.tdd.pricing.Products`
- Klasa ta ma pole `private final List<Product> list = new ArrayList<>();`
- Możemy teraz zastąpić użycie zwykłej listy w klasie Promotion3for2 na `Products`:
```java
class Promotion3for2 {
    List<Product> apply(Product product) {
        Products products = new Products();
        products.add(product.fullPrice(numberOfFullPriceProducts(product.quantity())));
        if (numberOfFreeProducts(product.quantity()) > 0) {
            products.add(product.free(numberOfFreeProducts(product.quantity())));
        }
        return products.asList();
    }
    // (...)
}
```
Widać że brakuje nam metod add() i asList(). 
- F2 - przenosi do błędu
- Alt+Enter - wygenerowanie metody
I po chwili mamy nową klasę:
```java
class Products {
    private final List<Product> list = new ArrayList<>();

    void add(Product product) {
        list.add(product);
    }

    List<Product> asList() {
        return new ArrayList<>(list);
    }
}
```
I tutaj możemy przenieść tego if'a! Metoda `add(Product)` może sprawdzać quantity:
```java
class Products {
    private final List<Product> list = new ArrayList<>();

    void add(Product product) {
        if (product.quantity() != 0) {
            list.add(product);
        }
    }

    List<Product> asList() {
        return new ArrayList<>(list);
    }
}
```
A klasa Promotion3for2 może wyglądać tak:
```java
class Promotion3for2 {
    List<Product> apply(Product product) {
        Products products = new Products();
        products.add(product.fullPrice(numberOfFullPriceProducts(product.quantity())));
        products.add(product.free(numberOfFreeProducts(product.quantity())));
        return products.asList();
    }

    private int numberOfFullPriceProducts(int quantity) {
        return quantity - numberOfFreeProducts(quantity);
    }

    private int numberOfFreeProducts(int quantity) {
        return quantity / 3;
    }
}
```

Sprawdzamy testy. Wszystko powinno przechodzić. 
