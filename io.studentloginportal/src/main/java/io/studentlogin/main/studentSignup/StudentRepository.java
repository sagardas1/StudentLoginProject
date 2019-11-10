package io.studentlogin.main.studentSignup;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
@Transactional
public interface StudentRepository extends CrudRepository<StudentDetails,String> {
	@Transactional
	@Query(value="select * from student_details where contact_number=:contactNumber",nativeQuery=true)
	StudentDetails getStudentDetailByNumber(String contactNumber);
	
	@Transactional
	@Query(value="select * from student_details where email=:email AND password=:password",nativeQuery=true)
	StudentDetails findByUserName(String email, String password);


}
