package com.ey.accountservice.models;


import javax.persistence.*;

@Entity
@Table(name = "bankaccounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_id")
    private int id;

    @Column(columnDefinition = "NUMERIC(12,2)")
    private int amount;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String acc_type;
    private int transaction_limit;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    public Account(int id, int amount, String name, String acc_type, int transaction_limit, User user_id) {
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.acc_type = acc_type;
        this.transaction_limit = transaction_limit;
        this.user_id = user_id;
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcc_type() {
        return acc_type;
    }

    public void setAcc_type(String acc_type) {
        this.acc_type = acc_type;
    }

    public int getTransaction_limit() {
        return transaction_limit;
    }

    public void setTransaction_limit(int transaction_limit) {
        this.transaction_limit = transaction_limit;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", amount=" + amount +
                ", name='" + name + '\'' +
                ", acc_type='" + acc_type + '\'' +
                ", transaction_limit=" + transaction_limit +
                ", user_id=" + user_id +
                '}';
    }
}






