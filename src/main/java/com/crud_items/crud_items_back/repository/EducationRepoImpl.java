package com.crud_items.crud_items_back.repository;

import java.security.Key;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.crud_items.crud_items_back.model.Education;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EducationRepoImpl implements IEducationRepo {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Education> educationRowMapper = (rs, rowNum) -> {
        Education education = new Education();
        education.setId(rs.getLong("id"));
        education.setDegree(rs.getString("degree"));
        education.setInstitution(rs.getString("institution"));
        education.setStartDate(rs.getObject("start_date", java.time.LocalDate.class));
        education.setEndDate(rs.getObject("end_date", java.time.LocalDate.class));
        education.setDescription(rs.getString("description"));
        education.setPersonalInfoId(rs.getLong("personal_info_id"));
        return education;
    };

    @Override
    public Education save(Education education) {
        if (education.getId() == null) {
            String insertSql = "INSERT INTO educations (degree, institution, start_date, end_date, description, personal_info_id) VALUES (?, ?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement ps = connection.prepareStatement(insertSql, new String[] { "id" });
                        ps.setString(1, education.getDegree());
                        ps.setString(2, education.getInstitution());
                        ps.setObject(3, education.getStartDate());
                        ps.setObject(4, education.getEndDate());
                        ps.setString(5, education.getDescription());
                        ps.setLong(6, education.getPersonalInfoId());
                        return ps;
                    }, keyHolder);
            Long id = keyHolder.getKey().longValue();
            education.setId(id);
        } else {
            String updateSql = "UPDATE educations SET degree = ?, institution = ?, start_date = ?, end_date = ?, description = ?, personal_info_id = ? WHERE id = ?";
            jdbcTemplate.update(updateSql,
                    education.getDegree(),
                    education.getInstitution(),
                    education.getStartDate(),
                    education.getEndDate(),
                    education.getDescription(),
                    education.getPersonalInfoId(),
                    education.getId());
        }
        return education;
    }

    @Override
    public Optional<Education> findById(Long id) {
        String query = "SELECT * FROM educations WHERE id = ?";
        try {
            Education results = jdbcTemplate.queryForObject(query, educationRowMapper, id);
            return Optional.ofNullable(results);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Education> findAll() {
        String sql = "SELECT * FROM educations";
        return jdbcTemplate.query(sql, educationRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM educations WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Education> findByPersonId(Long personId) {
        String query = "SELECT * FROM educations WHERE personal_info_id = ?";
        return jdbcTemplate.query(query, educationRowMapper, personId);
    }

}
