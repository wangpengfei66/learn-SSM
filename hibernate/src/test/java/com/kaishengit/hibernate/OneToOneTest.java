package com.kaishengit.hibernate;

import com.kaishengit.pojo.Card;
import com.kaishengit.pojo.Person;
import org.junit.Test;

public class OneToOneTest extends BaseTest {

    @Test
    public void save() {
        Person person = new Person();
        person.setPersonName("王老ba");

        Card card = new Card();
        card.setCardNum(90988);

        card.setPerson(person);

        session.save(person);
        session.save(card);
    }

    @Test
    public void find() {
        Person person = (Person) session.get(Person.class,2);
        Card card = person.getCard();
        System.out.println(person.getPersonName() + " -> " + card.getCardNum() );
    }
    @Test
    public void find2() {
        Card card = (Card) session.get(Card.class,2);
        Person person = card.getPerson();
        System.out.println(person.getPersonName() + " -> " + card.getCardNum() );
    }

    @Test
    public void delete() {
        Person person = (Person) session.get(Person.class,3);
        session.delete(person);
    }
}
