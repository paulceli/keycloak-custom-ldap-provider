package com.celi.auth.provider;

import org.keycloak.Config;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.ldap.LDAPIdentityStoreRegistry;
import org.keycloak.storage.ldap.LDAPStorageProvider;
import org.keycloak.storage.ldap.LDAPStorageProviderFactory;


public class CustomLDAPStorageProviderFactory extends LDAPStorageProviderFactory {

    private LDAPIdentityStoreRegistry ldapStoreRegistry;

    /*
    https://access.redhat.com/documentation/fr-fr/red_hat_build_of_keycloak/22.0/html/server_developer_guide/providers#use_available_providers
    When extending existing Factory, we can't change the ID of the provider and must use the default.

    @Override
    public String getId() {
        return "LDAP_no-password-check";
    }
    */

    @Override
    public int order() {
        return 1;
    }

    @Override
    public void init(Config.Scope config) {
        this.ldapStoreRegistry = new LDAPIdentityStoreRegistry();
    }


    @Override
    public LDAPStorageProvider create(KeycloakSession session, ComponentModel model) {
        var configDecorators = getLDAPConfigDecorators(session, model);
        var ldapIdentityStore = ldapStoreRegistry.getLdapStore(session, model, configDecorators);
        return new CustomLDAPStorageProvider(this, session, model, ldapIdentityStore);
    }

    @Override
    public void close() {
        this.ldapStoreRegistry = null;
    }
}
