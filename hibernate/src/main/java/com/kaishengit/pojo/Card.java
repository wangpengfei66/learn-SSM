package com.kaishengit.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "FK",strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(name = "property",value = "person"))
    @GeneratedValue(generator = "FK")
    private int id;
    @Basic
    @Column(name = "card_num")
    private Integer cardNum;
    @OneToOne(mappedBy = "card")
    @PrimaryKeyJoinColumn
    private Person person;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Integer getCardNum() {
        return cardNum;
    }

    public void setCardNum(Integer cardNum) {
        this.cardNum = cardNum;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
