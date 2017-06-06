package eclipse_plugin.Mailify.core;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MyTransferable implements Transferable {
	private static ArrayList<DataFlavor> MyFlavors = new ArrayList<DataFlavor>();
	private String plain = null;
	private String html = null;

	static {
		try {
			for (String m : new String[]{"text/plain", "text/html"}) {
				MyFlavors.add(new DataFlavor(m + ";class=java.lang.String;charset=utf-8"));
				MyFlavors.add(new DataFlavor(m + ";class=java.io.Reader;charset=utf-8"));
				MyFlavors.add(new DataFlavor(m + ";class=java.io.InputStream;charset=utf-16"));
			}
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public MyTransferable(String plain, String html) {
		this.plain = plain;
		this.html = html;
	}

	public DataFlavor[] getTransferDataFlavors() {
		return MyFlavors.toArray(new DataFlavor[MyFlavors.size()]);
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return MyFlavors.contains(flavor);
	}

	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
		String s = null;
		if (flavor.getMimeType().contains("text/plain")) {
			s = plain;
		}
		else if (flavor.getMimeType().contains("text/html")) {
			s = html;
		}
		if (s != null) {
			if (String.class.equals(flavor.getRepresentationClass())) {
				return s;
			}
			else if (Reader.class.equals(flavor.getRepresentationClass())) {
				return new StringReader(s);
			}
			else if (InputStream.class.equals(flavor.getRepresentationClass())) {
				return new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
			}
		}
		throw new UnsupportedFlavorException(flavor);
	}
}

