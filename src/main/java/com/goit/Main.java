package com.goit;

import com.goit.database.Database;
import com.goit.entities.Client;
import com.goit.services.ClientService;
import org.flywaydb.core.Flyway;

import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Flyway flyway = Flyway.configure().dataSource(Database.dbUrl, Database.dbUser, Database.dbPass).load();
        flyway.migrate();

        try {
            ClientService cl = new ClientService(Database.getInstance().getConnection());
            System.out.println(cl.listAll().toString());
            System.out.println(cl.getById(5));
            cl.setName(5L, "Danchyc");
            cl.create("Dan");
            System.out.println(cl.listAll().toString());
            cl.deleteById(6L);
            System.out.println(cl.listAll().toString());
            System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }






    }
}