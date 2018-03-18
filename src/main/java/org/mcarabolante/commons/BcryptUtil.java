package org.mcarabolante.commons;

import org.mindrot.jbcrypt.BCrypt;

import java.util.UUID;

public final class BcryptUtil {
    private BcryptUtil(){

    }

    public static String hash(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPw(String password, String encryptedPassword) {
        return BCrypt.checkpw(password, encryptedPassword);
    }

}
