

import org.junit.rules.ErrorCollector;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!


public class UrlValidatorTest {
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void testManualTest()
    {
        //You can use this function to implement your manual testing
        UrlValidator validator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        try {
            collector.checkThat(validator.isValid(null), equalTo(false));
        } catch (Error e) {
            collector.addError(e);
        }
        try {
            collector.checkThat(validator.isValid("http"), equalTo(false));
        } catch (Error e) {
            collector.addError(e);
        }
        try {
            collector.checkThat(validator.isValid("abc.def.google.com"), equalTo(false));
        } catch (Error e) {
            collector.addError(e);
        }
        try {
            collector.checkThat(validator.isValid("http://www....google.com"), equalTo(false));
        } catch (Error e) {
            collector.addError(e);
        }
        try {
            collector.checkThat(validator.isValid("http://www.@@$.com"), equalTo(false));
        } catch (Error e) {
            collector.addError(e);
        }
        try {
            collector.checkThat(validator.isValid("http://localhost:8000"), equalTo(false));
        } catch (Error e) {
            collector.addError(e);
        }
        try {
            collector.checkThat(validator.isValid("https://www.pir.com"), equalTo(true));
        } catch (Error e) {
            collector.addError(e);
        }
        try {
            collector.checkThat(validator.isValid("http://www.google.com"), equalTo(true));
        } catch (Error e) {
            collector.addError(e);
        }
        try {
            collector.checkThat(validator.isValid("https://www.google.org"), equalTo(true));
        } catch (Error e) {
            collector.addError(e);
        }
        collector.checkThat(validator.isValid("http://www.google.com/search"), equalTo(true));
        try {
            collector.checkThat(validator.isValid("https://www.google.com/search?source=hp&ei=VzGXWrTyKKGJ0wKdt6-gBA&q=hello&oq=hello"), equalTo(true));
        } catch (Error e) {
            collector.addError(e);
        }

    }

    @Test
    public void testYourFirstPartition()
    {
        //You can use this function to implement your First Partition testing

    }

    @Test
    public void testYourSecondPartition(){
        //You can use this function to implement your Second Partition testing

    }
    //You need to create more test cases for your Partitions if you need to
    @Test
    public void testIsValid()
    {
        //You can use this function for programming based testing
                UrlValidator validator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        System.out.println("\n\n----- PROGRAMMATIC TESTING -----\n");
        int i;

        String myUrl = "http://www.aol.com/#?x=1 ";

        System.out.println(testPart(validator, myUrl));

        // available components (first 3 are good, last three are bad)
        String[] schemes = {"http://", "ftp://", "", "aa:", "/////", "aolc"};
        String[] authorities = {"www.aol.com", "www.biz.gov", "d.co", "ll", "", "//////"};
        String[] ports = {"", ":8000", ":0", ":999999999", "abd", ":::"};
        String[] paths = {"", "/oboe", "/oboe/", "///", "/#", "/.."};
        String[] queries = {"", "?action=up", "?x=1", "?value=false", "?bobo=no", "?yes=ffS%"};

        String url = "";

        boolean check = true; // flag to see if URL should be invalid
        int ranNum = 0;
        boolean result;
        // randomly generate urls and test on each run
        for(i = 0; i < 100; i++){

            url = ""; // reset url
            check = true; // reset flag

            // append random scheme
            ranNum = (int) (Math.random() * 5);
            if(ranNum > 2){
                check = false;
            }

            url += schemes[ranNum];

            // append random authority
            ranNum = (int) (Math.random() * 5);
            if(ranNum > 2){
                check = false;
            }

            url += authorities[ranNum];

            // append random port
            ranNum = (int) (Math.random() * 5);
            if(ranNum > 2){
                check = false;
            }

            url += ports[ranNum];

            // append random path
            ranNum = (int) (Math.random() * 5);
            if(ranNum > 2){
                check = false;
            }

            url += paths[ranNum];

            // append random query (doesn't change check flag because all are

            ranNum = (int) (Math.random() * 5);

            url += queries[ranNum];

            try{
                result = testPart(validator, url);
                if((check != result)){
                    failMessage(url + " returned " + result);
                }
            }
            catch(Error e){
                System.out.println(e);
                System.out.println("Error occured with url: " + url);
            }
        }

    }

}
