package miu.edu.attendance.service;

import miu.edu.attendance.domain.Student;
import miu.edu.attendance.dto.StudentDTOResponse;
import miu.edu.attendance.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional (readOnly = true)
    @Override
    public List<StudentDTOResponse> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTOResponse> studentDTOResponses = students.stream().map(student ->
                modelMapper.map(student, StudentDTOResponse.class)).collect(Collectors.toList());
        return studentDTOResponses;

    }

    @Transactional (readOnly = true)
    @Override
    public List<Student> getAllStudentinfo() {
        List<Student> students = studentRepository.findAll();
        return students;
    }


    @Override
    public boolean addStudent(StudentDTOResponse studentDTOResponse) {
        String barcodeId = null;
        String studentID = null;
        Student student = modelMapper.map(studentDTOResponse, Student.class);
        Student student1 = studentRepository.save(student);
        if (student1.getBarcodeId() == null && student1.getStudentId() != null) {
            barcodeId = student1.getFirstName().substring(0, 1).toUpperCase() + "" +
                    student1.getLastName().substring(0, 1).toUpperCase() + student1.getId() + "" + student1.getStudentId();
            studentID = student1.getId() + "" + student1.getStudentId();
            student1.setBarcodeId(barcodeId);
            student1.setStudentId(studentID);
            studentRepository.save(student1);
            return true;
        }
        return false;

    }



    @Override
    public boolean deleteStudentByID(Long id) {

        if( studentRepository.findStudentById(id)!=null){
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public StudentDTOResponse findStudentByID(Long id) {
      Student student=  studentRepository.findStudentById(Long.valueOf(id));
        if(student!=null){
            return (modelMapper.map(student, StudentDTOResponse.class));
        }
        return null;
    }

    @Override
    public boolean updateStudent(StudentDTOResponse student) {
        Student s = studentRepository.findStudentById(student.getId());
        if (s != null) {
            s.setStudentId(student.getStudentId());
            s.setFirstName(student.getFirstName());
            s.setLastName(student.getLastName());
            studentRepository.save(s);
            return true;
        }
        return false;
    }
}


