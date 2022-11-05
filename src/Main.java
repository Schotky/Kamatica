public class Main {

    public static void main(String[] args) {
        double preostaliDug = 200000;
        double kamatnaStopa = 8;
        int brojGodinaOtplate = 20;
        int mesecnaDoplata = 1000;

        double mesecnaRata = izracunajMesecnuRatu(preostaliDug, kamatnaStopa, brojGodinaOtplate);

        System.out.println("Iznos kredita: " + (int) preostaliDug);
        System.out.println("Kamatna Stopa: " + kamatnaStopa);
        System.out.println("Godine otplate: " + brojGodinaOtplate);
        System.out.println("Mesecna rata + mesecna doplata = " + (int) mesecnaRata + " + " + mesecnaDoplata);

        double ukupanTrosakKredita = 0;

        for (int trenutnaGodina = 1; trenutnaGodina <= brojGodinaOtplate; trenutnaGodina++) {

            double godisnjaRata = 12 * mesecnaRata;
            double godisnjaKamata = preostaliDug * kamatnaStopa / 100;
            double godisnjaDoplata = 12 * mesecnaDoplata;

            preostaliDug = preostaliDug + godisnjaKamata - godisnjaRata - godisnjaDoplata;
            System.out.println("Dugovanje na kraju " + trenutnaGodina + ".\t godine\t= " + (int) preostaliDug);
            ukupanTrosakKredita += godisnjaRata + godisnjaDoplata;

            if (preostaliDug < 0) {
                ukupanTrosakKredita = ukupanTrosakKredita + preostaliDug;
                System.out.println("Ukupan trosak kredita: " + (int) ukupanTrosakKredita);
                break;
            }
        }
    }

    private static double izracunajMesecnuRatu(double iznosKredita, double godisnjaKamatnaStopa, int brojGodinaOtplate) {
        double mesecnaKamatnaStopa = godisnjaKamatnaStopa / 100 / 12;
        int ukupanBrojRata = brojGodinaOtplate * 12;
        double ukupnaEfektivnaKamatnaStopa = Math.pow(1 + mesecnaKamatnaStopa, ukupanBrojRata);
        return iznosKredita * (mesecnaKamatnaStopa * ukupnaEfektivnaKamatnaStopa / (ukupnaEfektivnaKamatnaStopa - 1));
    }

}