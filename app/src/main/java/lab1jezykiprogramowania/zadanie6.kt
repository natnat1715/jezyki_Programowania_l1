package lab1jezykiprogramowania

/**
 * @author Natalia Kurczyna
 * do poprawienia napisanego kodu użyto chatGPT, któy zasugerował, że zmienna matrycowa i kodująca powinny być listą a nie tabelą
 * oraz użycie "" dla każdego elementu z listy w funkcji main oraz joinToString() w println
 */

/**
 * skorzystano z chatGPT, który pomógł w poprawnej zmianie napisanego kodu dla wartości, dla których funkcja powinna wyrzucać wyjątek
 * funkcja komplement ma za zadanie stworzyć nić matrycową na podstawie nici kodującej podanej w funkcji main
 * @param przyjmuje zmienną o nazwie kodująca (lista String)
 * @return odwróconą listę String o nazwie matrycowa
 * @throws bład gdy podana zostanie zasada, która nie może zbudować nici DNA
 */
fun komplement(kodujaca: List<String>): List<String> {
    var matrycowa = mutableListOf<String>()
    for (zasada in kodujaca) {
        when (zasada) {
            "A" -> matrycowa.add("T")
            "T" -> matrycowa.add("A")
            "C" -> matrycowa.add("G")
            "G" -> matrycowa.add("C")
            else -> throw IllegalArgumentException("Podano niepoprawna zasade, ktora nie moze budowac DNA")
        }
    }
    return matrycowa.reversed()
}

/**
 * skorzystano z chatGPT, który pomógł w poprawnej zmianie napisanego kodu dla wartości, dla których funkcja powinna wyrzucać wyjątek
 * funkcja transkrybuj tworzy nić RNA na podstawie nici matrycowej zwróconej przez funkcję komplement
 * @param przyjmuje  listę String o nazwie matrycowa
 * @return listę String o nazwie nicRNA
 * @throws błąd gdy podana zostanie zasada, któa nie może zbudować nici RNA
 */
fun transkrybuj(matrycowa: List<String>): List<String>{
    var nicRNA = mutableListOf<String>()
    for (zasada in matrycowa) {
        when (zasada) {
            "A" -> nicRNA.add("U")
            "T" -> nicRNA.add("A")
            "C" -> nicRNA.add("G")
            "G" -> nicRNA.add("C")
            else -> throw IllegalArgumentException("Podano niepoprawna zasade, ktora nie moze budowac DNA")
        }
    }
    return nicRNA
}
fun main() {
    var kodujaca = listOf("A", "C", "C", "C", "T", "A")
    println("Nic kodujaca od 5' do 3': ${kodujaca.joinToString()}")
    var matrycowa = komplement(kodujaca)
    println("Nic matrycowa od 5' do 3': ${matrycowa.reversed().joinToString()} ")
    if (matrycowa!= listOf("T","A","G","G","G","T")){
        println("Test nie przeszedl")
    }else println("Test przeszedl")
    var nicRNA = transkrybuj(matrycowa)
    println("Nic RNA od 5' do 3': ${nicRNA.joinToString()}")
    if (nicRNA != listOf("A","U","C","C","C","A")){
        println("Test nie przeszedl")
    }else println("Test przeszedl")
    try{
        val zlaKodujaca = listOf("Z", "H", "K", "O")
        matrycowa =komplement(zlaKodujaca)
        println("Test nie przeszedl, bo funkcja zwrocila $matrycowa, a miala wyrzucic wyjatek")
    }catch (e:IllegalArgumentException){
        println("Test przeszedl, bo funkcja wyrzucila wyjatek: ${e.message}")
    }
    try {
        val zlaMatrycowa = listOf("Z", "H", "K", "O")
        nicRNA = transkrybuj(zlaMatrycowa)
        println("Test nie przeszedl, bo funkcja zwrocila $nicRNA, a miala wyrzucic wyjatek")
    }catch (e:IllegalArgumentException){
        println("Test przeszedl, bo funkcja wyrzucila wyjatek ${e.message}")
    }
}