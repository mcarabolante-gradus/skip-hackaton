package org.mcarabolante.authentication;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public final class TokenGenerator {
    private final static HashFunction SHA_512 = Hashing.sha512();

    public static String generateToken(){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[160];
        random.nextBytes(bytes);
        return new String(Base64.getEncoder().encode(bytes));
    }

    public static HashCode hashCode(String password){
        return SHA_512.hashString(password, UTF_8);
    }

    public static boolean verify(String password, String encryptedPassword) {
        return SHA_512.hashString(password, UTF_8).toString().equals(encryptedPassword);
    }


}
