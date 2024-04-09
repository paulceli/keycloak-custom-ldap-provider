package com.celi.auth.provider;

import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.storage.ldap.LDAPStorageProvider;
import org.keycloak.storage.ldap.LDAPStorageProviderFactory;
import org.keycloak.storage.ldap.idm.store.ldap.LDAPIdentityStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomLDAPStorageProvider extends LDAPStorageProvider {

    private static final Logger logger = LoggerFactory.getLogger(CustomLDAPStorageProvider.class);

    public CustomLDAPStorageProvider(LDAPStorageProviderFactory factory, KeycloakSession session, ComponentModel model, LDAPIdentityStore ldapIdentityStore) {
        super(factory, session, model, ldapIdentityStore);
    }


    @Override
    public boolean validPassword(RealmModel realm, UserModel user, String password) {
        //s'all good man
        logger.info("password validation bypassed");
        return true;
    }
}
