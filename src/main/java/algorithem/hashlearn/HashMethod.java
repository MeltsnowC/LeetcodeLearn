package algorithem.hashlearn;

import java.util.*;
import java.util.stream.Collectors;

import static jdk.nashorn.internal.objects.NativeArray.push;

public class HashMethod {

    /**
     * 1.hash,判断两个字符串是否是字母异位词，
     * s = anagram t = nagaram 输出true
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s,String t){
        if (s.length()!=t.length()){
            return false;
        }

//        保存t中的字符
        Map<Object, Integer> sett = t.chars().mapToObj(c->(char)c).collect(Collectors.toMap(c->c, c->1,Integer::sum));
//        保存s中的字符
        Map<Object, Integer> sets = s.chars().mapToObj(c->(char)c).collect(Collectors.toMap(c->c, c->1,Integer::sum));
        boolean result = sets.equals(sett);
        return result;
    }

    /**
     * 242. 有效的字母异位词 当题目限制了数值的大小，就可以使用数组代替哈希表。
     *  时间复杂度O(m+n) 空间复杂度O(1)
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram2(String s, String t) {
        int[] record = new int[26];

        for (int i = 0; i < s.length(); i++) {
            record[s.charAt(i) - 'a']++;     // 并不需要记住字符a的ASCII，只要求出一个相对数值就可以了
        }

        for (int i = 0; i < t.length(); i++) {
            record[t.charAt(i) - 'a']--;
        }

        for (int count: record) {
            if (count != 0) {               // record数组如果有的元素不为零0，说明字符串s和t 一定是谁多了字符或者谁少了字符。
                return false;
            }
        }
        return true;                        // record数组所有元素都为零0，说明字符串s和t是字母异位词
    }

    /**
     * 2.找到两个字符串s中所有p的异位词的字串。返回这些字串起始索引。
     * 使用滑动窗口
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagram(String s,String p){
        if (s.length()<p.length()) return new ArrayList<>();
        //统计p中每个字符出现的个数
        Map<Character, Integer> collect = p.chars().mapToObj(c -> (char) c).collect(Collectors.toMap(c -> c, c -> 1, Integer::sum));
        //保存索引结果
        List<Integer> li = new ArrayList<>();
        int index = 0;
        int end = p.length();
        //定义滑动窗口
        Map<Character, Integer>  wind = s.substring(index,end).chars().mapToObj(c->(char)c).collect(Collectors.toMap(c->c,c->1,Integer::sum));

        if (collect.equals(wind)){
            li.add(index);
        }
        System.out.println(wind);
        while (end<s.length()){
            wind.merge(s.charAt(index++),-1,Integer::sum);
            wind.merge(s.charAt(end++),1,Integer::sum);
            //删掉后来加入的，否则后边比较的时候永远是不一致的。
            wind.entrySet().removeIf(entry->!collect.containsKey(entry.getKey()));
            if (collect.equals(wind)){
                li.add(index);
            }
        }
        return li;
    }

    /**
     * 3.给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。[字母异位词分组]
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs){
        //将每个字符串中的字符和个数map作为一个key,将对应字符串作为value，结果返回所有的values
//        也可以直接用排序之后的String作为key
        Map<Map<Character,Integer>,List<String>> mpStrs = new HashMap<Map<Character, Integer>, List<String>>();
        for (String s :
                strs) {
            Map<Character, Integer> collect = s.chars().mapToObj(c -> (char) c).collect(Collectors.toMap(c -> c, c -> 1,Integer::sum));
            List<String> tem = mpStrs.getOrDefault(collect,new ArrayList<>());
            tem.add(s);
            mpStrs.put(collect,tem);
        }
        assert mpStrs != null;
        System.out.println(mpStrs.values());
        return new ArrayList<List<String>>(mpStrs.values());
    }

    /**
     * 计数方法实现3
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    /**
     * 5.求两个数组的交集
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1,int[] nums2){
        if (nums1==null || nums1.length==0 || nums2==null || nums2.length==0){
            return new int[0];
        }
        Set<Integer> set1 = new HashSet<Integer>();
        Set<Integer> result = new HashSet<Integer>();


        Arrays.stream(nums1).forEach(c->set1.add(c));
        for (int i:nums2){
            if (set1.contains(i)){
                result.add(i);
            }
        }
        System.out.println(set1);
        return result.stream().mapToInt(c->c).toArray();
    }

    /**
     * 判断一个数是不是快乐数，即，每一次将该数替换为它每个位置上的数字的平方和，
     * 重复着过程知道变为1，也可能数无限循环，如果变为1，那么这个数就是快乐数。
     * @param n
     * @return
     */
    public boolean isHappy(int n){
        //计算每个位数和

        Set<Integer> commen = new HashSet<>();
        while (n!=1){
            if (commen.contains(n)){
                return false;
            }
            commen.add(n);//顺序不能颠倒，否则直接就会false。
            n = powerSum(n);
//            System.out.println(n);
        }
        return true;
    }
    public int powerSum(int n){
        int temp = 0;
        int low = 0;
        while (n!=0){
            low = n%10;//获取最低位
            temp += (int) Math.pow(low,2);
            n = n/10;
        }
       return temp;

    }
}
