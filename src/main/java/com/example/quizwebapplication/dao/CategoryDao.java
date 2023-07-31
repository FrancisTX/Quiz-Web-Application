package com.example.quizwebapplication.dao;

import com.example.quizwebapplication.domain.Category;
import com.example.quizwebapplication.dto.category.CategoryDTO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDao extends AbstractHibernateDao<Category>{
    public CategoryDao() {
        setClazz(Category.class);
    }

    public List<CategoryDTO> getAllCategoryNames(){
        Session session = this.getCurrentSession();
        String sql = "SELECT new com.example.quizwebapplication.dto.category.CategoryDTO(c.categoryId, c.name) FROM Category c";
        Query<CategoryDTO> query = session.createQuery(sql, CategoryDTO.class);
        return query.getResultList();
    }

    public Category getCategoryById(int id){
        return this.findById(id);
    }
}
