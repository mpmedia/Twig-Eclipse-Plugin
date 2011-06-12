package org.eclipse.twig.core.documentModel.handler;

import org.eclipse.php.internal.core.documentModel.encoding.PHPDocumentCharsetDetector;
import org.eclipse.php.internal.core.documentModel.handler.PHPModelHandler;
import org.eclipse.twig.core.documentModel.loader.TwigDocumentLoader;
import org.eclipse.twig.core.documentModel.loader.TwigModelLoader;
import org.eclipse.twig.core.documentModel.provisional.contenttype.ContentTypeIdForTwig;
import org.eclipse.wst.sse.core.internal.document.IDocumentCharsetDetector;
import org.eclipse.wst.sse.core.internal.document.IDocumentLoader;
import org.eclipse.wst.sse.core.internal.provisional.IModelLoader;

/**
 * 
 * 
 * The {@link TwigModelHandler} is the main entry point of
 * the org.eclipse.wst.sse.core.modelHandler extension point.
 * 
 * It creates the {@link TwigModelLoader} and {@link TwigDocumentLoader}
 * instances.
 * 
 * The most part of this procedure is basically copy-pasted from
 * the SmartyPlugin:
 * 
 * http://code.google.com/p/smartypdt/
 * 
 * The reason for this is described here:
 * 
 * http://www.eclipse.org/forums/index.php/t/210550/
 * 
 * and here
 * 
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=296976
 * 
 * It boils down to this:
 * 
 * The grammar of the underlying XML Tokenizer is created
 * using jflex (http://jflex.de/) - namely version 1.2.2
 * which is not available anymore.
 * 
 * The current version (1.4.3) of jflex does not compile the grammar,
 * so i just used the generated parser from the SmartyPlugin,
 * as it was the only one which extends the WTP HTML editor,
 * instead of writing one from scratch - like the Django plugin
 * does: http://eclipse.kacprzak.org/
 * 
 * 
 * @author Robert Gruendler <r.gruendler@gmail.com>
 *
 */
@SuppressWarnings("restriction")
public class TwigModelHandler extends PHPModelHandler {
	
	
	private static String ModelHandlerID = "org.eclipse.twig.core.documentModel.handler"; //$NON-NLS-1$
	
	
	public TwigModelHandler() {
	
		super();
		
		System.err.println("instantiate model handler");
		setId(ModelHandlerID);
		setAssociatedContentTypeId(ContentTypeIdForTwig.CONTENT_TYPE_ID_TWIG);
		
	}
	
	
	@Override
	public IModelLoader getModelLoader() {

		return new TwigModelLoader();
		
	}
	
	@Override
	public IDocumentCharsetDetector getEncodingDetector() {
		
		return new PHPDocumentCharsetDetector();
	}
	
	@Override
	public IDocumentLoader getDocumentLoader() {

		return new TwigDocumentLoader();
	}
}