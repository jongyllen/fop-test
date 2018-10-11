package com.mycompany.app;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class FO2PDF {

    public void convertFO2PDF(File fo, File pdf) throws IOException {

        OutputStream out = null;

        try {

            out = new FileOutputStream(pdf);
            out = new BufferedOutputStream(out);

            FopFactory fopFactory = FopFactory.newInstance(new File("fop-cfg.xml"));

            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();

            Source src = new StreamSource(fo);
            Result res = new SAXResult(fop.getDefaultHandler());

            transformer.transform(src, res);

        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            out.close();
        }
    }
}
