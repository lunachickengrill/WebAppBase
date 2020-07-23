package eu.vrtime.webappbase.web.servlet;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.dom.DOMDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.util.WebUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

public class ApplicationServlet extends FrameworkServlet {

	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(ApplicationServlet.class);

	public ApplicationServlet() {

	}

	@Override
	protected void initFrameworkServlet() throws ServletException {
		super.initFrameworkServlet();
	}

	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Assert.isTrue(request.getMethod().equals("POST"), "only POST requests are allowed");

		String req = retrieveTmngxXmlRequest(request);
		logger.info(req);
		
		String respString = writeResponseAsString();

		response.setContentType(MimeTypeUtils.APPLICATION_XML_VALUE);
		response.setCharacterEncoding("UTF-8");
		response.setStatus(201);
		response.getWriter().write(respString);
		response.getWriter().flush();

	}

	private String retrieveTmngxXmlRequest(HttpServletRequest request) throws IOException {
		String xmlRequest;
		MimeType mimeType = MimeType.valueOf(request.getContentType());

		if (MimeTypeUtils.APPLICATION_XML.isCompatibleWith(mimeType)
				|| MimeTypeUtils.TEXT_XML.isCompatibleWith(mimeType)) {

			String characterEncoding = StringUtils.defaultString(request.getCharacterEncoding(),
					WebUtils.DEFAULT_CHARACTER_ENCODING);
			xmlRequest = StreamUtils.copyToString(request.getInputStream(), Charset.forName(characterEncoding));

		} else if (mimeType.isCompatibleWith(MediaType.APPLICATION_FORM_URLENCODED)) {
			xmlRequest = request.getParameter("tmngxXmlRequest");

			if (xmlRequest == null) {
				throw new IllegalArgumentException(
						"need parameter 'serviceRequest' but got " + request.getParameterMap().keySet());
			}
		} else {
			throw new IllegalArgumentException("unsupported mimetype " + mimeType + " (from" + request.getContentType()
					+ ")." + "Use " + MimeTypeUtils.APPLICATION_XML_VALUE + ", " + MimeTypeUtils.TEXT_XML_VALUE + " or "
					+ MediaType.APPLICATION_FORM_URLENCODED_VALUE + ".");
		}

		Assert.isTrue(!xmlRequest.isEmpty(), "xmlRequest is empty");

		return xmlRequest;
	}
	
	private String writeResponseAsString() throws IOException {
		ObjectMapper mapper = new XmlMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		ResponsePojo pojo = new ResponsePojo();
		pojo.setMessage("Yeah!");
		pojo.setStatus("SUCCESS");
		
		return mapper.writeValueAsString(pojo);
	}

	@JacksonXmlRootElement(localName="webAppBaseResponse")
	public static class ResponsePojo {

		@JacksonXmlProperty
		private String status;

		@JacksonXmlProperty
		private String message;

		public ResponsePojo() {

		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}

}
