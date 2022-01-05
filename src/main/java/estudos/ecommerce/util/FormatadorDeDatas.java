package estudos.ecommerce.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatadorDeDatas {

    private FormatadorDeDatas (){
        throw new IllegalArgumentException("Classe utilitaria, não deve ser instanciada");
    }

    public static LocalDate converteParaPadraoBrasil(String dataRecebida) {
        validaFormatacaoDeDataHoraVindaDaURL(dataRecebida);
        return LocalDate.parse(dataRecebida, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public static void validaFormatacaoDeDataHoraVindaDaURL(String data) {
        Pattern pattern = Pattern.compile("[0-9]{2}-[0-9]{2}-[0-9]{4}");
        Matcher matcher = pattern.matcher(data);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("data e hora não estão no formato especificado.");
        }
    }
}
