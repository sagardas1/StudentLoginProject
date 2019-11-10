package io.studentlogin.main.studentSignup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	@Autowired
	StudentRepository studentRepository;

	public BaseResponse addStudentDetails(StudentDetails st) throws Exception {
		BaseResponse baseResponse = null;

		try {
			baseResponse = new BaseResponse();
			if (st.getPassword().equals(st.getConfirmPassword())) 
			{

				StudentDetails details = studentRepository.getStudentDetailByNumber(st.getContactNumber());
			
				if (details == null) {
					studentRepository.save(st);

					baseResponse.setStatusCode(ResponseConstants.SUCCESS_CODE);
					baseResponse.setStatusMessage(ResponseConstants.SUCCESS_MESSAGE);
				} else {
					baseResponse.setStatusCode(ResponseConstants.FAILED_CODE);
					baseResponse.setStatusMessage("you already hav an account");
				}

			}else {
				baseResponse.setStatusCode(ResponseConstants.FAILED_CODE);
				baseResponse.setStatusMessage("password didnt match");
			}
		} catch (Exception e) {
			System.out.println("exceprion occurs while inserting recordsin db  " + e);
		}

		return baseResponse;
	}

	public BaseResponse studentLogin(String email, String password) {
		BaseResponse baseResponse=null;
	try {
		baseResponse =new BaseResponse();
	StudentDetails studentDetails=studentRepository.findByUserName(email,password);
	if(studentDetails!=null) {
		baseResponse.setStatusCode(ResponseConstants.SUCCESS_CODE);
		baseResponse.setStatusMessage(ResponseConstants.SUCCESS_MESSAGE);
	}else {
		baseResponse.setStatusCode(ResponseConstants.FAILED_CODE);
		baseResponse.setStatusMessage("Dont have login,please sign up first for log in.");
	}
		
	}catch(Exception e) {
		System.out.println("exception occured while login "+e);
	}
		return baseResponse;
	}

}
