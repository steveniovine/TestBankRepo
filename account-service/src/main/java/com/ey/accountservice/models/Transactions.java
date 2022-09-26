package com.ey.accountservice.models;


import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private int id;

    @OneToOne
    @JoinColumn(name = "useraccountid")
    private User userAccountId;

    @OneToOne
    @JoinColumn(name = "targetaccountid")
    private User targetAccountId;



    @Column(columnDefinition = "NUMERIC(12,2) CHECK (amount>=100)")
    private int amount;

    @Column(name = "startdate")
    private String startDate;

    @Column(name = "completiondate")
    private String completionDate;

    public void Transaction() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public User getUserAccountId() {
        return userAccountId;
    }
    public void setUserAccountId(User userAccountId) {
        this.userAccountId = userAccountId;
    }
    public User getTargetAccountId() {
        return targetAccountId;
    }
    public void setTargetAccountId(User targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getCompletionDate() {
        return completionDate;
    }
    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }



    @Override
    public String toString() {
        return "Transaction{" +
                "userAccountId=" + userAccountId +
                ", targetAccountId=" + targetAccountId +
                ", amount=" + amount +
                ", startDate=" + startDate +
                ", completionDate=" + completionDate +
                '}';
    }


}

