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
    @Test
    public void testTwoSum(){
        HashMethod hm = new HashMethod();
        int[] nums = new int[]{2,7,11,15};
        int target = 9;
        int[] nums2 = new int []{3,2,4};
        int target2 = 6;
        int[] nums3 = new int []{3,3};
        int target3 = 6;
        int[] ints = hm.twoSum(nums, target);
        int[] ints2 = hm.twoSum(nums2, target2);
        int[] ints3 = hm.twoSum(nums3, target3);
        System.out.println(Arrays.toString(ints3));
        System.out.println(Arrays.toString(ints2));
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void testForSum(){
        HashMethod hm = new HashMethod();
        int[] A = new int[]{};
        int[] B = new int[]{};
        int[] C = new int[]{};
        int[] D = new int[]{};

        int result = hm.forSumCount(A, B, C, D);
        System.out.println(result);
    }
}
