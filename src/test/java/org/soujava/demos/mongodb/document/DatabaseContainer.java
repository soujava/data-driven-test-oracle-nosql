package org.soujava.demos.mongodb.document;

import org.eclipse.jnosql.communication.Settings;
import org.eclipse.jnosql.communication.semistructured.DatabaseConfiguration;
import org.eclipse.jnosql.communication.semistructured.DatabaseManager;
import org.eclipse.jnosql.communication.semistructured.DatabaseManagerFactory;
import org.eclipse.jnosql.databases.oracle.communication.OracleNoSQLConfigurations;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;
public enum DatabaseContainer {

    INSTANCE;

    private final GenericContainer<?> container = new GenericContainer<>
            (DockerImageName.parse("ghcr.io/oracle/nosql:latest-ce"))
            .withExposedPorts(8080);

    {
        container.start();
    }
    public DatabaseManager get(String database) {
        DatabaseManagerFactory factory = managerFactory();
        return factory.apply(database);
    }



    public DatabaseManagerFactory managerFactory() {
        var configuration = DatabaseConfiguration.getConfiguration();
        Settings settings = Settings.builder()
                .put(OracleNoSQLConfigurations.HOST, host())
                .build();
        return configuration.apply(settings);
    }

    public String host() {
        return "http://" + container.getHost() + ":" + container.getFirstMappedPort();
    }
}
