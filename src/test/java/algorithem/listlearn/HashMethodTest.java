package algorithem.listlearn;

import algorithem.hashlearn.HashMethod;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class HashMethodTest {
    @Test
    public void test(){
        String s = "anagram";
        String t = "nagragm";
        String s1 = "cbaebabacd";
        String t1 = "abc";
        String s2 = "abab";
        String t2 = "ab";
        String s3 = "cbaebabacd";
        String t3 = "abc";


        HashMethod hashMethod = new HashMethod();
        boolean re = hashMethod.isAnagram(s, t);
        List<Integer> anagram = hashMethod.findAnagram(s3, t3);
        System.out.printf("寻找异位词:\n子串size:"+anagram.size()+"\n字串索引："+ anagram.toString());
        System.out.println(s+"和"+t+"是否为异位词："+re);

        boolean happy = hashMethod.isHappy(9);
        System.out.println(happy);

    }
}
