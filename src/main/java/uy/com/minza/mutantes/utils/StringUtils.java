package uy.com.minza.mutantes.utils;

import org.springframework.stereotype.Component;

/**
 * Utilidades para trabajar con Strings
 */
@Component("stringUtils")
public class StringUtils {

    /**
     * Verifica que un string solamente contenga ciertos caracteres
     *
     * @param input String que se quiere verificar
     * @param chars Conjunto de caracteres permitidos
     * @return true si el String de entrada solamente contiene caracteres dentro del conjunto de caracteres permitidos
     */
    public boolean containsOnly(String input, char... chars) {
        if (chars == null) {
            return false;
        } else {
            for (char aChar : chars) {
                input = input.replaceAll(String.valueOf(aChar), "");
            }
            return "".equals(input);
        }
    }
}
