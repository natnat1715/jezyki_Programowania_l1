package lab1jezykiprogramowania


/**
 *@ author Natalia Kurczyna
 * skorzystano z chatGPT, który wyjaśnił, że indeksy w liście odpowiadają potęgom, wyjaśnił czym jest blok init (wywoływany zawsze po utworzeniu nowego obiektu)
 * oraz że sprawdzanie czy lista jest pusta należy umieszczać w bloku init oraz zasugerował żeby w bloku init było usunZeraNaKoncu
 */
class Wielomian (wspolczynniki:MutableList<Double>){
    var wspolczynniki = wspolczynniki

    init{
        usunZeraNaKoncu(wspolczynniki)
        if( wspolczynniki.isEmpty()){
            throw IllegalArgumentException("Lista nie moze byc pusta")
        }
    }

    /**
     * funkcja usuwa zera jeśli są one na końcu listy, ale tylko jeśli lista ma więcej niż jeden element
     * skorzystano z chatGPT, który podpowiedział, żeby użyć funkcji last() oraz poprawił błędnie zaimplementowaną funkcję
     * @param przyjmuje mutowalną listę współczynników Double, gdzie indeksy oznaczają kolejne potęgi
     * @return nie zwraca niczego
     * @throws nie wyrzuca wyjątków
     */
    fun usunZeraNaKoncu(wspolczynniki: MutableList<Double>){
        while (wspolczynniki.size >1 && wspolczynniki.last() ==0.0){
            wspolczynniki.removeAt(wspolczynniki.lastIndex)
        }
    }

    /**
     * funkcja zwraca stopień wielomianu, czyli największą potęgę z niezerowym współczynnikiem
     * @param
     * @return stopień wielomianu w Int
     * @throws nie wyrzuca wyjątków
     */
    fun stopien(): Int {
        return wspolczynniki.size - 1
    }

    /**
     * funkcja zwraca tekstową reprezentację wielomianu. Jeśli wszystkie współczynniki wielomianu są równe 0 to zwraca W(x) = 0.0, przed dodatniki
     * współczynnikami dodaje "+", pomija składniki gdzie współczynnik jest równy 0
     * skorzystano z chatGPT, który poprawił błędnie zaimplementowane fragmenty funkcji oraz doprecyzował jakie przypadki należy rozpatrzyć
     * podczas pisania funkcji
     * @param nie przyjmuje żadnych parametrów
     * @return reprezentację wielomianu w String
     * @throws nie wyrzuca wyjątków
     */
    override fun toString(): String{
        if (wspolczynniki.all { it == 0.0 }) return "W(x) = 0"
        var wynik= " W(x) = "
        for( i in wspolczynniki.indices.reversed()) {
            if (wspolczynniki[i] == 0.0) {
                continue
            }
            if (i < wspolczynniki.size - 1 && wspolczynniki[i] >0.0){
                wynik += "+"
            }
            if (i == 0) {
                if (wspolczynniki[i] > 0.0) {
                    wynik += " ${wspolczynniki[i]} "
                } else wynik += " ${wspolczynniki[i]} "
            } else if (i == 1) {
                if (wspolczynniki[i] == 1.0) {
                    wynik += " x "
                } else if (wspolczynniki[i] == -1.0) {
                    wynik += "- x "
                } else if (wspolczynniki[i] > 0.0) {
                    wynik += " ${wspolczynniki[i]}x "
                } else wynik += " ${wspolczynniki[i]}x "
            } else {
                if (wspolczynniki[i] == 1.0) {
                    wynik += " x^$i "
                } else if (wspolczynniki[i] == -1.0) {
                    wynik += "- x^$i "
                } else if (wspolczynniki[i] > 0.0) {
                    wynik += " ${wspolczynniki[i]}x^$i "
                } else wynik += " ${wspolczynniki[i]}x^$i "
            }
        }
        return wynik
    }

    /**
     * funkcja oblicza wartość wielomianu dla podanego x
     * skorzystano z chatGPT, który zasugerował stworzenie fun invoke i wyjaśnił co należy zrobić w poleceniu
     * @param x w Double
     * @return wynik w Double
     * @throws nie wyrzuca wyjątków
     */
    operator fun invoke (x:Double): Double{
        var wynik1 = 0.0
        for ( i in wspolczynniki.indices){
            wynik1 += wspolczynniki[i]* Math.pow(x, i.toDouble())
        }
        return wynik1
    }

    /**
     * funkcja dodaje dwa wielomiany, lecz jeśli jeden z wielomianów nie ma współczynnika z daną potęgą, wtedy traktuje (tą potęgę) jako 0.0
     * skorzystano z chatGPT, który zwrócił uwagę, że maksyamlna długość jest potrzebna, żeby pętla przebiegała tyle razy ile wynosi długość najdłuższej listy,
     * zasugerował getOrNull()
     * @param inny wielomian, który będzie dodwany do pierwszego wielomianu
     * @return wynik dodawania dwóch wielomianów, jako obiekt klasy wielomian
     * @throws nie wyrzuca wyjątków ani błędów
     */
    operator fun plus (other : Wielomian):Wielomian {
        val maxDlugosc = maxOf(this.wspolczynniki.size, other.wspolczynniki.size)
        var lista = MutableList(maxDlugosc){0.0}
        for (i in 0 until maxDlugosc){
            val a = this.wspolczynniki.getOrNull(i)?: 0.0
            val b = other.wspolczynniki.getOrNull(i)?: 0.0
            lista[i] = a+b
        }
        return Wielomian(lista)
    }

    /**
     * funkcja odejmuje od siebie dwa wielomiany
     * skorzystano z chatGPT, który poprawił nieprawidłową implementację fragmentów funkcji, funkcję tą wykonano analogicznie do poprzedniej
     * @param inny wielomian, który będzie odejmowany od pierwszego wielomianu
     * @return nowy obiekt klasy Wielomian, który jest wynikiem odejmowania dwóch wielomianów
     * @throws nie wyrzuca wyjątków ani błędów
     */
    operator fun minus (other : Wielomian):Wielomian {
        val maxDlugosc = maxOf(this.wspolczynniki.size, other.wspolczynniki.size)
        var lista = MutableList(maxDlugosc){0.0}
        for (i in 0 until maxDlugosc){
            val a = this.wspolczynniki.getOrNull(i)?: 0.0
            val b = other.wspolczynniki.getOrNull(i)?: 0.0
            lista[i] = a-b
        }
        return Wielomian(lista)
    }

    /**
     * funkcja wykonuje mnożenie dwóch wielomianów
     * skorzystano z chatGPT, który podpowiedział, że stopień wyniku mnożenia to suma wtopni obu wielomianów +1 oraz poprawił nieprawidłowo
     * zaimplementowaną pętlę
     * @param inny wielomian,który będzie mnożony przez pierwszy wielomian
     * @return obiekt klasy Wielomian, który będzie wynikiem mnożenia dwóch wielomianów
     * @throws nie wyrzuca błędów
     */
    operator fun times ( other: Wielomian):Wielomian{
        var lista = MutableList(this.stopien() + other.stopien() + 1){0.0}
        for (i in this.wspolczynniki.indices){
            for (j in other.wspolczynniki.indices){
                lista [i+j] += this.wspolczynniki[i] * other.wspolczynniki[j]
            }
        }
        return Wielomian(lista)
    }

    /**
     * funkcja używa operatora złożonego: do pierwszego wielomianu dodaje drugi, czyści listę współczynników i dodaje wynik do listy
     * skorzystano z chatGPT, który objaśnił co należy zrobić w tym poleceniu, zasugerował czyszczenie listy i dodanie do niej wyniku
     * @param inny wielomian, który będzie dodawany do pierwszego
     * @return nie zwraca żadnej wartości
     * @throws nie wyrzuca błędów
     */
    operator fun plusAssign(other: Wielomian) {
        val nowy = this + other
        this.wspolczynniki.clear()
        this.wspolczynniki.addAll(nowy.wspolczynniki)
    }
    /**
     * funkcja używa operatora złożonego: od pierwszego wielomianu odejmuje drugi, czyści listę współczynników i dodaje wynik do listy
     * skorzystano z chatGPT, który objaśnił co należy zrobić w tym poleceniu, zasugerował czyszczenie listy i dodanie do niej wyniku
     * @param inny wielomian, który będzie odejmowany od pierwszego
     * @return nie zwraca żadnej wartości
     * @throws nie wyrzuca błędów
     */
    operator fun minusAssign(other: Wielomian) {
        val nowy = this - other
        this.wspolczynniki.clear()
        this.wspolczynniki.addAll(nowy.wspolczynniki)
    }
    /**
     * funkcja używa operatora złożonego: mnoży wielomainy przez siebie, czyści listę współczynników i dodaje wynik do listy
     * skorzystano z chatGPT, który objaśnił co należy zrobić w tym poleceniu, zasugerował czyszczenie listy i dodanie do niej wyniku
     * @param inny wielomian, który będzie mnożony przez pierwszy wielomian
     * @return nie zwraca żadnej wartości
     * @throws nie wyrzuca błędów
     */
    operator fun timesAssign(other: Wielomian) {
        val nowy = this * other
        this.wspolczynniki.clear()
        this.wspolczynniki.addAll(nowy.wspolczynniki)
    }
}

fun main() {
    var wspolczynniki = mutableListOf<Double>(2.0, 0.0, 3.0, 5.0)
    val wielomian = Wielomian(wspolczynniki)
    var wspolczynniki2 = mutableListOf(3.0, -2.0, 8.0)
    val wielomian2 = Wielomian(wspolczynniki2)
    println("Stopien wielomianu: ${wielomian.stopien()}")
    if (wielomian.stopien() == 3) {
        println("Test przeszedl")
    } else println("Test nie przeszedl")
    println("Wynik: ${wielomian(2.0)}")
    if (wielomian(2.0) == 54.0) {
        println("Test przeszedl")
    } else println("Test nie przeszedl")
    println("Suma ${wielomian + wielomian2}")
    var dwaWielomiany = wielomian + wielomian2
    if (dwaWielomiany.toString().trim() == "W(x) =  5.0x^3 + 11.0x^2  -2.0x + 5.0") {
        println("Test przeszedl")
    } else println("Test nie przeszedl")
    println("Roznica ${wielomian - wielomian2}")
    dwaWielomiany = wielomian - wielomian2
    if (dwaWielomiany.toString().trim() == "W(x) =  5.0x^3  -5.0x^2 + 2.0x  -1.0") {
        println("Test przeszedl")
    } else println("Test nie przeszedl")
    println("Mnozenie ${wielomian * wielomian2}")
    dwaWielomiany = wielomian * wielomian2
    if (dwaWielomiany.toString().trim() == "W(x) =  40.0x^5 + 14.0x^4 + 9.0x^3 + 25.0x^2  -4.0x + 6.0"
    ) {
        println("Test przeszedl")
    } else println("Test nie przeszedl")
    println("Mnozenie ${wielomian * wielomian2}")
    wielomian += wielomian2
    println("Wielomian: $wielomian")
    if (wielomian.toString().trim() == "W(x) =  5.0x^3 + 11.0x^2  -2.0x + 5.0") {
        println("Test przeszedl")
    } else println("Test nie przeszedl")
    println(" ${wielomian}")
    wielomian -= wielomian2
    println("Wielomian2: $wielomian")
    if (wielomian.toString().trim() == "W(x) =  5.0x^3 + 3.0x^2 + 2.0") {
        println("Test przeszedl")
    } else println("Test nie przeszedl")
    wielomian *= wielomian2
    println("Wielomian3: $wielomian")
    if (wielomian.toString().trim() == "W(x) =  40.0x^5 + 14.0x^4 + 9.0x^3 + 25.0x^2  -4.0x + 6.0"
    ) {
        println("Test przeszedl")
    } else println("Test nie przeszedl")
    wspolczynniki = mutableListOf(1.0, 0.0, 0.0)
    val wielomian3 = Wielomian(wspolczynniki)
    wspolczynniki2 = mutableListOf(-1.0, 2.0, -3.0, 4.0)
    val wielomian4 = Wielomian(wspolczynniki2)
    println("Stopien wielomianu: ${wielomian3.stopien()}")
    if (wielomian3.stopien() == 0) {
        println("Test przeszedl")
    } else println("Test nie przeszedl")
    println("Wynik: ${wielomian3(2.0)}")
    if (wielomian3(2.0) == 1.0) {
        println("Test przeszedl")
    } else println("Test nie przeszedl")
    println("Suma ${wielomian3 + wielomian4}")
    var noweWielomiany = wielomian3 + wielomian4
    if (noweWielomiany.toString().trim() == "W(x) =  4.0x^3  -3.0x^2 + 2.0x") {
        println("Test przeszedl")
    } else println("Test nie przeszedl")
    println("Roznica ${wielomian3 - wielomian4}")
    noweWielomiany = wielomian3 - wielomian4
    if (noweWielomiany.toString().trim() == "W(x) =  -4.0x^3 + 3.0x^2  -2.0x + 2.0") {
        println("Test przeszedl")
    } else println("Test nie przeszedl")
    println("Mnozenie ${wielomian3 * wielomian4}")
    noweWielomiany = wielomian3 * wielomian4
    if (noweWielomiany.toString().trim() == "W(x) =  4.0x^3  -3.0x^2 + 2.0x  -1.0") {
        println("Test przeszedl")
    } else println("Test nie przeszedl")
    wielomian3 += wielomian4
    println("Wielomian: $wielomian3")
    if (wielomian3.toString().trim() == "W(x) =  4.0x^3  -3.0x^2 + 2.0x") {
        println("Test przeszedl")
    } else println("Test nie przeszedl")
    println(" ${wielomian3}")
    wielomian3 -= wielomian4
    println("Wielomian: $wielomian3")
    if (wielomian3.toString().trim() == "W(x) =  1.0") {
        println("Test przeszedl")
    } else println("Test nie przeszedl")
    wielomian3 *= wielomian4
    println("Wielomian: $wielomian3")
    if (wielomian3.toString().trim() == "W(x) =  4.0x^3  -3.0x^2 + 2.0x  -1.0") {
        println("Test przeszedl")
    } else println("Test nie przeszedl")
    try {
        wspolczynniki = mutableListOf()
        val wielomian5 = Wielomian(wspolczynniki)
        println("Test nie przeszedl, bo funkcja zwrocila ${wielomian5.stopien()}a powinna wyrzucic wyjatek")
    } catch (e: IllegalArgumentException) {
        println("Test przeszedl, bo funkcja wyrzucila wyjatek: ${e.message}")
    }
}