package io.github.cauzy.GSDS.Client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;

@ApplicationScoped
public class ClientProducer {

    @Produces
    public Client createClient() {
        return ClientBuilder.newClient();
    }
}

