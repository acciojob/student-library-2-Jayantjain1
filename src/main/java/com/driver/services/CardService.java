package com.driver.services;

import com.driver.models.Student;
import com.driver.models.Card;
import com.driver.models.CardStatus;
import com.driver.repositories.CardRepository;
import com.driver.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {


    @Autowired
    CardRepository cardRepository;

    @Autowired
    StudentRepository studentRepository;

    public Card createAndReturn(Student student){
        Card card = new Card();
        card.setStudent(student);
        student.setCard(card);
        studentRepository.save(student);
        return card;
    }

    public void deactivateCard(int student_id){
        cardRepository.deactivateCard(student_id, CardStatus.DEACTIVATED.toString());
    }
}
