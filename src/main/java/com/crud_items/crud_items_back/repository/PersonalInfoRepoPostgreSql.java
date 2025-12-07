package com.crud_items.crud_items_back.repository;

import java.sql.PreparedStatement;
// language
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.core.JdbcTemplate;
// framework
import org.springframework.stereotype.Repository;
// domain
import com.crud_items.crud_items_back.model.PersonalInfo;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PersonalInfoRepoPostgreSql implements IPersionalInfoRespository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<PersonalInfo> personalInfoRowMapper = (rs, rowNum) -> {
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setId(rs.getLong("id"));
        personalInfo.setFirstName(rs.getString("first_name"));
        personalInfo.setLastName(rs.getString("last_name"));
        personalInfo.setTitle(rs.getString("title"));
        personalInfo.setProfileDescription(rs.getString("profile_description"));
        personalInfo.setProfileImageUrl(rs.getString("profile_image_url"));
        personalInfo.setYearsOfExperience(rs.getInt("years_of_experience"));
        personalInfo.setEmail(rs.getString("email"));
        personalInfo.setPhone(rs.getString("phone"));
        personalInfo.setLinkedinUrl(rs.getString("linkedin_url"));
        personalInfo.setGithubUrl(rs.getString("github_url"));
        return personalInfo;
    };

    @Override
    public List<PersonalInfo> findAll() {
        String sql = "SELECT * FROM personal_info";
        return jdbcTemplate.query(sql, personalInfoRowMapper);
    }

    // @Override
    // public Optional<PersonalInfo> findById(Long id) {
    // String query = "SELECT * FROM personal_info WHERE id = ?";
    // List<PersonalInfo> results = jdbcTemplate.query(query, personalInfoRowMapper,
    // id);
    // if (!results.isEmpty()) {
    // return Optional.of(results.get(0));
    // } else {
    // return Optional.empty();
    // }
    // }

    @Override
    public Optional<PersonalInfo> findById(Long id) {
        String query = "SELECT * FROM personal_info WHERE id = ?";
        try {

            // Use jdbcTemplate to execute the query and map the result to a PersonalInfo
            // object
            // queryForObject returns a single object or throws an exception if not found
            // personalInfoRowMapper maps the ResultSet to a PersonalInfo instance
            // id is passed as the query parameter
            PersonalInfo result = jdbcTemplate.queryForObject(query, personalInfoRowMapper, id);
            // Wrap the result in an Optional (will be null if not found)
            return Optional.ofNullable(result);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public PersonalInfo save(PersonalInfo personalInfo) {
        if (personalInfo.getId() == null) {
            // SQL statement to insert a new record into the personal_info table
            String sql = "INSERT INTO personal_info (first_name, last_name, title, profile_description, profile_image_url, years_of_experience, email, phone, linkedin_url, github_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // KeyHolder to retrieve the generated primary key (id) after insertion
            KeyHolder keyHolder = new GeneratedKeyHolder();

            // Execute the insert statement using JdbcTemplate, providing a
            // PreparedStatement with parameters
            jdbcTemplate.update(connection -> {
                // Prepare the statement and specify that the generated "id" column should be
                // returned
                PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
                ps.setString(1, personalInfo.getFirstName());
                ps.setString(2, personalInfo.getLastName());
                ps.setString(3, personalInfo.getTitle());
                ps.setString(4, personalInfo.getProfileDescription());
                ps.setString(5, personalInfo.getProfileImageUrl());
                ps.setInt(6, personalInfo.getYearsOfExperience());
                ps.setString(7, personalInfo.getEmail());
                ps.setString(8, personalInfo.getPhone());
                ps.setString(9, personalInfo.getLinkedinUrl());
                ps.setString(10, personalInfo.getGithubUrl());
                return ps;
            }, keyHolder);

            // Retrieve the generated id from the KeyHolder and set it to the PersonalInfo
            // object
            Long id = Objects.requireNonNull(keyHolder.getKey()).longValue();
            personalInfo.setId(id);

            // Return the PersonalInfo object (note: id is not set here)
            return personalInfo;
        } else {
            String sql = "UPDATE personal_info SET first_name = ?, last_name = ?, title = ?, profile_description = ?, profile_image_url = ?, years_of_experience = ?, email = ?, phone = ?, linkedin_url = ?, github_url = ? WHERE id = ?";
            jdbcTemplate.update(sql,
                    personalInfo.getFirstName(),
                    personalInfo.getLastName(),
                    personalInfo.getTitle(),
                    personalInfo.getProfileDescription(),
                    personalInfo.getProfileImageUrl(),
                    personalInfo.getYearsOfExperience(),
                    personalInfo.getEmail(),
                    personalInfo.getPhone(),
                    personalInfo.getLinkedinUrl(),
                    personalInfo.getGithubUrl(),
                    personalInfo.getId());
            return personalInfo;
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM personal_info WHERE id = ?";
        jdbcTemplate.update(sql, id);

    }

}
