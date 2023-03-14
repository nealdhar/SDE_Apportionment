package edu.virginia.cs.hw3;

public class ApportionmentFormatFactory {
    public ApportionmentFormat getApportionmentFormat(String apportionmentFormat) {
        if (apportionmentFormat.equals("alpha")) {
            return new AlphabeticalApportionmentFormat();
        }
        if (apportionmentFormat.equals("benefit")) {
            return new RelativeBenefitFormat();
        }
        else throw new IllegalArgumentException("Enter valid format");
        }
    }

