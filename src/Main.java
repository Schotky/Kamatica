import java.math.BigDecimal;
import java.math.MathContext;

public class Main {

    public static void main(String[] args) {
        BigDecimal vrednostStana = new BigDecimal(231360);
        BigDecimal ucesceProcenat = new BigDecimal(20);
        BigDecimal ucesceSuma = vrednostStana.multiply(ucesceProcenat).divide(new BigDecimal(100), MathContext.DECIMAL128);
        BigDecimal preostaliDug = vrednostStana.multiply(new BigDecimal(100).subtract(ucesceProcenat)).divide(new BigDecimal(100), MathContext.DECIMAL128);
        BigDecimal iznosKredita = new BigDecimal(String.valueOf(preostaliDug));
        BigDecimal kamatnaStopa = new BigDecimal("7.55");
        BigDecimal brojGodinaOtplate = new BigDecimal(20);
        BigDecimal mesecnaDoplata = new BigDecimal(0);

        BigDecimal mesecnaRata = izracunajMesecnuRatu(preostaliDug, kamatnaStopa, brojGodinaOtplate);

        System.out.println("Vrednost stana: " + vrednostStana.toBigInteger());
        System.out.println("Ucesce: " + ucesceSuma.toBigInteger());
        System.out.println("Iznos kredita: " + preostaliDug.toBigInteger());
        System.out.println("Kamatna Stopa: " + kamatnaStopa);
        System.out.println("Godine otplate: " + brojGodinaOtplate);
        System.out.println("Mesecna rata + mesecna doplata = " + mesecnaRata.toBigInteger() + " + " + mesecnaDoplata);

        BigDecimal ukupanTrosakKredita = new BigDecimal(0);

        for (int trenutnaGodina = 1; trenutnaGodina <= (brojGodinaOtplate.intValue() + 1); trenutnaGodina++) {

            BigDecimal godisnjaRata = mesecnaRata.multiply(new BigDecimal(12));
            BigDecimal godisnjaKamata = preostaliDug.multiply(kamatnaStopa).divide(new BigDecimal(100), MathContext.DECIMAL128);
            BigDecimal godisnjaDoplata = mesecnaDoplata.multiply(new BigDecimal(12));

            preostaliDug = preostaliDug.add(godisnjaKamata).subtract(godisnjaRata).subtract(godisnjaDoplata);
            System.out.println("Dugovanje na kraju " + trenutnaGodina + ".\t godine\t= " + preostaliDug.intValue());
            ukupanTrosakKredita = ukupanTrosakKredita.add(godisnjaRata).add(godisnjaDoplata);

            if (preostaliDug.compareTo(new BigDecimal(0)) < 0) {
                ukupanTrosakKredita = ukupanTrosakKredita.add(preostaliDug);
                System.out.println("Ukupan trosak kredita: " + ukupanTrosakKredita.intValue());
                System.out.println("Banci poklonili: " + (ukupanTrosakKredita.subtract(iznosKredita)).intValue());
                break;
            }
        }
    }

    private static BigDecimal izracunajMesecnuRatu(BigDecimal iznosKredita, BigDecimal godisnjaKamatnaStopa, BigDecimal brojGodinaOtplate) {
        BigDecimal mesecnaKamatnaStopa = (godisnjaKamatnaStopa.divide(new BigDecimal(100), MathContext.DECIMAL128)).divide(new BigDecimal(12), MathContext.DECIMAL128);
        BigDecimal ukupanBrojRata = brojGodinaOtplate.multiply(new BigDecimal(12));
        BigDecimal ukupnaEfektivnaKamatnaStopa = (mesecnaKamatnaStopa.add(new BigDecimal(1))).pow(ukupanBrojRata.intValue());
        return iznosKredita.multiply(mesecnaKamatnaStopa.multiply(ukupnaEfektivnaKamatnaStopa).divide(ukupnaEfektivnaKamatnaStopa.subtract(new BigDecimal("1")), MathContext.DECIMAL128));
    }

}