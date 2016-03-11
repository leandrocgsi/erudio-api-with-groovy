package br.com.erudio.entrypoint.v1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import br.com.erudio.repository.interfaces.IReportRepository;

@Controller
@Secured("ROLE_USER")
@RequestMapping("/api/v1/report")
@Api(value = "/report", description = "Building a report in PDF!")
public class ReportEntryPoint {

	@Autowired
	private IReportRepository reportRepository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "Building a report in PDF!", notes = "Building a report in PDF!")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 500, message = "Houston we have a problem") })
	public @ResponseBody HttpEntity<byte[]> makeReport(HttpServletResponse response) throws Exception {
		// See this http://www.benashby.com/spring/2013/10/30/serving-large-files-spring-mvc.html to bigest files
		// File file = reportRepository.makeReport();
		Path path = Paths.get("D:/E-Books/7-habits-ebook.pdf");
		byte[] buffer = Files.readAllBytes(path);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.setContentDispositionFormData("Content-Disposition", "output.pdf");
	    headers.setContentLength(buffer.length);
		return new HttpEntity<byte[]>(buffer, headers);
	}
}