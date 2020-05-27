package br.com.aurum.base.utils;

import org.springframework.stereotype.Component;

@Component
public class Utils {

    public String unaccent(String text) {
        String newText = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            String replacement = substituirVolgal(c);
            newText += replacement;
        }

        return newText;
    }
    public String substituirVolgal(char letraInicial) {
        String replacement = substituirVolgal(letraInicial,"a","á|à|â|ã|ä|å|ā|ă|ą|À|Á|Â|Ã|Ä|Å|Ā|Ă|Ą|Æ".split("|"));
        if (replacement == null) {
            replacement = substituirVolgal(letraInicial,"e","è|é|ê|ё|ë|ē|ĕ|ė|ę|ě|È|Ê|Ë|Ё|Ē|Ĕ|Ė|Ę|Ě|€".split("|"));
        }
        if (replacement == null) {
            replacement = substituirVolgal(letraInicial,"i","ı|ì|í|î|ï|ì|ĩ|ī|ĭ|Ì|Í|Î|Ï|Ї|Ì|Ĩ|Ī|Ĭ".split("|"));
        }
        if (replacement == null) {
            replacement = substituirVolgal(letraInicial,"o","ò|ó|ô|õ|ö|ō|ŏ|ő|ø|Ò|Ó|Ô|Õ|Ö|Ō|Ŏ|Ő|Ø|Œ".split("|"));
        }
        if (replacement == null) {
            replacement = substituirVolgal(letraInicial,"u","ù|ú|û|ü|ũ|ū|ŭ|ů|Ù|Ú|Û|Ü|Ũ|Ū|Ŭ|Ů".split("|"));
        }
        if (replacement == null) {
            replacement = new String(new char[]{letraInicial});
        }
        return replacement;
    }

    public String substituirVolgal(char letraInicial, String letraResult, String[] validar) {
        for (String val : validar) {
            if (letraInicial == val.charAt(0)) {
                return letraResult;
            }
        }
        return null;
    }
}
