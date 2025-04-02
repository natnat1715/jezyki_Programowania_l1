package lab1jezykiprogramowania

/**
 * @author Natalia Kurczyna
 * ciąg Fibonacciego: https://pl.wikipedia.org/wiki/Ci%C4%85g_Fibonacciego
 * do poprawienia kodu użyto chatGPT, który poprawił błędy w niepoprawnie działającym kodzie oraz pokazał jak zaimplementować prosty test sprawdzający czy program wyrzuci wyjątek i poprawił fragment kodu służący do zwrócenia pustej listy
 *funkcja ciagPetla liczy ciag Fibonacciego z użyciem pętli
 * @param funkcja ciagPetla przyjmuje n w typie Int (n to wielkość listy)
 * @return  mutowalna lista Int
 * @throws funkcja wyrzuca wyjątek kiedy n oznaczjące liczbę elementów listy jest mniejsze od 0, natomiast kiedy n jest równe 0 lub 1 to zwracają listę Int z wartością n
 */
 fun ciagPetla(n:Int): MutableList<Int>{
    if (n < 0) throw IllegalArgumentException("Podano nieprawidlowa wartosc ciagu: liczba elementow ciagu nie moze byc ujemna")
    if (n <2) return MutableList(n){0}
    val list = MutableList(n) {0}
    list[0] = 0
    list[1] = 1
    for( i in 2 until n ){
        list[i] = list[i-1] + list[i-2]
    }
    return list
 }

/**
 * funkcja ciagRekursja liczy ciąg Fibonacciego rekurencyjnie
 * rekursaj/rekurencja: https://pl.wikipedia.org/wiki/Rekurencja
 * skorzystano z pomocy chatGPT, który zasugerował stworzenie mutowalnej listy, wyjaśnił działanie rekursji na przykładzie i pomógł w zadeklarowaniu parametrów funkcji oraz
 * poprawił błędy w niepoprawnie działającym kodzie oraz pokazał jak zaimplementować prosty test sprawdzający czy program wyrzuci wyjątek i poprawił fragment kodu do zwrócenia pustej listy
 * @param przyjumje n w typie Int (gdzie n to wielkość tablicy), d w typie Int, e w typie Int, list1 która jest listą typów Int
 * @return mutowalną listę w typie String
 * @throws wyjątek kiedy n oznaczające liczbę elementów listy jest mniejsze od 0, natomiast kiedy n jest równe 0 lub 1 to zwraca listę Int z wartością n
 */
    fun ciagRekursja (n:Int, d: Int= 0 , e: Int = 1, list1: MutableList<Int> = mutableListOf()): MutableList<Int> {
        if (n < 0) throw IllegalArgumentException("Podano nieprawidlowa wartosc ciagu: liczba elementow ciagu nie moze byc ujemna")
        if (n == 0) return mutableListOf()
        if (n == 1) return mutableListOf(0)
        if (list1.size == n) return list1
        list1.add(d)
        return ciagRekursja(n, e, e+d, list1)
    }



fun main(){
    var n = 5
    var list = ciagPetla(n)
    println(list.joinToString())
    if (list != listOf(0,1,1,2,3)){
        println("Test nie przeszedl")
    }else println("Test przeszedl")
    var list1 = ciagRekursja(n, 0, 1, mutableListOf() )
    println(list1.joinToString())
    if (list1 != listOf(0,1,1,2,3)){
        println("Test nie przeszedl")
    }else println("Test przeszedl")
    n = 8
    list = ciagPetla(n)
    println(list.joinToString())
    if (list != listOf(0,1,1,2,3,5,8,13)){
        println("Test nie przeszedl")
    }else println("Test przeszedl")
    list1 = ciagRekursja(n, 0, 1, mutableListOf() )
    println(list1.joinToString())
    if (list1 != listOf(0,1,1,2,3,5,8,13)){
        println("Test nie przeszedl")
    }else println("Test przeszedl")
    n = 1
    list = ciagPetla(n)
    println(list.joinToString())
    if (list != listOf(0)){
        println("Test nie przeszedl")
    }else println("Test przeszedl")
    list1 = ciagRekursja(n, 0, 1, mutableListOf() )
    println(list1.joinToString())
    if (list1 != listOf(0)){
        println("Test nie przeszedl")
    }else println("Test przeszedl")
    n = 0
    list = ciagPetla(n)
    println(list.joinToString())
    if (list != emptyList<Int>()){
        println("Test nie przeszedl")
    }else println("Test przeszedl")
    list1 = ciagRekursja(n, 0, 1, mutableListOf() )
    println(list1.joinToString())
    if (list1 != emptyList<Int>()){
        println("Test nie przeszedl")
    }else println("Test przeszedl")
    n = 2
    list = ciagPetla(n)
    println(list.joinToString())
    if (list != listOf(0,1)){
        println("Test nie przeszedl")
    }else println("Test przeszedl")
    list1 = ciagRekursja(n, 0, 1, mutableListOf() )
    println(list1.joinToString())
    if (list1 != listOf(0,1)){
        println("Test nie przeszedl")
    }else println("Test przeszedl")
    try{
        n = -5
        list = ciagPetla(n)
        println("Test nie przeszedl")
    }catch (e: IllegalArgumentException){
        println("Test przeszedl: funkcja wyrzuca wyjatek: ${e.message}")
    }
    try{
        n = -5
        list1 = ciagRekursja(n)
        println("Test nie przeszedl")
    }catch (e: IllegalArgumentException){
        println("Test przeszedl: funkcja wyrzuca wyjatek: ${e.message}")
    }
}