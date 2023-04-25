package com.auddice.qfield.bdd.update;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        System.out.println( "Generation à partir du fichier "+ args[0]);

        List<String> inputLines = Files.readAllLines(Paths.get(args[0]));
        List<String> outputLines = new ArrayList<>();

        for(String line : inputLines){
            List<String> outputLine = new ArrayList<>();
            String[] csvLine = line.split(";");
            // On ajoute le CD_VERN et le CD_NOM
            outputLine.add(csvLine[0]);
            outputLine.add(csvLine[1]);
            // On prend le premier no vernaculaire avant la virgule
            String nomVernaculaire = StringUtils.substringBefore(csvLine[2],',');
            String nomVernaculaireSansAccent = StringUtils.stripAccents(nomVernaculaire);
            outputLine.add(nomVernaculaire);
            outputLine.add(nomVernaculaireSansAccent);
            String outputLineStr = outputLine.stream().collect(Collectors.joining(";"));

            outputLines.add(outputLineStr);
        }

        Files.write(Paths.get(args[0].replace(".csv",".output.csv")),outputLines);
    }

}
