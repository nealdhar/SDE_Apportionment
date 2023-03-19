# HW3-Apportionment-Refactored

## Authors

* Samira Samadi - ss5ug
* Neal Dhar - nd2pvz

## Description on how to use your code
* Copy the .git link from the GitHub repository and paste the link in IntelliJ (New --> Project From Version Control --> Github)
* Open "Terminal" tab at the bottom and type *./gradlew build* to generate .jar file in build/libs/
* Drag csv files to be in the same place as the .jar file: build/libs/
* To run jar file, type in terminal: java -jar build/libs/Apportionment.jar build/libs/fileName.csv *optional flags and arguments*
* At the end of the command to run the jar file, optional flags and arguments can be specified 
  * long flags
    * --reps [integer] - must be followed by a positive (non-zero, non-negative) integer. Defaults to --reps 435 
    * --format formatName - set the format name. Format choices:
      * --format alpha - print States (AlphabeticalApportionmentFormat): this is the default value 
      * --format benefit - prints States by benefit (RelativeBenefitFormat)
    * algorithm strategyName - set the Apportionment strategy 
      * --algorithm hamilton - use Hamilton Apportionment algorithm 
      * --algorithm jefferson  - use Jefferson Apportionment algorithm 
      * --algorithm huntington - use Huntington-Hill Apportionment algorithm: this is the default value
  * short flags
    * -r is short for --reps
    * -f is short for --format 
    * -a is short for --algorithm
    * combined short flags
      * -rfa 1000 benefit hamilton
        * The next argument (1000) is -r (--reps)
        * The argument after that (benefit)  is -f (--format)
        * And the argument after that (hamilton) is -a (--algorithm)
  * short and long flags can be used simultaneously
    * for example: --reps 1000 -f alpha -algorithm hamilton

