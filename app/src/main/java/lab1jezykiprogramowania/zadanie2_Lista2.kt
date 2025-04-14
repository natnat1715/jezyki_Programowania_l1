package lab1jezykiprogramowania

import android.annotation.SuppressLint

/**
 * @author Natalia Kurczyna
 * skorzystano z chatGPT, który podpowiedział użycie bloku init
 */
class DNASequence(
    var identifier: String,
    var data: String,

    ){
    init {
        if(data.isEmpty()){
            throw IllegalArgumentException("Sekwencja DNA nie moze byc pusta")
        }
    }
    val VALID_CHARS =listOf<String>("A", "C", "G", "T")

    val length:Int
        get()= data.length

    /**
     * funkcja zwraca sekwencję zasad w formacie FASTA
     * skorzystano z chatGPT, który wyjaśnił czym jest FASTA i poprawił błędy w implementacji metody oraz zwrócił uwagę, że należy sprawdzać każdy element z data
     * @param funkcja nie przyjmuje wprost żadnych parametrów
     * @return identifier oraz data w formacie FASTA
     * @throws wyjątek jeśli podano nieprawidłową zasadę
     */
    fun toFASTAString (): String{
        if (data.all { it.toString() in VALID_CHARS}) {
            return "$identifier\n$data"
        } else throw IllegalArgumentException("Podano nieprwaidlowa zasade")

    }

    /**
     * funkcja zmienia zasadę z podanej pozycji na nową zasadę, jeśli zadana pozycja znajduje się wśród indeksów, a zmieniana zasada znajduje
     * się wśród dozwolonych zasad
     * skorzystano z chatGPT, który pokazał sposób i poprawił implementację zmiany znaku na inny, pokazał użycie substring, z której skorzystano
     * oraz indices i single
     * @param pozycję w Int oraz zasadę, na którą bezie zmieniona zasada w data w String
     * @return ciąg zasad w DNA ze zmienioną zasadą
     * @throws błąd jeśli podano nieprawidłową zasadę lub indeks większy niż dostępne
     */
    fun mutate(position:Int, value:String): String {
        if (position in data.indices && value.single().toString() in VALID_CHARS) {
            data = data.substring(0, position) + value + data.substring(position + 1)
            return data
        } else throw IllegalArgumentException("Podano nieprwaidlowa zasade lub podano indeks wiekszy niz dostepne")
    }
    /**
     * funkcja znajduje motyw w sekwencji zasad
     * skorzystano z chatGPT, który wyjaśnił czym jest motyw, że w String też można wyszukać danej sekwencji po indeksach, poprawił nieprawidłową
     * implementację metody i pomógł w deklaracji warunku i while
     * @param przyjmuje motif String
     * @return informację na jakiej pozycji został znaleziony dany motyw w Int lub jeśli motyw nie został znaleziony to zwraca o tym komunikat
     * @throws wyjątek jesli podana zostanie nieprawidłowa zasada lub indeks większy niż dostępne
     */
    fun findMotif(motif:String): List<Int> {
        if( !data.all { it .toString() in VALID_CHARS } || !motif.all { it.toString() in VALID_CHARS }) {
            throw IllegalArgumentException("Podano nieprawidlowa zasade, ktora nie moze budowac DNA")
        }
        val motywy = mutableListOf<Int>()
        var indeks = data.indexOf(motif)

        while (indeks >= 0){
            motywy.add(indeks)
            indeks = data.indexOf(motif, indeks +1)
        }
        return if (motywy.isEmpty()) {
            println("Nie znaleziono podanego motywu $motif")
            emptyList()
        } else {
            motywy
        }
    }

    /**
     * funkcja tworzy nić komplementarną do nici kodującej ( w tym przypadku to data)
     * funkcja została stworzona analogicznie do funkcji z zadania z poprzedniej listy
     * @param przyjmuje data String czyli sekwencję zasad w nici kodującej
     * @return odwróconą nić komplementarną, czyli od 5' do 3'
     * @throws nie wyrzuca wyjątków
     */
    fun complement (data:String): String {
        var komplementarna = mutableListOf<String>()
        for (zasada in this.data) {
            when (zasada) {
                'A' -> komplementarna.add("T")
                'T' -> komplementarna.add("A")
                'C' -> komplementarna.add("G")
                'G' -> komplementarna.add("C")
                else -> throw IllegalArgumentException("Podano niepoprawna zasade, ktora nie moze budowac DNA")
            }
        }
        return komplementarna.reversed().joinToString("")
    }

    /**
     * funkcja przekształca nić DNA na odpowiadającą jej sekwencję RNA
     * funkcja została stworzona analogicznie do funkcji z zadania z poprzedniej listy
     * @param przyjmuje nic komplementarną jako listę String
     * @return wynik transkrypcji DNA na RNA w postaci listy String
     * @throws wyjątek jeśli podano nieprawidłową zasadę, która nie może budować RNA
     */
    fun transcribe (komplementarna:String): String{
        var nicRNA = mutableListOf<String>()
        for (zasada in komplementarna){
            when (zasada) {
                'A' -> nicRNA.add("U")
                'T' -> nicRNA.add("A")
                'C' -> nicRNA.add("G")
                'G' -> nicRNA.add("C")
                else -> throw IllegalArgumentException("Podano niepoprawna zasade, ktora nie moze budowac RNA")
            }
        }
        return nicRNA.joinToString("")
    }
}

class RNASequence(
    var identifier: String,
    var data: String,
) {
    val VALID_CHARS = listOf<String>("A", "C", "G", "U")
    val length: Int
        get() = data.length

    /**
     * funkcja zwraca identyfikator i data w formacie FASTA jeżeli wszystkie elementy z data znajdują się wśród dozwolonych elementów
     * skorzystano z chatGPT, który poprawnie zaimplementował błędne fragmenty funkcji
     * @param funkcja nic nie przyjmuje
     * @return zwraca reprezentację nici RNA String (identyfikator oraz sekwencję zasad w RNA) w formacie FASTA
     * @throws wyjątek jeżeli podano zasadę, która nie znajduje się w VALID_CHARS
     */
    fun toFASTAString(): String {
        if (data.all { it.toString() in VALID_CHARS }) {
            return "$identifier\n$data"
        } else throw IllegalArgumentException("Podano nieprwaidlowa zasade")
    }

    /**
     * funkcja zmienia zasadę na określonej pozycji na zadeklarowaną zasadę, jeśli znajduje się ona w VALID_CHARS
     * skorzystano z chatGPT, który poprawił błędną implementację warunku, zasugerował użycie indices oraz single(), a także pomógł w napisaniu
     * implementacji do zwrócenia nowej nici oraz sprawdzenie indeksu
     * @param pozycja w Int oraz zasada jako value w String
     * @return zmienioną nić RNA w String
     * @throws wyjątek jeśli podano nieprawidłową zasadę lub indeks większy niż dostępne
     */
    fun mutate(position: Int, value: String): String {
        if (position in data.indices && value.single().toString() in VALID_CHARS) {
            data = data.substring(0, position) + value + data.substring(position + 1)
            return data
        } else throw IllegalArgumentException("Podano nieprwaidlowa zasade lub podano indeks wiekszy niz dostepne")
    }

    /**
     * funkcja znajduje zadany motyw w sekwencji RNA lub podaje odpowiedni komunikat jeżeli nie znaleziono motywu w nici
     * skorzystano z chatGPT, który poprawił nieprawidłową implementacje, pomógł w napisaniu warunku oraz poprawił pętlę while po indeksach
     * @param motyw w String
     * @return listę Int, która jest indeksami na których znaleziono zadany motyw
     * @throws wyjątej jeżeli podano nieprawidłową zasadę, która nie może budować RNA
     */

    fun findMotif(motif: String): List<Int> {
        if (!data.all { it.toString() in VALID_CHARS } || !motif.all { it.toString() in VALID_CHARS }) {
            throw IllegalArgumentException("Podano nieprawidlowa zasade, ktora nie moze budowac RNA")
        }
        var motywy = mutableListOf<Int>()
        var indeks = data.indexOf(motif)
        while (indeks >= 0) {
            motywy.add(indeks)
            indeks = data.indexOf(motif, indeks+1)
        }
        if (motywy.isEmpty()){
            println("Nie znaleziono motywu $motif")
        }
        return motywy
    }

    /**
     * funkcja szuka w sekwencji RNA (data) kodonu START czyli "AUG" i od tego miejsca zaczyna odczytywać kodony (co 3 litery), każdy kodon
     * tłumaczy na aminokwas, przerywa gdy napotka kodon ST (czyli stop) i jeśli nie znajdzie kodonu start ani stop to zwraca odpowiedni komunikat
     * wiedza o kodonach zaczerpnięta z :https://pl.wikipedia.org/wiki/Kodon
     * skorzystano z chatGPT, który pomógł w poprawieniu nieprawidłowej implementacji i zapisu, zasugerował utworzenie tablicyKodonów jako mapOf,
     * pomógł w implementacji warunku dla kodonu start oraz dał pomysł na użycie while oraz utworzenie zmiennej znalezionyST
     * @param wprost nie przyjmuje żadnych parametrów, ale korzysta z data i identifier
     * @return obiekt klasy ProteinSequence, który zawiera identifier oraz ciąg aminokwasów
     * @throws wyjątki jeśli nie znaleziono kodonu start, stop, brak białek przed kodonem stop lub nie znaleziono białka w tablicy kodonów
     */
    fun transcribe(): ProteineSequence {
        val tablicaKodonow = mapOf(
            "AUG" to "Me",
            "UUU" to "Fe",
            "UUC" to "Fe",
            "CUU" to "Le",
            "CUC" to "Le",
            "CUA" to "Le",
            "CUG" to "Le",
            "AUU" to "Iz",
            "AUC" to "Iz",
            "AUA" to "Iz",
            "GUU" to "Wa",
            "GUC" to "Wa",
            "GUA" to "Wa",
            "GUG" to "Wa",
            "UCU" to "Sr",
            "UCC" to "Sr",
            "UCA" to "Sr",
            "UCG" to "Sr",
            "CCU" to "Pr",
            "CCC" to "Pr",
            "CCA" to "Pr",
            "CCG" to "Pr",
            "ACU" to "Te",
            "ACC" to "Te",
            "ACA" to "Te",
            "ACG" to "Te",
            "GCU" to "Al",
            "GCC" to "Al",
            "GCA" to "Al",
            "GCG" to "Al",
            "UAU" to "Ty",
            "UAC" to "Ty",
            "CAU" to "Hi",
            "CAC" to "Hi",
            "CAA" to "Gt",
            "CAG" to "Gt",
            "AAU" to "As",
            "AAC" to "As",
            "AAA" to "Li",
            "AAG" to "Li",
            "GAU" to "Ap",
            "GAC" to "Ap",
            "GAA" to "Gl",
            "GAG" to "Gl",
            "UGU" to "Cy",
            "UGC" to "Cy",
            "UGG" to "Tr",
            "CGU" to "Ar",
            "CGC" to "Ar",
            "CGA" to "Ar",
            "AGU" to "Se",
            "AGC" to "Se",
            "AGA" to "Ar",
            "AGG" to "Ar",
            "GGU" to "Gi",
            "GGC" to "Gi",
            "GGA" to "Gi",
            "GGG" to "Gi",
            "UAA" to "ST",
            "UAG" to "ST",
            "UGA" to "ST"
        )
        val kodonSTART = data.indexOf("AUG")
        if (kodonSTART == -1) {
            throw IllegalArgumentException("Brak kodonu START w podanej sekwencji")
        }
        val nowaNic = mutableListOf<String>()
        var i = kodonSTART
        var znaleznionyST = false
        while (i + 3 <= data.length) {
            val kodon = data.substring(i, i + 3)
            val aminokwas = tablicaKodonow[kodon]
                ?: throw IllegalArgumentException("Nie znalezniomo bialka w tablicy kodonow")

            if (aminokwas == "ST") {
                znaleznionyST= true
                break
            } else nowaNic.add(aminokwas)
            i += 3
        }
        if (!znaleznionyST){
            throw IllegalArgumentException ("Nie znaleziono kodonu STOP w sekwencji")
        }
        if (nowaNic.isEmpty()){
            throw IllegalArgumentException ("Brak bialek przed kodonem STOP")
        }
        return ProteineSequence(identifier, nowaNic.joinToString(""))

    }
}
class ProteineSequence(
    var identifier: String,
    var data: String,
){ val VALID_CHARS = listOf<String>("Me", "Fe", "Le", "Iz", "Wa", "Sr", "Pr", "Te", "Al", "Ty", "Hi", "Gt",
    "As", "Li", "Ap", "Gl", "Cy", "Tr", "Ar", "Se", "Gi", "ST")
    val length:Int
        get() = data.length/2

    /**
     * funkcja zwraca identyfikator i data w formacie FASTA jeżeli wszystkie elementy z data znajdują się wśród dozwolonych elementów,
     * skorzystano z chatGPT, który poprawnie zaimplementował błędne fragmenty funkcji i zasugerował użycie chunked(), który dzieli w tym
     * przypadku String na mniejsze łańcuchy znaków (po 2 znaki)
     * @param funkcja nic nie przyjmuje
     * @return zwraca reprezentację białka String (identyfikator oraz sekwencję aminokwasów w białku) w formacie FASTA
     * @throws wyjątek jeżeli podano aminokwas, który nie znajduje się w VALID_CHARS
     */
    fun toFASTAString (): String{
        if (data.chunked(2).all { it.toString() in VALID_CHARS}) {
            return "$identifier\n$data"
        } else throw IllegalArgumentException("Podano nieprwaidlowy aminokwas")

    }

    /**
     * funkcja sprawdza czy szukany motyw znajduje się w białku i jeśli tak to zwraca jego pozycję, a jeśli nie to wyświetla informację, że
     * motyw nie został znaleziony, na początku zostaje utworzona zmienna obecna, która dzieli listę aminokwasów na dorbne listy po dwa znaki
     * (tutaj jeden aminokwas zapisywany jest jako dwa znaki)
     * skorzystano z chatGPT, który poprawił błędnie zaimplementowane fragmenty funkcji, zasugerował stworzenie zmiennych obecna i szukanyMotyw,
     * i pomógł w implementacji pętli for
     * @param motyw w String
     * @return lista Int, czyli lista indeksów pod którymi został znaleziony szukany motyw lub komunikat, że nie znalezniono motywu
     * @throws wyjątek jeśli podano aminokwas, który nie znajduje się w VALID_CHARS
     */
    fun findMotif(motif:String): List<Int> {
        var motywy = mutableListOf<Int>()
        var obecna = data.chunked(2)
        var szukanyMotyw = motif

        if( !data.chunked(2).all { it .toString() in VALID_CHARS } || !motif.chunked(2).all { it.toString() in VALID_CHARS }) {
            throw IllegalArgumentException("Podano nieprawidlowy aminokwas")
        }

        for (i in obecna.indices){
            if ( obecna[i] == szukanyMotyw){
                motywy.add(i)
            }
        }
        if (motywy.isEmpty()){
            println("Nie znaleziono motywu $motif")
        }
        return motywy
    }
}

@SuppressLint("SuspiciousIndentation")
fun main(){
    var identifier = "Seq123"
    var data = "AACTGG"
    var motif = "CTG"
    var sekwencja = DNASequence(identifier, data)
    println("Sekwencja ma dlugosc: ${sekwencja.length}")
    if( sekwencja.length == 6){
        println("Test przeszedl")
    }else println("Test nie przeszedl")
    println("DNA w formacie FASTA: ${sekwencja.toFASTAString()}")
    if( sekwencja.toFASTAString() == "Seq123\nAACTGG" ){
        println("Test przeszedl")
    }else println("Test nie przeszedl")
    val zmienionaSekwencja = sekwencja.mutate(1, "C")
    println("Zmieniona sekwencja: $zmienionaSekwencja")
    if( zmienionaSekwencja == "ACCTGG" ){
        println("Test przeszedl")
    }else println("Test nie przeszedl")
    val pozycja = sekwencja.findMotif(motif)
    sekwencja.findMotif(motif)
    if( pozycja == listOf(2) ){
        println("Test przeszedl")
    }else println("Test nie przeszedl")
    val komplementarna = sekwencja.complement(sekwencja.data)
    println("Nic komplementarna: $komplementarna")
    if( komplementarna == "CCAGGT"){
        println("Test przeszedl")
    }else println("Test nie przeszedl")
    var transkrybujaca = sekwencja.transcribe(komplementarna)
    println("Nic RNA: $transkrybujaca")
    if( transkrybujaca == "GGUCCA"){
        println("Test przeszedl")
    }else println("Test nie przeszedl")
    try{
        val pusteDNA = DNASequence("PustaSeq", "")
        println("Test nie przeszedl, bo funkcja zwrocila ${pusteDNA.length}")
    }catch (e:IllegalArgumentException){
        println("Test przeszedl, bo funkcja wyrzucila wyjatek ${e.message}")
    }
    try{
        val pusteDNA = DNASequence("PustaSeq", "")
        println("Test nie przeszedl, bo funkcja zwrocila ${pusteDNA.mutate(1, "A")}")
    }catch (e:IllegalArgumentException){
        println("Test przeszedl, bo funkcja wyrzucila wyjatek ${e.message}")
    }
    try{
        val pusteDNA = DNASequence("PustaSeq", "")
        println("Test nie przeszedl, bo funkcja zwrocila ${pusteDNA.findMotif("CCC")}")
    }catch (e:IllegalArgumentException){
        println("Test przeszedl, bo funkcja wyrzucila wyjatek ${e.message}")
    }
    try{
        val pusteDNA = DNASequence("PustaSeq", "")
        println("Test nie przeszedl, bo funkcja zwrocila ${pusteDNA.transcribe(komplementarna)}")
    }catch (e:IllegalArgumentException){
        println("Test przeszedl, bo funkcja wyrzucila wyjatek ${e.message}")
    }
    try{
        val pusteDNA = DNASequence("PustaSeq", "")
        println("Test nie przeszedl, bo funkcja zwrocila ${pusteDNA.toFASTAString()}")
    }catch (e:IllegalArgumentException){
        println("Test przeszedl, bo funkcja wyrzucila wyjatek ${e.message}")
    }
    var identifier1 = "Seq456"
    var sekwencja1 = RNASequence(identifier1, transkrybujaca)
    println("Sekwencja ma dlugosc: ${sekwencja1.length}")
    if( sekwencja1.length == 6){
        println("Test przeszedl")
    }else println("Test nie przeszedl")
    println("RNA w formacie FASTA: ${sekwencja1.toFASTAString()}")
    if( sekwencja1.toFASTAString() =="Seq456\nGGUCCA"){
        println("Test przeszedl")
    }else println("Test nie przeszedl")
    println("Zmieniona sekwencja: ${sekwencja1.mutate(1, "C")}")
    if( sekwencja1.mutate(1,"C") == "GCUCCA" ){
        println("Test przeszedl")
    }else println("Test nie przeszedl")
    var motifRNA = "UCC"
    val pozycjaRNA1 = sekwencja1.findMotif(motifRNA)
    println("Pozycja motywu $motifRNA: $pozycjaRNA1")
    if (pozycjaRNA1 == listOf(2)){
        println("Test przeszedl")
    } else println("Test nie przeszedl")

    motifRNA = "AGG"
    val pozycjaRNA2 = sekwencja1.findMotif(motifRNA)
    println("$pozycjaRNA2")
    try{
        val zlyMotyw = "ABH"
        sekwencja1.findMotif(zlyMotyw)
        println("Test nie przeszedl, bo funkcja nie wyrzuciala wyjatku")
    } catch( e:IllegalArgumentException){
        println("Test przeszedl, bo funkcja wyrzucila wyjatek ${e.message}")
    }
    try {
        sekwencja1.transcribe()
        println("Test nie przeszedl, bo funkcja nie wyrzucila wyjatku")
    } catch (e:IllegalArgumentException){
        println("Test przeszedl bo funkcja wyrzucila wyjatek ${e.message}")
    }
    var identifier3 = "SeqB765"
    var data3 = "MeLiTyST"
    var sekwencja4 = ProteineSequence(identifier3, data3)
    println("Dlugosc: ${sekwencja4.length}")
    var sekwencja6 = RNASequence("SeqB345", "CGFAUGCCCGAAUAGAAC")
    val wynikTranskrypcji2 = sekwencja6.transcribe()
    println("Wynik transkrypcji: ${wynikTranskrypcji2.toFASTAString()}")
    if (wynikTranskrypcji2.toFASTAString() == "SeqB345\nMePrGl"){
        println("Test przeszedl")
    } else println("Test nie przeszedl")
    var motifBialko = "Li"
    val pozycjaBialko1 = sekwencja4.findMotif(motifBialko)
    println("Pozycja motywu $motifBialko: $pozycjaBialko1")
    if (pozycjaBialko1 == listOf(1)){
        println("Test przeszedl")
    } else println("Test nie przeszedl")
    motifBialko = "Se"
    if (sekwencja4.findMotif(motifBialko).isEmpty()){
        println("Test przeszedl, bo  motyw $motifBialko nie zostal znaleziony")
    }else println("Test nie przeszedl, bo motyw $motifBialko nie powinien zostac znaleziony")
    try{
        val zlyMotywBialko = "ABH"
        sekwencja4.findMotif(zlyMotywBialko)
        println("Test nie przeszedl, bo funkcja nie wyrzuciala wyjatku")
    } catch( e:IllegalArgumentException){
        println("Test przeszedl, bo funkcja wyrzucila wyjatek ${e.message}")
    }
    try {
        var sekwencja5 = RNASequence("SeqB123", "AUGAAACGC")
        sekwencja5.transcribe()
        println("Test nie przeszedl, bo funkcja nie wyrzucila wyjatku")
    } catch (e:IllegalArgumentException){
        println("Test przeszedl, bo funkcja wyrzucila wyjatek ${e.message}")
    }
    try {
        var sekwencja7 = RNASequence("SeqB111", "CGFAUGCCCGAAAAC")
        sekwencja7.transcribe()
        println("Test nie przeszedl, bo funkcja nie wyrzucila wyjatku")
    } catch (e:IllegalArgumentException){
        println("Test przeszedl bo funkcja wyrzucila wyjatek ${e.message}")
    }

}