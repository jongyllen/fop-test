package com.mycompany.app;
import java.io.*;

public class App 
{
    public static void main(String[] args) {


        try {

            System.out.println("Running...");

            FO2PDF pdfGenerator = new FO2PDF();

            File baseDir = new File(".");
            File outDir = new File(baseDir, "out");
            outDir.mkdirs();

            File dir = new File("xml/fo");
            File[] directoryListing = dir.listFiles();
            System.out.println("Found " + directoryListing.length + " fo files");
            System.out.println();

            for (File inFile : directoryListing) {

                File pdfFile = new File(outDir, inFile.getName() + ".pdf");

                System.out.println("Input: XSL-FO (" + inFile + ")");
                System.out.println("Output: PDF (" + pdfFile + ")");
                System.out.println();
                System.out.println("Transforming...");

                try {
                    pdfGenerator.convertFO2PDF(inFile, pdfFile);
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }

            System.out.println("Success!");


        }  catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(-1);
        }
    }


}
