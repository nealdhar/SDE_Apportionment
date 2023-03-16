package edu.virginia.cs.hw3;

public class ApportionmentFormatFactory {
    public ApportionmentFormat getApportionmentFormat(String format) {
        if (format.equals("alpha")) {
            return new AlphabeticalApportionmentFormat();
        }
        if (format.equals("benefit")) {
            return new RelativeBenefitFormat();
        }
        else throw new IllegalArgumentException("Enter valid format");
        }
    }

