package contestx;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class SWTdialog {

	protected Shell shell;
	private FileDialog f;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public SWTdialog(Shell parent, int style) {
		shell = new Shell();
		f = new FileDialog(parent, style);
	}
	public SWTdialog(int style) {
		shell = new Shell();
		f = new FileDialog(shell, style);
	}
	
	public String open() {
		String s = f.open();
		return s;
	}
}
