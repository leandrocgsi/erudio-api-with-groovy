package br.com.erudio.entrypoint.v1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public void makeReport(HttpServletResponse response) throws Exception {
		//See this http://www.benashby.com/spring/2013/10/30/serving-large-files-spring-mvc.html to bigest files
		// File file = reportRepository.makeReport();
		Path path = Paths.get("D:/E-Books/7-habits-ebook.pdf");
		byte[] buffer = Files.readAllBytes(path);
		 
	    try {
	      InputStream inputStream = new ByteArrayInputStream(buffer);
	      ServletOutputStream outputStream = response.getOutputStream();
	      IOUtils.copy(inputStream, outputStream);
	      
	      response.setContentType("application/pdf");
          response.setHeader("Content-Disposition", "attachment; filename=output.pdf");
          outputStream.flush();
          outputStream.close();
          response.setStatus(200);
	      
	      
	      
	    } catch (IOException ex) {
//	      log.info("Error writing file to output stream. Filename was '{}'", fileName, ex);
	      throw new RuntimeException("IOError writing file to output stream");
	    }
	}
//		//See this http://www.benashby.com/spring/2013/10/30/serving-large-files-spring-mvc.html to bigest files
//		// File file = reportRepository.makeReport();
//		Path path = Paths.get("D:/E-Books/7-habits-ebook.pdf");
//		byte[] buffer = Files.readAllBytes(path);
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(new MediaType("application", "pdf"));
//		headers.setContentDispositionFormData("Content-Disposition", "output.pdf");
////	    header.set("Content-Disposition", "attachment; filename=" + fileName.replace(" ", "_"));
//	    headers.setContentLength(buffer.length);
//		return new HttpEntity<byte[]>(buffer, headers);
//	}
}