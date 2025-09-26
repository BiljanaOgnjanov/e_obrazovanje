package com.eobrazovanje.eobrazovanje_api.examBookings;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eobrazovanje.eobrazovanje_api.examBookings.dto.CreateExamBookingDto;
import com.eobrazovanje.eobrazovanje_api.examBookings.dto.ExamBookingDto;
import com.eobrazovanje.eobrazovanje_api.examBookings.dto.GradeDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bookings")
public class ExamBookingController {
    @Autowired
    private ExamBookingService examBookingService;

    @PostMapping
    public ResponseEntity<ExamBookingDto> bookExam(@Valid @RequestBody CreateExamBookingDto data) {
        return ResponseEntity.ok(examBookingService.bookExam(data));
    }

    @DeleteMapping("/{bookingId}/students/{studentId}")
    public ResponseEntity<Void> cancelBooking(@PathVariable UUID bookingId,
                                              @PathVariable UUID studentId) {
        examBookingService.cancelBooking(bookingId, studentId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{bookingId}/grade")
    public ResponseEntity<ExamBookingDto> gradeExamBooking(@PathVariable UUID bookingId,
                                                   @Valid @RequestBody GradeDto data) {
        return ResponseEntity.ok(examBookingService.gradeExamBooking(bookingId, data));
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<List<ExamBookingDto>> getStudentBookings(@PathVariable UUID studentId) {
        return ResponseEntity.ok(examBookingService.getStudentBookings(studentId));
    }

    @GetMapping("/exams/{examId}")
    public ResponseEntity<List<ExamBookingDto>> getBookingsForExam(@PathVariable UUID examId) {
        return ResponseEntity.ok(examBookingService.getBookingsForExam(examId));
    }
}
