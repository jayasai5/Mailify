package eclipse_plugin.Mailify.core;
import java.awt.*;
import java.awt.datatransfer.*;
public class ConvertCode{
	private StringBuilder sb=null;
	public ConvertCode(String d){
		sb = new StringBuilder();
		sb.append("<html><body>");
		sb.append("<pre style='font-family: Menlo, Monaco, Consolas, \"Courier New\", monospace; font-size: 12pt; background-color:#0d211a; color:#FFFFFF'>");
		PrettifyHighlighter highlighter = new PrettifyHighlighter();
		String highlighted = highlighter.highlight("java", d);
		sb.append(highlighted);
		sb.append("</pre>");
		sb.append("</body></html>");

		Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
		c.setContents(new MyTransferable(d, sb.toString()), null);
	}
	public StringBuilder getSb() {
		return sb;
	}

}