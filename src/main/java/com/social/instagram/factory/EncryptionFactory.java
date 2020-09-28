package com.social.instagram.factory;

import com.social.instagram.util.encoding.Sha256Util;

public class EncryptionFactory {

    public Sha256Util sha256Util() {
        return new Sha256Util();
    }

}