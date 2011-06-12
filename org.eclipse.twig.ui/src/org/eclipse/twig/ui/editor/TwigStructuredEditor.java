package org.eclipse.twig.ui.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.twig.core.documentModel.parser.TwigSourceParser;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.wst.sse.ui.StructuredTextEditor;


/**
 * 
 * 
 * 
 * @see http://code.google.com/p/smartypdt/
 *
 */
public class TwigStructuredEditor extends StructuredTextEditor {

	public TwigStructuredEditor() {
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void doSetInput(IEditorInput input) throws CoreException {

		IResource resource = null;
		if (input instanceof IFileEditorInput) {
			// This is the existing workspace file
			final IFileEditorInput fileInput = (IFileEditorInput) input;
			resource = fileInput.getFile();
		} else if (input instanceof IStorageEditorInput) {
			// This kind of editor input usually means non-workspace file, like
			// PHP file which comes from include path, remote file which comes
			// from
			// Web server while debugging, file from ZIP archive, etc...

			//			final IStorageEditorInput editorInput = (IStorageEditorInput) input;
			//			final IStorage storage = editorInput.getStorage();

			//			if (storage instanceof ZipEntryStorage) {
			//				resource = ((ZipEntryStorage) storage).getProject();
			//			}
		}

		if (resource instanceof IFile) {
			if ("twig".equals(resource.getFullPath().getFileExtension())) {

				TwigSourceParser.editFile.set(resource);

				super.doSetInput(input);

			} else {
				super.doSetInput(input);
			}
		} else {
			super.doSetInput(input);
		}

		ImageDescriptor imageDescriptor = input.getImageDescriptor();
		if (imageDescriptor != null) {
			setTitleImage(JFaceResources.getResources().createImageWithDefault(imageDescriptor));
		}
//		if (isShowingOverrideIndicators()) {
//			installOverrideIndicator(true);
//		}		
	}

}