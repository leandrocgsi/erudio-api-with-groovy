package br.com.erudio.utils.email

import java.io.File;
import java.io.Serializable;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

class EmailUtils implements Serializable {

	MultiPartEmail getEmailSession(EMailConfigs configs) throws EmailException {
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName(configs.getHostname());
		email.setSmtpPort(configs.getPort());
		email.setAuthentication(configs.getUsername(), configs.getPassword());
		email.setSSL(configs.getSsl());
		email.setFrom(configs.getFrom());
		return email;
	}

	EmailAttachment attachFile(String fileDir) {
		File file = new File(fileDir);
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(file.getPath());
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Attachment");
		attachment.setName(file.getName());
		return attachment;
	}

	def sendSimpleMail(EMailConfigs configs, String to) throws EmailException {
		MultiPartEmail email = setConfigs(configs, to);
		email.send();
	}

	def void sendEmailWithAttachment(EMailConfigs configs, String to, String filePath) throws EmailException {
		MultiPartEmail email = setConfigs(configs, to);
		EmailAttachment attach = attachFile(filePath);
		email.attach(attach);
		email.send();
	}

	def MultiPartEmail setConfigs(EMailConfigs configs, String to) throws EmailException {
		MultiPartEmail email = new MultiPartEmail();
		email = getEmailSession(configs);
		email.setSubject(configs.getSubject());
		email.setMsg(configs.getMessage());
		email.addTo(to);
		return email;
	}
}