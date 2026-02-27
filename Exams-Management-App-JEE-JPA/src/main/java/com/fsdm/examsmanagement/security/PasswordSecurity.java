package com.fsdm.examsmanagement.security;

public class PasswordSecurity {

    /**
     * Hache un mot de passe avec l'algorithme BCrypt.
     *
     * @param password le mot de passe en texte clair
     * @return le mot de passe haché
     */
    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    /**
     * Vérifie un mot de passe en texte clair avec son hash.
     *
     * @param password le mot de passe en texte clair
     * @param hash le mot de passe haché stocké en base
     * @return true si le mot de passe correspond au hash, sinon false
     */
    public static boolean verify(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

}
