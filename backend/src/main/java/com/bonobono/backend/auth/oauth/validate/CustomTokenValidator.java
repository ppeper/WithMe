package com.bonobono.backend.auth.oauth.validate;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

public interface CustomTokenValidator {

    public Map<String, Object> validate(String idTokenString) throws GeneralSecurityException, IOException;
}
