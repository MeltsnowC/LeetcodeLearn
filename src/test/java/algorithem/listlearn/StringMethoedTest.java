package algorithem.listlearn;

import algorithem.stringlearn.MyString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
@Slf4j

public class StringMethoedTest {
    @Test
    public void test(){
        String s = "We are happy.";

        MyString myString = new MyString();
        String result = myString.replaceSpace(s);
        log.info(result);

    }
}
