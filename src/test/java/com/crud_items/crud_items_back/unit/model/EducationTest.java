package com.crud_items.crud_items_back.unit.model;

import com.crud_items.crud_items_back.model.Education;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class EducationTest {
    @Test
    void testAllArgsConstructorAndGetters() {
        // Arrange: create test data
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 31);
        // Act: create Education object with all arguments
        Education education = new Education(
                1L,
                "Bachelor of Science",
                "University of Example",
                startDate,
                endDate,
                "Studied Computer Science",
                100L);
        // Assert: verify all fields are set correctly
        assertEquals(1L, education.getId());
        assertEquals("Bachelor of Science", education.getDegree());
        assertEquals("University of Example", education.getInstitution());
        assertEquals(startDate, education.getStartDate());
        assertEquals(endDate, education.getEndDate());
        assertEquals("Studied Computer Science", education.getDescription());
        assertEquals(100L, education.getPersonalInfoId());
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        // Arrange: create empty Education and set fields
        Education education = new Education();
        education.setId(2L);
        education.setDegree("Master of Arts");
        education.setInstitution("Institute of Testing");
        education.setStartDate(LocalDate.of(2018, 9, 1));
        education.setEndDate(LocalDate.of(2020, 6, 30));
        education.setDescription("Studied Testing Methodologies");
        education.setPersonalInfoId(200L);
        // Act: (no action needed, just using setters/getters)
        // Assert: verify all fields are set correctly
        assertEquals(2L, education.getId());
        assertEquals("Master of Arts", education.getDegree());
        assertEquals("Institute of Testing", education.getInstitution());
        assertEquals(LocalDate.of(2018, 9, 1), education.getStartDate());
        assertEquals(LocalDate.of(2020, 6, 30), education.getEndDate());
        assertEquals("Studied Testing Methodologies", education.getDescription());
        assertEquals(200L, education.getPersonalInfoId());
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange: create two identical Education objects
        LocalDate date = LocalDate.of(2021, 1, 1);
        Education edu1 = new Education(3L, "PhD", "Test University", date, date, "Research", 300L);
        Education edu2 = new Education(3L, "PhD", "Test University", date, date, "Research", 300L);
        // Act: (no action needed, just comparing)
        // Assert: verify equals and hashCode
        assertEquals(edu1, edu2);
        assertEquals(edu1.hashCode(), edu2.hashCode());
    }

    @Test
    void testToString() {
        // Arrange: create Education object
        Education education = new Education(4L, "Diploma", "College", LocalDate.now(), LocalDate.now(),
                "Diploma course", 400L);
        // Act: call toString()
        String str = education.toString();
        // Assert: verify string contains expected values
        assertTrue(str.contains("Diploma"));
        assertTrue(str.contains("College"));
        assertTrue(str.contains("Diploma course"));
    }
}
