package com.example.server.repository;



import java.util.List;

import com.example.server.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcRepository implements ParticipantRepository {

/*    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Employee employee) {
        return jdbcTemplate.update("INSERT INTO participants (name, contact, attending) VALUES(?,?,?)",
        new Object[] { employee.getName(), employee.getContact(), employee.isAttending()});
    }

    @Override
    public int update(Employee employee) {
        return jdbcTemplate.update("UPDATE participants SET name=?, contact=?, attending=? WHERE id=?",
        new Object[] { employee.getId(), employee.getName(), employee.getContact()});
    }

    @Override
    public Employee findById(Long id) {
        try {
            Employee employee = jdbcTemplate.queryForObject("SELECT * FROM participants WHERE id=?",
                BeanPropertyRowMapper.newInstance(Employee.class), id);
            return employee;
          } catch (IncorrectResultSizeDataAccessException e) {
            return null;
          }
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM participants WHERE id=?", id);
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT * from participants", BeanPropertyRowMapper.newInstance(
            Employee.class));
    }

    @Override
    public List<Employee> findByAttending(boolean attending) {
        return jdbcTemplate.query("SELECT * from participants WHERE attending=?",
        BeanPropertyRowMapper.newInstance(Employee.class), attending);
    }

    @Override
    public List<Employee> findByNameContaining(String name) {
        String q = "SELECT * from participants WHERE name LIKE '%" + name + "%'";
    return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Employee.class));
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from participants");
    }
    */
}
