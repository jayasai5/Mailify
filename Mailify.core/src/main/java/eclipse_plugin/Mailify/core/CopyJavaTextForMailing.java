package eclipse_plugin.Mailify.core;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.Toolkit;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class CopyJavaTextForMailing extends AbstractHandler{


	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		String text=null;
		IWorkbench wb= PlatformUI.getWorkbench();
		IWorkbenchWindow activeWorkBench=wb.getActiveWorkbenchWindow();
		ISelection selection=activeWorkBench.getActivePage().getSelection();
		if (activeWorkBench!=null) {
			if (selection!=null && selection instanceof ITextSelection ) {
				//Use text selection to set initial search pattern.
				text = ((ITextSelection)selection).getText();

			} 
		}
		ConvertCode cd=new ConvertCode(text);
		MyTransferable tr=new MyTransferable(text, cd.getSb().toString());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(tr, new ClipboardOwner() {

			@Override
			public void lostOwnership(Clipboard clipboard, Transferable contents) {
				// TODO Auto-generated method stub

			}
		});
		return null;
	}
} 