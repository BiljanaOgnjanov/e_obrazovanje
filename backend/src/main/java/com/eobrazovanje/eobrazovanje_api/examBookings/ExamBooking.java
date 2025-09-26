package com.eobrazovanje.eobrazovanje_api.examBookings;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.eobrazovanje.eobrazovanje_api.courses.Course;
import com.eobrazovanje.eobrazovanje_api.exams.Exam;
import com.eobrazovanje.eobrazovanje_api.professorProfiles.ProfessorProfile;
import com.eobrazovanje.eobrazovanje_api.studentProfiles.StudentProfile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="exam_bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder 
public class ExamBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentProfile student;

    @Enumerated(EnumType.STRING)
    private ExamBookingStatus status;

    private Integer test1;
    private Integer test2; 
    private Integer attendance; 
    private Integer totalScore;
    private Integer finalGrade;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}