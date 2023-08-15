package algorithem.stringlearn;

import lombok.extern.slf4j.Slf4j;


import java.util.Map;
@Slf4j
public class MyString {
    /**
     * 1.翻转字符串,不消耗额外空间
     * @param str
     */
    public void  reverseString(String str){
        char[] chars = str.toCharArray();
        int pre = 0;
        int last = chars.length-1;
        while (pre<last){
            chars[pre]^=chars[last];
            chars[last]^=chars[pre];
            chars[pre]^=chars[last];
            pre++;
            last--;
        }
    }

    /**
     * 2.翻转字符串，
     * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
     *
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。
     * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();

        for (int i=0;i<s.length();i+=2*k){
            //1.每个2k个字符，就翻转前k个
            //2.剩余字符小于2k,大于k个，翻转前边k个
            int  start = i;
            int end = Math.min(s.length()-1,start+k-1);
            while (start<end){
                chars[start]^=chars[end];
                chars[end]^=chars[start];
                chars[start]^=chars[end];
                start++;
                end--;
            }
        }
        return new String(chars);
    }
    public void reverse(char[] ch, int i, int j) {
        for (; i < j; i++, j--) {
            char temp = ch[i];
            ch[i] = ch[j];
            ch[j] = temp;
        }
    }
    public String reversStr2(String s,int k){
        char[] chars = s.toCharArray();
        for (int i=0;i<s.length();i+=2*k){
            if (i+k<=s.length()){
                reverse(chars,i,i+k-1);
            }else {
                reverse(chars,i,s.length()-1);
            }
        }

        return new String(chars);
    }

    /**
     * 3.替换字符串中的空格为20%
     * @param s
     * @return
     */
    public String replaceSpace(String s){
        //统计空格数量
        int count = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ' '){
                count++;
            }
        }
        char[] sb = new char[s.length()+count*2];
        System.out.println(sb.length);
        //从后往前添加字符
//        i表示新字符串长度，j为旧字符串长度
        for (int i=sb.length-1,j=s.length()-1;j>=0;i--,j--){
            if (s.charAt(j)!=' '){
                sb[i] = s.charAt(j);
            }else {
                sb[i] = '0';
                sb[i-1] = '2';
                sb[i-2] = '%';
                i -= 2;
            }
        }
        return new String(sb);
    }

    //3.1使用一个新的对象，复制 str，复制的过程对其判断，是空格则替换，否则直接复制，类似于数组复制
    public static String replaceSpace2(String s) {
        if (s == null) {
            return null;
        }
        //选用 StringBuilder 单线程使用，比较快，选不选都行
        StringBuilder sb = new StringBuilder();
        //使用 sb 逐个复制 s ，碰到空格则替换，否则直接复制
        for (int i = 0; i < s.length(); i++) {
            //s.charAt(i) 为 char 类型，为了比较需要将其转为和 " " 相同的字符串类型
            //if (" ".equals(String.valueOf(s.charAt(i)))){}
            if (s.charAt(i) == ' ') {
                sb.append("%20");
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 3.2双指针解决交换空格
     * @param s
     * @return
     */
    public String replaceSpace3(String s) {
        if(s == null || s.length() == 0){
            return s;
        }
        //扩充空间，空格数量2倍
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ' '){
                str.append("  ");
            }
        }
        //若是没有空格直接返回
        if(str.length() == 0){
            return s;
        }
        //有空格情况 定义两个指针
        int left = s.length() - 1;//左指针：指向原始字符串最后一个位置
        s += str.toString();
        int right = s.length()-1;//右指针：指向扩展字符串的最后一个位置
        char[] chars = s.toCharArray();
        while(left>=0){
            if(chars[left] == ' '){
                chars[right--] = '0';
                chars[right--] = '2';
                chars[right] = '%';
            }else{
                chars[right] = chars[left];
            }
            left--;
            right--;
        }
        return new String(chars);
    }

    /**
     * 4.翻转字符串中的单词
     * @param stirng
     * @return
     */
    public String removeExtraSpaces(String stirng){
//        int fast = stirng.length()-1;
//        int low = stirng.length()-1;
        StringBuilder sb = new StringBuilder();
        for (int fast = stirng.length()-1,low = stirng.length()-1;fast<=0;){
            while (true){

            }
        }
        return null;
    }



}
