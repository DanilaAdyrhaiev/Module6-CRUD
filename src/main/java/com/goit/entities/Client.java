package com.goit.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Client {
    long id;
    private String name;
    public Client(){

    }
    public Client(long id, String name){
        this.id = id;
        this.name = name;
    }

//    public List<Client> generateFiveClient(){
//        List<Client> clients = new ArrayList<>();
//        clients.add(new Client("Mark"));
//        clients.add(new Client("Steve"));
//        clients.add(new Client("John"));
//        clients.add(new Client("Mary"));
//        clients.add(new Client("Tony"));
//        return clients;
//    }
}
