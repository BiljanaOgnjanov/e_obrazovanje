package com.eobrazovanje.eobrazovanje_api.examBookings;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamBookingRepository extends JpaRepository<ExamBooking, UUID> {
    List<ExamBooking> findByStudentId(UUID studentId);

    List<ExamBooking> findByExamId(UUID examId);

    ExamBooking findByExamIdAndStudentId(UUID examId, UUID studentId);
}
