package com.example.quizwebapplication.dao;

import com.example.quizwebapplication.domain.Choice;
import com.example.quizwebapplication.dto.choice.ChoiceDTO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ChoiceDao extends AbstractHibernateDao<Choice> {
    public ChoiceDao() {
        setClazz(Choice.class);
    }

    public void saveChoice(Choice choice) {
        this.add(choice);
    }

    public Choice getChoiceById(int id) {
        return this.findById(id);
    }

    public ChoiceDTO getChoiceDTOById(int id) {
        Session session = this.getCurrentSession();
        String sql = "SELECT new com.example.quizwebapplication.dto.choice.ChoiceDTO(c.choiceId, c.description, c.isCorrect) FROM Choice c WHERE c.choiceId = :id";
        Query<ChoiceDTO> query = session.createQuery(sql, ChoiceDTO.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }
}
