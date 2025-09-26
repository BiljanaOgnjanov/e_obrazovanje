package com.eobrazovanje.eobrazovanje_api.examBookings;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.eobrazovanje.eobrazovanje_api.examBookings.dto.CreateExamBookingDto;
import com.eobrazovanje.eobrazovanje_api.examBookings.dto.ExamBookingDto;
import com.eobrazovanje.eobrazovanje_api.examBookings.dto.GradeDto;
import com.eobrazovanje.eobrazovanje_api.exams.Exam;
import com.eobrazovanje.eobrazovanje_api.exams.ExamRepository;
import com.eobrazovanje.eobrazovanje_api.exceptions.ResourceNotFoundException;
import com.eobrazovanje.eobrazovanje_api.finance.FinancialCardService;
import com.eobrazovanje.eobrazovanje_api.finance.TransactionType;
import com.eobrazovanje.eobrazovanje_api.studentProfiles.StudentProfile;
import com.eobrazovanje.eobrazovanje_api.studentProfiles.StudentProfileRepository;

@Service
public class ExamBookingService {
    private final ExamBookingRepository examBookingRepository;
    private final ExamRepository examRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final FinancialCardService financialCardService;

    public ExamBookingService(
        ExamBookingRepository examBookingRepository,
        ExamRepository examRepository,
        StudentProfileRepository studentProfileRepository,
        FinancialCardService financialCardService
    ) {
        this.examBookingRepository = examBookingRepository;
        this.examRepository = examRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.financialCardService = financialCardService;
    }

    public ExamBookingDto bookExam(CreateExamBookingDto data) {
        Exam exam = examRepository.findById(data.examId())
            .orElseThrow(() -> new ResourceNotFoundException("Exam not found."));
        
        StudentProfile student = studentProfileRepository.findById(data.studentId())
            .orElseThrow(() -> new ResourceNotFoundException("Student not found."));

        if (examBookingRepository.findByExamIdAndStudentId(data.examId(), data.studentId()) != null) {
            throw new IllegalStateException("Student already booked this exam.");
        }

        financialCardService.withdraw(data.studentId(), 200.0, TransactionType.EXAM_FEE);

        ExamBooking booking = ExamBooking.builder()
            .exam(exam)
            .student(student)
            .status(ExamBookingStatus.BOOKED)
            .build();

        return toDto(examBookingRepository.save(booking));
    }

    public void cancelBooking(UUID bookingId, UUID studentId) {
        ExamBooking booking = examBookingRepository.findById(bookingId)
            .orElseThrow(() -> new ResourceNotFoundException("Booking not found."));

        if (!booking.getStudent().getId().equals(studentId)) {
            throw new ResourceNotFoundException("Booking not found.");
        }

        if (booking.getExam().getExamDate().toLocalDate().isBefore(LocalDate.now())) {
            throw new IllegalStateException("Cannot cancel after exam date.");
        }

        financialCardService.deposit(studentId, 200.0, TransactionType.REFUND);

        booking.setStatus(ExamBookingStatus.CANCELLED);

        examBookingRepository.save(booking);
    }

    public ExamBookingDto gradeExamBooking(UUID bookingId, GradeDto data) {
        ExamBooking booking = examBookingRepository.findById(bookingId)
            .orElseThrow(() -> new ResourceNotFoundException("Booking not found."));
        
        if (booking.getStatus() == ExamBookingStatus.CANCELLED) {
            throw new IllegalStateException("Cannot grade a cancelled booking.");
        }

        int total = data.test1() + data.test2() + data.attendance();
        int grade = calculateGrade(total);
        ExamBookingStatus status = grade >= 6 ? ExamBookingStatus.PASSED : ExamBookingStatus.FAILED;

        booking.setTest1(data.test1());
        booking.setTest2(data.test2());
        booking.setAttendance(data.attendance());
        booking.setTotalScore(total);
        booking.setFinalGrade(grade);
        booking.setStatus(status);

        return toDto(examBookingRepository.save(booking));
    }

    public List<ExamBookingDto> getStudentBookings (UUID studentId) {
        return examBookingRepository.findByStudentId(studentId)
            .stream()
            .map(this::toDto)
            .toList();
    }

    public List<ExamBookingDto> getBookingsForExam (UUID examId) {
        return examBookingRepository.findByExamId(examId)
            .stream()
            .map(this::toDto)
            .toList();
    }

    private int calculateGrade(int total) {
        if (total >= 91) {
            return 10;
        } else if (total >= 81) {
            return 9;
        } else if (total >= 71) {
            return 8;
        } else if (total >= 61) {
            return 7;
        } else if (total >= 51) {
            return 6;
        } else {
            return 5;
        }
    }

    private ExamBookingDto toDto(ExamBooking booking) {
        return new ExamBookingDto(
            booking.getId(),
            booking.getExam().getId(),
            booking.getStudent().getId(),
            booking.getStatus(),
            booking.getTest1(),
            booking.getTest2(),
            booking.getAttendance(),
            booking.getTotalScore(),
            booking.getFinalGrade(),
            booking.getCreatedAt(),
            booking.getUpdatedAt()
        );
    }
}
