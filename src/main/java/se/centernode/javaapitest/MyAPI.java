package se.centernode.javaapitest;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import se.centernode.javaapitest.entity.Statement;
import se.centernode.javaapitest.entity.StatementLink;
import se.centernode.javaapitest.enums.LinkType;
import se.centernode.javaapitest.enums.State;
import se.centernode.javaapitest.enums.StatementType;
import se.centernode.javaapitest.repository.StatementLinkService;
import se.centernode.javaapitest.repository.StatementService;

@RestController
@Component
@AllArgsConstructor
public class MyAPI {
//	List<Statement> list = new ArrayList<>();

	private ObjectMapper objectMapper;
	private StatementService statementService;
	private StatementLinkService statementLinkService;

	public MyAPI() {
		objectMapper = new ObjectMapper();
		
		System.out.println("API created");
		Statement s1 = new Statement(StatementType.STATEMENT, "Anders_Orback", State.UNRESOLVED, "All grass is green.");
		statementService.save(s1);
		
		statementService.addQuestion(s1.getId(), "Anders_Orback", "Green is a wide range of colors. Does this need to be better defined?");
		
		statementService.addQuestion(s1.getId(), "Anders_Orback", "What Is the definition of 'grass'?");

		statementService.updateAndSave(s1, "Most common grass color is green.");

	}

//	@GetMapping("/api")
//	public String getMyData() {
//		List<MyObject> list = new ArrayList<>();
//		list.add(new MyObject("Anders", "My Test"));
//		list.add(new MyObject("Kalle", "Testsr också"));
//		try {
//			return objectMapper.writeValueAsString(list);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "Error";
//	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping("/api/statements")
	public String getStates() {

		try {
			return objectMapper.writeValueAsString(statementService.findAll());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Error";
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping("/api/statement")
	public String getState() {
		try {
			return objectMapper.writeValueAsString(
					new Statement(StatementType.STATEMENT, "Anders_Orback", State.UNRESOLVED, "All grass is green."));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Error";
	}

}
