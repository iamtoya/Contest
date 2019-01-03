package contestx;
import java.io.File;

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
		String[] stringarray = {"*.ser", "*.csv"};
		f.setFilterExtensions(stringarray);
		String s = f.open();
		return s;
	}
	public File setFile() {
		String[] stringarray = {"*.png", "*.jpg"};
		f.setFilterExtensions(stringarray);
		String s = f.open();
		File file = null;
		if(s != null) {
			file = new File(s);
		}
		return file;
	}
}
