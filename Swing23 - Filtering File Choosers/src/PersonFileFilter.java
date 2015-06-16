import java.io.File;

import javax.swing.filechooser.FileFilter;

// a filter is neccesary to admit only the right file extensions

public class PersonFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		
		if (f.isDirectory()) {
			return true;
		}

		String name = f.getName();
		//now, to get the extension of the file, use this little utility class
		String extension = Utils.getFileExtension(name);
		// return true only if extension is "per"
		if (extension == null || extension != "per") {
			return false;
		} else return true;
	}

	@Override
	public String getDescription() {
		return "Person database files (*.per)";
	}

}
