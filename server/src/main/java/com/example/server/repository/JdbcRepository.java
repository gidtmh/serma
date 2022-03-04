package com.example.server.repository;



import java.util.List;

import com.example.server.model.Participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcRepository implements ParticipantRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Participant participant) {
        return jdbcTemplate.update("INSERT INTO participants (name, contact, attending) VALUES(?,?,?)",
        new Object[] { participant.getName(), participant.getContact(), participant.isAttending()});
    }

    @Override
    public int update(Participant participant) {
        return jdbcTemplate.update("UPDATE participants SET name=?, contact=?, attending=? WHERE id=?",
        new Object[] { participant.getId(), participant.getName(), participant.getContact()});
    }

    @Override
    public Participant findById(Long id) {
        try {
            Participant participant = jdbcTemplate.queryForObject("SELECT * FROM participants WHERE id=?",
                BeanPropertyRowMapper.newInstance(Participant.class), id);
            return participant;
          } catch (IncorrectResultSizeDataAccessException e) {
            return null;
          }
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM participants WHERE id=?", id);
    }

    @Override
    public List<Participant> findAll() {
        return jdbcTemplate.query("SELECT * from participants", BeanPropertyRowMapper.newInstance(Participant.class));
    }

    @Override
    public List<Participant> findByAttending(boolean attending) {
        return jdbcTemplate.query("SELECT * from participants WHERE attending=?",
        BeanPropertyRowMapper.newInstance(Participant.class), attending);
    }

    @Override
    public List<Participant> findByNameContaining(String name) {
        String q = "SELECT * from participants WHERE name LIKE '%" + name + "%'";
    return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Participant.class));
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from participants");
    }
    
}
