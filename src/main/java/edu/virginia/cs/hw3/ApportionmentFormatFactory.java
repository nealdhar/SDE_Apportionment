package edu.virginia.cs.hw3;

public class ApportionmentFormatFactory {
    public ApportionmentFormat getApportionmentFormat(String format) {
        if (format.trim().equals("alpha")) {
            return new AlphabeticalApportionmentFormat();
        }
        if (format.trim().equals("benefit")) {
            return new RelativeBenefitFormat();
        }
        else {
            throw new IllegalArgumentException("Error: " + format + " is an invalid format");
        }
    }
}
