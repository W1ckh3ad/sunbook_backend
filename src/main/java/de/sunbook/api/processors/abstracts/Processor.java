package de.sunbook.api.processors.abstracts;

import de.sunbook.api.connections.SqlServerConnection;

public abstract class Processor {
    protected String table;
    protected SqlServerConnection connection;

    public Processor(String table) {
        this.table = table;
        connection = SqlServerConnection.getInstance();
    }
}
