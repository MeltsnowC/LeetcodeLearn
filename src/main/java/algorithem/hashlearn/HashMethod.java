package algorithem.hashlearn;

import javax.swing.text.html.parser.Entity;
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
     * 6.判断一个数是不是快乐数，即，每一次将该数替换为它每个位置上的数字的平方和，
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
    private int powerSum(int n){
        int temp = 0;
        int low = 0;
        while (n!=0){
            low = n%10;//获取最低位
            temp += (int) Math.pow(low,2);
            n = n/10;
        }
       return temp;
    }

    /**
     * 7.计算数组中两数只和等于target的元素索引。每个元素只能使用一次
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums,int target){
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (map.containsKey(target- nums[i])){
                return new int[]{map.get(target-nums[i]),i};
            }
            map.put(nums[i],i);
        }
        return null;
    }

    /**
     * 8.计算四个数和为0的元组个数
     * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0
     * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -2^28 到 2^28 - 1 之间，最终结果不会超过 2^31 - 1 。
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int forSumCount(int[] A,int[] B,int[] C,int[] D){
        int count = 0;
        int temp = 0;
        Map<Integer,Integer> map = new HashMap<>();
        //首先计算AB两个数组中的元素和，保存在map中
        for (int i=0;i<A.length;i++){
            for (int b:B){
                temp = b+A[i];
                map.put(temp,map.getOrDefault(temp,0)+1);
            }
        }

        //统计剩余的两个元素的和，在map中找是否存在相加为0的情况，同时记录次数

        for (int c:C){
            for (int d:D){
                temp = c+d;
                count = map.getOrDefault(0-temp,0);
            }
        }
        return count;
    }

    /**
     * 9.赎金信，杂志中每个字符只能使用一次.这里直接使用数组效率更高
     * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，
     * 判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。如果可以构成，返回 true ；否则返回 false。
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote,String magazine){
       Map<Object,Integer> mapran =   ransomNote.chars().mapToObj(c->(char)c).collect(Collectors.toMap(c->c,c->1,Integer::sum));
       Map<Object,Integer> mapmag =   magazine.chars().mapToObj(c->(char)c).collect(Collectors.toMap(c->c,c->1,Integer::sum));
       boolean result = mapran.entrySet().stream().allMatch(entry -> mapmag.containsKey(entry.getKey()) && (mapmag.get(entry.getKey()) >=entry.getValue()));
       return result;
    }

    /**
     * 10.使用数组实现赎金信
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstructBylist(String ransomNote,String magazine){
        if (ransomNote.length()>magazine.length()){//个数不够，直接false
            return false;
        }
        int[] record = new int[26];//定义存放字母的数组
        for (char a:magazine.toCharArray()){
            record[a-'a'] += 1;
        }
        for (char b:ransomNote.toCharArray()){
            record[b-'a']-=1;
        }
        // 如果数组中存在负数，说明ransomNote字符串总存在magazine中没有的字符
        for(int i : record){
            if(i < 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 11.三数之和
     * 哈希法,会超时
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     *
     * 注意： 答案中不可以包含重复的三元组。
     * 时间复杂度: O(n^2)
     * 空间复杂度: O(n)，额外的 set 开销
     * @param list
     * @return
     */
    public List<List<Integer>> threeSum(int[] list){
//        set中存放nums元素，temp存放两个数的和
        Set<List<Integer>> result = new HashSet<>();
        int temp = 0;//保存+b
        Set<Integer> set = new HashSet<>();
        for (int i=0;i<list.length;i++){
            for (int j=i+1;j<list.length;j++){
                temp = list[i]+list[j];
                if (set.contains(0-temp)){
                    result.add(Arrays.asList(list[i],0-temp,list[j]).stream().sorted().collect(Collectors.toList()));
                }else {
                    set.add(list[j]);
                }
            }
            set.clear();
        }
        return new ArrayList<>(result);
    }

    /**
     * 12.三数之和
     * 使用双指针法，注意去重方法
     * 首先对数组排序
     * 依然还是在数组中找到 abc 使得a + b +c =0，我们这里相当于 a = nums[i]，b = nums[left]，c = nums[right]。
     * 如果 nums[i] + nums[left] + nums[right] < 0 说明 此时 三数之和小了，left 就向右移动，才能让三数之和大一些，直到left与right相遇为止。
     * 时间复杂度：O(n^2)。
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSumByDoublePoint(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        // 找出a + b + c = 0
        // a = nums[i], b = nums[left], c = nums[right]
        for (int i = 0; i < nums.length; i++) {
            // 排序之后如果第一个元素已经大于零，那么无论如何组合都不可能凑成三元组，直接返回结果就可以了
            if (nums[i] > 0) {
                return result;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {  // 去重a
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (right > left) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    //找到了三元组
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 去重逻辑应该放在找到一个三元组之后，对b 和 c去重
                    while (right > left && nums[right] == nums[right - 1]) right--;
                    while (right > left && nums[left] == nums[left + 1]) left++;

                    right--;
                    left++;
                }
            }
        }
        return result;
    }



}
