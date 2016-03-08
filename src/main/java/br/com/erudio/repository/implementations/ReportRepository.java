package br.com.erudio.repository.implementations;

import java.io.File;
import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.com.erudio.repository.interfaces.IReportRepository;
import br.com.erudio.service.reporter.Reporter;


@Repository
public class ReportRepository implements IReportRepository, Serializable{

	private static final long serialVersionUID = 1L;
	
	Reporter reporter = new Reporter(); 

	@Override
	public File makeReport() throws Exception {
		return reporter.makeReport();
	}

	
}