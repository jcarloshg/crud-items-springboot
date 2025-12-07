package com.crud_items.crud_items_back.repository;

import java.security.Key;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.crud_items.crud_items_back.model.Experience;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ExperiencePostgreSql implements IExperienceRepo {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Experience> experienceRowMapper = (rs, rowNum) -> {
        Experience experience = new Experience();
        experience.setId(rs.getLong("id"));
        experience.setJobTitle(rs.getString("job_title"));
        experience.setCompanyName(rs.getString("company_name"));
        experience.setStartDate(rs.getObject("start_date", java.time.LocalDate.class));
        experience.setEndDate(rs.getObject("end_date", java.time.LocalDate.class));
        experience.setDescription(rs.getString("description"));
        experience.setPersonalInfoId(rs.getLong("personal_info_id"));
        return experience;
    };

    @Override
    public Experience save(Experience experience) {
        if (experience.getId() == null) {
            String insertSql = "INSERT INTO experience (job_title, company_name, start_date, end_date, description, personal_info_id) VALUES (?, ?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(insertSql, new String[] { "id" });
                ps.setString(1, experience.getJobTitle());
                ps.setString(2, experience.getCompanyName());
                ps.setObject(3, experience.getStartDate());
                ps.setObject(4, experience.getEndDate());
                ps.setString(5, experience.getDescription());
                ps.setLong(6, experience.getPersonalInfoId());
                return ps;
            }, keyHolder);

            Long id = keyHolder.getKey().longValue();
            experience.setId(id);

        } else {
            String updateSql = "UPDATE experience SET job_title = ?, company_name = ?, start_date = ?, end_date = ?, description = ?, personal_info_id = ? WHERE id = ?";
            jdbcTemplate.update(updateSql, experience.getJobTitle(), experience.getCompanyName(),
                    experience.getStartDate(), experience.getEndDate(), experience.getDescription(),
                    experience.getPersonalInfoId(), experience.getId());
        }
        return experience;
    }

    @Override
    public Optional<Experience> findById(Long id) {
        String sql = "SELECT * FROM experience WHERE id = ?";
        try {
            Experience result = jdbcTemplate.queryForObject(sql, experienceRowMapper, id);
            return Optional.ofNullable(result);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Experience> findAll() {
        String sql = "SELECT * FROM experience";
        return jdbcTemplate.query(sql, experienceRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM experience WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Experience> findByPersonId(Long personId) {
        String sql = "SELECT * FROM experience WHERE personal_info_id = ?";
        return jdbcTemplate.query(sql, experienceRowMapper, personId);
    }

}
