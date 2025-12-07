package com.crud_items.crud_items_back.repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.crud_items.crud_items_back.model.Skill;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SkillRepoImplement implements ISkillRepo {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Skill> skillRowMapper = (rs, rowNum) -> {
        Skill skill = new Skill();
        skill.setId(rs.getLong("id"));
        skill.setName(rs.getString("name"));
        skill.setLevelPercentage(rs.getInt("level_percentage"));
        skill.setIconClass(rs.getString("icon_class"));
        skill.setPersonalInfoId(rs.getLong("personal_info_id"));
        return skill;
    };

    @Override
    public Skill save(Skill skill) {
        if (skill.getId() == null) {
            String insertSql = "INSERT INTO skills (name, level_percentage, icon_class, personal_info_id) VALUES (?, ?, ?, ?)";

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement ps = connection.prepareStatement(insertSql, new String[] { "id" });
                        ps.setString(1, skill.getName());
                        ps.setInt(2, skill.getLevelPercentage());
                        ps.setString(3, skill.getIconClass());
                        ps.setLong(4, skill.getPersonalInfoId());
                        return ps;
                    }, keyHolder);

            Number key = keyHolder.getKey();
            if (key != null) {
                skill.setId(key.longValue());
            } else {
                throw new IllegalStateException("Failed to retrieve generated key for Skill");
            }
        } else {
            String updateSql = "UPDATE skills SET name = ?, level_percentage = ?, icon_class = ?, personal_info_id = ? WHERE id = ?";
            jdbcTemplate.update(updateSql, skill.getName(), skill.getLevelPercentage(), skill.getIconClass(),
                    skill.getPersonalInfoId(), skill.getId());
        }
        return skill;
    }

    @Override
    public Optional<Skill> findById(Long id) {
        String query = "SELECT * FROM skills WHERE id = ?";
        try {
            Skill skill = jdbcTemplate.queryForObject(query, skillRowMapper, id);
            return Optional.ofNullable(skill);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Skill> findAll() {
        String sql = "SELECT * FROM skills";
        return jdbcTemplate.query(sql, skillRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String query = "DELETE FROM skills WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public List<Skill> findByPersonId(Long personId) {
        String query = "SELECT * FROM skills WHERE personal_info_id = ?";
        return jdbcTemplate.query(query, skillRowMapper, personId);
    }

}
