package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import no.knowit.rfid.TagListener;
import no.knowit.couchdb.KaffeHenting;
import java.text.SimpleDateFormat;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  private org.apache.jasper.runtime.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.apache.jasper.runtime.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<META HTTP-EQUIV=\"refresh\" CONTENT=\"1\">\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("\t<head>\n");
      out.write("\t\t<title>blame it</title>\n");
      out.write("\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />\n");
      out.write("\t\t<style type=\"text/css\">\n");
      out.write("\t\t\t<!--\n");
      out.write("body {\n");
      out.write("\tmargin-left: 30px;\n");
      out.write("\tmargin-right: 30px;\n");
      out.write("\tfont-size: 35px;\n");
      out.write("}\n");
      out.write("-->\n");
      out.write("</style>\n");
      out.write("\t</head>\n");
      out.write("\t<body background=\"BG_ALL.jpg\">\n");
      out.write("\t<div style=\"clear:left; height:170px\">\n");
      out.write("\t<h1 style=\"float:left\">\n");
      out.write("\t\t<span style=\"background-color:#B22222; padding:10px 0 5px 0; color:white; font-family:times; font-size: 50px\">&nbsp;&nbsp;blame&nbsp;it&nbsp;&nbsp;</span>\n");
      out.write("\t</h1>\n");
      out.write("\t<h3 style=\"float:right;\">Totalt antall kopper: ");
      out.print( TagListener.antallKopperTotalt() );
      out.write("</h3><br/>\n");
      out.write("\t</div>\n");
      out.write("\t\t<table cellpadding=\"5px\" >\n");
      out.write("\t\t\t<tr height=\"0px\">\n");
      out.write("\t\t\t\t<th align=\"left\" width=\"20%\"></th>\n");
      out.write("\t\t\t\t<th align=\"left\" width=\"50%\"></th>\n");
      out.write("\t\t\t\t<th width=\"10%\"></th>\n");
      out.write("\t\t\t\t<th width=\"20%\"></th>\n");
      out.write("\t\t\t</tr>\n");
      out.write("\t\t\t<tr><td colspan=\"4\"><hr/></td></tr>\n");
      out.write("\t\t\t");
  SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
				for (int i = 0; i < TagListener.getKaffeDrikkere().size(); i++) {
				KaffeHenting kh =  TagListener.getKaffeDrikkere().get(i);
			
      out.write("\n");
      out.write("\t\t\t<tr>\n");
      out.write("\t\t\t\t<td><img height=\"90\" width=\"100\" src=\" ");
      out.print( kh.getPerson().getPhoto());
      out.write("\" /></td>\n");
      out.write("\t\t\t\t<td style=\"font-weight:bold;\">");
      out.print( kh.getPerson().getName());
      out.write(" </td>\n");
      out.write("\t\t\t\t<td align=\"center\">");
      out.print( format.format(kh.getSisteTidspunkt()) );
      out.write("</td>\n");
      out.write("\t\t\t\t<td align=\"center\">");
      out.print( kh.getAntallKaffe() );
      out.write(" kopp");
 if (kh.getAntallKaffe() > 1) out.print("er"); 
      out.write("  </td>\n");
      out.write("\t\t\t</tr>\n");
      out.write("\t\t\t");
 } 
      out.write("\n");
      out.write("\t\t</table>\n");
      out.write("\t</body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
