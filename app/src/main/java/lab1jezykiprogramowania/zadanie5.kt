package lab1jezykiprogramowania

/**
 *@ author Natalia Kurczyna
 * problem Collatza: https://pl.wikipedia.org/wiki/Problem_Collatza
 * użyto chatGPT do poprawy napisanego kodu i zasugerował on że najlepiej użyć wyrażenia while zamiast for, że podanaLiczba musi być != 1 bo potem
 * zgodnie z problemem Collatza wpadamy w ciąg 4,2,1,4,2,1 itd., zasugerował obsłużenie c==1 oraz sprawdzenie wszystkich podawanych wartości w main dzięki try and catch
 * oraz zasugerował dodanie do nowyCiag podanaLiczba
 * reszta zadania utworzona na podstawie materiałów własnych z poprzednio wykonanych zadań i projektów
 * zadaniem funkcji jest zwrócenie ciągu liczb przed wpadnięceim w cykl 4,2,1,4,2,1, itd.
 * @param  wartość c w typie Int
 * @return mutowalna listę Int, której nadano nazwę ciagNowy
 * @throws  błąd, gdy podana wartość c jest ujemna lub równa 0, bo zgodnie z problemem Collatza do porównywania bierzemy liczbę naturalną
 */
fun problem(c:Int): MutableList<Int>{
    if ( c<= 0 ){
        throw IllegalArgumentException("Podano nieprawidlowa liczbe. Liczba musi byc dodatnia i rozna od 0")
    }
    var ciagNowy: MutableList<Int> = mutableListOf()
    var wynik :Int
    var podanaLiczba = c
    ciagNowy.add(podanaLiczba)

    if (podanaLiczba == 1){
        return ciagNowy
    }
    while (podanaLiczba != 1){
        if (podanaLiczba % 2 == 0) {
            podanaLiczba = podanaLiczba / 2
        } else {
            podanaLiczba = 3 * podanaLiczba + 1
        }
        ciagNowy.add(podanaLiczba)
    }
    return ciagNowy
}

fun main(){
    var c = 37
    var ciagNowy = problem(c)
    println("Nowy ciag: $ciagNowy")
    if(ciagNowy != listOf(37,112,56,28,14,7,22,11,34,17,52,26,13,40,20,10,5,16,8,4,2,1)){
        println("Test nie przeszed")
    }else println("Test przeszedl")
    c = 10
    ciagNowy = problem(c)
    println("Nowy ciag: $ciagNowy")
    if(ciagNowy != listOf(10,5,16,8,4,2,1)){
        println("Test nie przeszedl")
    }else println("Test przeszedl")
    c = 1
    ciagNowy = problem(c)
    println("Nowy ciag: $ciagNowy")
    if(ciagNowy != listOf(1)){
        println("Test nie przeszedl")
    }else println("Test przeszedl")
    c = 2
    ciagNowy = problem(c)
    println("Nowy ciag: $ciagNowy")
    if(ciagNowy != listOf(2,1)){
        println("Test nie przeszedl")
    }else println("Test przeszedl")
    try {
        c = 0
        ciagNowy = problem(c)
        println("Test nie przeszedl, bo funkcja wyrzuca $ciagNowy, a miala wyrzucic wyjatek")
    }catch (e: IllegalArgumentException){
        println("Test przeszedl, bo funkcja wyrzuca wyjatek: ${e.message}")
    }
    try{
        c = -4
        ciagNowy = problem(c)
        println("Test nie przeszedl, bo funkcja wyrzuca $ciagNowy, a miala wyrzucic wyjatek")
    }catch (e: IllegalArgumentException){
        println("Test przeszedl, funkcja wyrzuca wyjatek ${e.message}")
    }

}