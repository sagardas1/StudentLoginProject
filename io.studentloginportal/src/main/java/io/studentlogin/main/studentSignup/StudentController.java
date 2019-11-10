package io.studentlogin.main.studentSignup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@RequestMapping(method=RequestMethod.POST,value="/addstudentdetails")
	public BaseResponse addStudentDetails(@RequestBody StudentDetails studentDetails) {
		BaseResponse baseResponse=null;
		try {
			
			 baseResponse=studentService.addStudentDetails(studentDetails);
			 System.out.println("---->"+baseResponse.getStatusCode());
			 System.out.println(baseResponse.getStatusMessage());
			
			
		}catch(Exception e) {System.out.println("exceprion occurs while inserting recordsin db  "+e);}
		return baseResponse;
	}

	@RequestMapping(method=RequestMethod.GET,value="/login/{email}/{password}")
	public BaseResponse studentLogin(@PathVariable String email,@PathVariable String password)
	{
		BaseResponse baseResponse=null;
		
		try {
			baseResponse=studentService.studentLogin(email,password);
		}catch(Exception e) {
			System.out.println("exception occured while login "+e);
		}
		
		return baseResponse;
	}
}
