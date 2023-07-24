package com.goit.services;

import com.goit.entities.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private PreparedStatement createPrSt;
    private PreparedStatement readPrSt;
    
    private Statement readSt;
    private PreparedStatement updatePrSt;
    private PreparedStatement deletePrSt;

    public ClientService(Connection connection){
        try {
            createPrSt = connection.prepareStatement("insert client(name) value(?)", Statement.RETURN_GENERATED_KEYS);
            readPrSt = connection.prepareStatement("select name from client where id = ?");
            updatePrSt = connection.prepareStatement("update client set name = ? where id = ?");
            deletePrSt = connection.prepareStatement("delete from client where id = ?");
            readSt = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long create(String name){
        try{
            long val = 0L;
            createPrSt.setString(1, name);
            createPrSt.executeUpdate();
            ResultSet keys = createPrSt.getGeneratedKeys();
            if(keys.next())
                return keys.getLong(1);
            else
                return val;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getById(long id){
        try{
            readPrSt.setLong(1, id);
            ResultSet rs = readPrSt.executeQuery();
            if(!rs.next()){
                return null;
            }
            return rs.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setName(long id, String name){
        try {
            updatePrSt.setLong(2, id);
            updatePrSt.setString(1, name);
            updatePrSt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(long id){
        try {
            deletePrSt.setLong(1, id);
            deletePrSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Client> listAll(){
        List<Client> clients = new ArrayList<>();
        try {
            ResultSet rs = readSt.executeQuery("select * from client");
            while(rs.next()){
                long id = rs.getLong(1);
                String name = rs.getString(2);
                clients.add(new Client(id, name));
            }
            return clients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
