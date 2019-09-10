package com.davidlutta.PersonApi.dao;

import com.davidlutta.PersonApi.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.*;

@Repository("postgres")
public class PersonDataAccessService extends JdbcDaoSupport implements PersonDAO {
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public void insertPerson(UUID id, Person person) {
        String sql = "INSERT INTO mtu "
                +"(id, name, age, job) VALUES (?, ?, ?, ?)";
        assert getJdbcTemplate() != null;
        getJdbcTemplate().update(sql, id,person.getName(),person.getAge(),person.getJob());
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        String sql = "SELECT * FROM mtu WHERE id = ?";
        assert getJdbcTemplate() != null;
        return Optional.ofNullable(getJdbcTemplate().queryForObject(sql, new Object[]{id},
                (rs, rowNum) -> {
                    Person p = new Person();
                    UUID newId = UUID.fromString(rs.getString("id"));
                    p.setId(newId);
                    p.setName(rs.getString("name"));
                    p.setAge(rs.getInt("age"));
                    p.setJob(rs.getString("job"));
                    return p;
                }));
    }

    @Override
    public List<Person> selectAllPeople() {
        String sql = "SELECT * FROM mtu";
        assert getJdbcTemplate() != null;
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<Person> result = new ArrayList<Person>();
        for (Map<String, Object> row: rows){
            Person p = new Person();
            p.setId((UUID)row.get("id"));
            p.setName((String)row.get("name"));
            p.setAge((int)row.get("age"));
            p.setJob((String)row.get("job"));
            result.add(p);
        }
        return result;
    }

    @Override
    public void deletePersonById(UUID id) {
        String sql = "DELETE FROM mtu WHERE id = ?";
        assert getJdbcTemplate() != null;
        getJdbcTemplate().update(sql,id);
    }

    @Override
    public void updatePersonById(UUID id, Person person) {
        String sql = "UPDATE  mtu "+
                "SET name=?, age=?, job=?"+
                " WHERE id = ?";
        assert getJdbcTemplate() != null;
        getJdbcTemplate().update(sql,person.getName(),person.getAge(),person.getJob(), id);
    }

    @Override
    public int getTotalNumberOfPeople() {
        String sql = "SELECT COUNT(*) FROM mtu";
        assert getJdbcTemplate() != null;
        return getJdbcTemplate().queryForObject(sql,Integer.class);
    }
}
