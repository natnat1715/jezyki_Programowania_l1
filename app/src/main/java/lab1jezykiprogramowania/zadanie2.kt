package lab1jezykiprogramowania
/**
* @author Natalia Kurczyna
 * wiedza o multizbiorze została zaczerpnięta z zajęć laboratorynych
 * do poprawienia kodu użyto chatGPT, który poprawił tworzenie mutowalnej listy i zaproponował usuwanie elementów z listy i przerwanie pętli, gdy znajdzie odpowiadającą wartość w kopii drugiej tablicy
 *@param funkcja przyjmuje x (lista Int), y (lista Int)
 * @return mutowalną listę Int zawierającą część wspólną listy x i listy y
 * funkcja pętlą porównuje wartość elementu i z listy x z wartościami j z kopią listy y i jeśli znajdzie wspólną wartość to dodaje ją do czescWspolna i usuwa
 * ten element z kopii listy y aby nie był on porównywany przy następnym przebiegu pętli
 */
fun wspolne( x: List<Int>, y:List<Int>): MutableList<Int>{
   val yKopia = y.toMutableList()
   var czescWspolna= mutableListOf<Int>()
  for (i in x){
      for ( j in yKopia){
        if ( i == j){
            czescWspolna.add(i)
            yKopia.remove(j)
            break
        }
      }
  }
    return czescWspolna
}
fun main(){
 var x = listOf(2,2,3,5,6,6,7)
 var y = listOf(2,2,4,6,7,34,2)
 var czescWspolna = wspolne(x,y)
  println(" Elementy wspolne:${czescWspolna}")
  if (czescWspolna != listOf(2,2,6,7)){
      println("Test nie przeszedl")
  } else println("Test przeszedl")
  x = listOf()
  y = listOf(1,0)
    czescWspolna = wspolne(x,y)
    println(" Elementy wspolne:${czescWspolna}")
  if (czescWspolna != emptyList<Int>()){
     println("Test nie przeszedl")
  } else println("Test przeszedl")
  x = listOf(1,1,1,-1)
  y = listOf(1,0,-1,5,1)
    czescWspolna = wspolne(x,y)
    println(" Elementy wspolne:${czescWspolna}")
  if (czescWspolna != listOf(1,1,-1)) {
      println("Test nie przeszedl")
  }else println("Test przeszedl")
}
