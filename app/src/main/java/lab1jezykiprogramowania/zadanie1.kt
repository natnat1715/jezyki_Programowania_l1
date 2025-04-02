package lab1jezykiprogramowania

import kotlin.math.sqrt

/**
 * @author Natalia Kurczyna
 * Do wzoru herona i wyswietlanych komunikatow skorzystano z: https://pl.wikipedia.org/wiki/Wzór_Herona
 * korzystano z biblioteki kotlina do rzucania błędów i try and catch
 * skorzystano z chatGPT, który pomógł w znalezieniu błędów przy implementacji testów dla wartości, które miałyby wyrzucać wyjątki i zaproponował rozwiązania, z których skorzystano
 * funkcja heron liczy pole trojkata wykorzystujac wzor Herona
 * @param wartosc boku a w Double, wartosc boku b w Double, wartosc boku c w Double
 * @return wartosc pola trojkata w Double
 */
fun heron (a:Double, b:Double, c:Double):Double {
    if (a<=0 || b<= 0 || c<=0){
        throw IllegalArgumentException("Podano nieprawidlowa wartosc boku rowna 0 lub liczbie ujemnej")
    }
    if( a+b<=c || b+c<=a || a+c<=b){
        throw IllegalArgumentException("Odcinkami o podanych dlugosciach nie mozna polaczyc trzech punktow tej samej plaszczyzny, wiec wartosc pola nie nalezy do liczby rzeczywistych")
    }
    val p = ((a+b+c)/2)
    val pole1 = (sqrt(p*(p-a)*(p-b)*(p-c)))
    return pole1
}
fun main(){
    var pole = heron(3.0, 4.0, 5.0)
    println("Pole: $pole")
    try {
        var pole1 = heron( 3.0, 4.0, 5.0)
        println("Pole1: $pole1 ")
    }catch ( e: IllegalArgumentException){
        println("Pole1: ${e.message}")
    }
    if (pole != 6.0){
        println("Test nie przeszedl")
    }else println("Test przeszedl")
    try{
        var pole1 = heron( 5.0, 3.1, 0.1)
        println("Test nie przeszedl, bo funkcja zwrocila $pole1 a powinna wyrzucic wyjatek")
    } catch(e:IllegalArgumentException){
        println("Test przeszedl, bo funkcja wyrzucila wyjatek: ${e.message}")
    }
    try{
       var pole2= heron (-1.1, 4.0, 5.1)
        println("Test nie przeszedl, bo funkcja zwrocila $pole2 a powinna wyrzucic wyjatek")
    } catch(e:IllegalArgumentException){
        println("Test przeszedl, bo funkcja wyrzucila wyjatek: ${e.message}")
    }
    try{
        var pole3 = heron(4.0,5.0, 9.0)
        println("Test nie przeszedl, bo funkcja zwrocila $pole3 a powinna wyrzucic wyjatek")
    }catch (e: IllegalArgumentException){
        println("Test przeszedl, bo funkcja wyrzucila wyjatek: ${e.message}")
    }
}